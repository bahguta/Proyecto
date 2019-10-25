/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Factura;
import Dto.Producto;
import Logica.ConexionBBDD;
import Logica.LogicaNegocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.openide.util.Exceptions;

/**
 * Gestionar Facturas
 * 
 * @author Plam
 */
public class GestionarFacturas {
    private static final Logger LOG = Logger.getLogger(GestionarFacturas.class.getName());

    private List<Factura> listaFacturas;
    private ConexionBBDD conexion;

    /**
     * Constructor
     *
     * @param conexion
     */
    public GestionarFacturas(ConexionBBDD conexion) {
        this.conexion = conexion;
        this.listaFacturas = new ArrayList<>();
    }

    /**
     * Metodo para borrar una factura
     *
     * @param idFactura el codigo de la factura que se va a borrar
     * @return retorna las filas actualizadas
     */
    public int delFactura(int idFactura) {
        String consulta = "delete from factura where ID_factura = " + idFactura;
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Facturas borradas: " + filas);
        refrescarFacturas();
        return filas;
    }

    /**
     * Metodo para crear una factura en la base de datos
     *
     * @param precio el precio de la factura
     * @param ID_persona el codigo de la persona al quien pertenece la factura
     * @param listaProductos la lista de los productos que contiene la factura
     * @param trabajo Las actividades que se han echo para la factura
     * @param logica el objeto logica para gestionar los datos de la factura
     * @param frame frame para poder mostrar Dialog Box
     * @param gestionarInventario el objeto gestionarInventarios para obtener
     * datos para la factura
     * @return retorna las filas actualizadas
     */
    public int addFactura(
            double precio,
            int ID_persona,
            List<Producto> listaProductos,
            String trabajo,
            LogicaNegocio logica,
            JFrame frame,
            GestionarInventario gestionarInventario) {

        int filas = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {

            int ID_factura = -1;
            int productosInsertados = 0;

            //consulta para insertar la factura para obtener su ID 
            String consulta = "insert into factura (fecha, trabajo, precio) values ('" + sdf.format(new Date()) + "', '" + trabajo + "', " + precio + ")";
            filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //obtengo el ID de la factura que se acaba de insertar para poder insertar correctamente
            //los productos en la tabla negocio relacionados con el ID de la factura
            String consultaNextID = "SELECT max(ID_factura) FROM factura";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consultaNextID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado.next()) {
                ID_factura = resultado.getInt(1);
            }

            if (logica.ventaCompra(frame, logica.getPersonaPorID(ID_persona), getFacturaPorID(ID_factura), precio, gestionarInventario)) {

                //insertando los registros de los productos en la tabla negocio 
                for (Producto producto : listaProductos) {
                    String subConsulta = "insert into negocio (ID_factura, ID_persona, ID_producto, cantidad ) values (" + ID_factura + " , " + ID_persona + ", " + producto.getCodProducto() + ", " + producto.getCantidad() + ")";
                    productosInsertados = conexion.ejecutarStatementNOSELECT(subConsulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                }
            } else {
                conexion.ejecutarStatementNOSELECT("rollback", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                return -1;

            }
            //
            System.out.println("Facturas insertadas: " + filas);
            System.out.println("Productos insertados para factura con ID " + ID_factura + ": " + productosInsertados + " productos");

            //commit
            conexion.ejecutarStatementNOSELECT("commit", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            refrescarFacturas();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return filas;
    }

//    private List<Integer> getPersonasPorType(String type) {
//        List<Integer> lista = null;
//        String consulta = "select distinct ID_PERSONA from negocio order by ID_PERSONA";
//        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//        if (resultado != null) {
//            try {
//                lista = new ArrayList<>();
//                while (resultado.next()) {
//                    lista.add(resultado.findColumn("ID_PERSONA"));
//                }
//            } catch (SQLException ex) {
//                Exceptions.printStackTrace(ex);
//            }
//        }
//        return lista;
//    }
    /**
     * Metodo auxiliar para obtener una lista tipo Integer con todos los ID de
     * las facturas de la lista. Este metodo esta usado en el metodo
     * getFacturasTypo(String type, LogicaNegocio logica)
     *
     * @return retorna una lista tipo Integer
     */
    private List<Integer> getIDFacturasNegocio() {
        List<Integer> lista = null;
        String consulta = "select distinct ID_factura from negocio where ID_PERSONA in (select ID_PERSONA from negocio) order by ID_factura";
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            lista = new ArrayList<>();
            try {
                while (resultado.next()) {
                    lista.add(resultado.getInt("ID_factura"));
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return lista;
    }

    /**
     * Metodo para obtener todas las facturas segun el tipo de la persona , es
     * decir , si el tipo es "Clientes" va a buscar facturas solo de personas
     * que son clientes, y en caso de "Proveedores" buscara solo facturas que
     * pertenecen a personas que son Proveedores
     *
     * @param type El tipo de la persona
     * @param logica logica para obtener datos necesarios para completar esta
     * tarea
     * @return retorna una lista con facturas tipo Factura
     */
    public List<Factura> getFacturasTypo(String type, LogicaNegocio logica) {
        refrescarFacturas();
        List<Factura> lista = new ArrayList<>();
        for (Factura factura : listaFacturas) {
            //System.out.println(logica.getPersonaPorIDFactura(factura.getCodFactura()).toString());
            if (logica.getPersonaPorIDFactura(factura.getCodFactura()) != null
                    && logica.getPersonaPorIDFactura(factura.getCodFactura()).getTipo().equalsIgnoreCase(type)) {
                lista.add(factura);
            }
        }
        return lista;
    }

//    
//    
//    public List<Factura> getFacturasTypo(String type, LogicaNegocio logica) {
//        List<Factura> listaFact = new ArrayList<>();
//        List<Producto> listaProductos = null;
//        List<Integer> listaIDFacturas = getIDFacturasNegocio();
//        int ID_persona = 0;
//        try {
//            for (Integer ID_factura : listaIDFacturas) {
//                System.out.println(ID_factura);
//                String cons = "select ID_persona, ID_producto, cantidad from negocio where ID_factura = " + ID_factura + " order by ID_persona";
//                ResultSet resultadocons = conexion.ejecutarStatementSELECT(cons, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                if (resultadocons != null) {
//                    listaProductos = new ArrayList<>();
//                    while (resultadocons.next()) {
//
//                        if (ID_persona != resultadocons.getInt("ID_persona")) {
//                            ID_persona = resultadocons.getInt("ID_persona");
//                            int ID_producto = resultadocons.getInt("ID_producto");
//                            int cantidad = resultadocons.getInt("cantidad");
//                            Producto p = logica.getProductoPorID(ID_producto);
//
//                            listaProductos.add(new Producto(
//                                    p.getCodProducto(),
//                                    p.getNombre(),
//                                    p.getPrecio(),
//                                    p.getPeso(),
//                                    cantidad
//                            ));
//
//                            String conFacturas = "select * from factura where ID_factura = " + ID_factura;
//                            ResultSet resultadoFacturas = conexion.ejecutarStatementSELECT(conFacturas, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//                            if (resultadoFacturas.next() && logica.getPersonaPorID(ID_persona).getTipo().equals(type)) {
//
//                                listaFact.add(new Factura(
//                                        ID_factura,
//                                        resultadoFacturas.getDate("fecha"),
//                                        resultadoFacturas.getString("trabajo"),
//                                        listaProductos,
//                                        resultadoFacturas.getDouble("precio")));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return listaFact;
//    }
    /**
     * Metodo para obtener una factura por su ID
     *
     * @param ID_factura el codigo de la factura que se va a buscar
     * @return returna una factura tipo Factura
     */
    public Factura getFacturaPorID(int ID_factura) {
        refrescarFacturas();
        for (Factura factura : listaFacturas) {
            if (factura.getCodFactura() == ID_factura) {
                return factura;
            }
        }
        return null;
    }

    /**
     * Metodo para refrescar la lista de las facturas
     */
    public void refrescarFacturas() {
        String consulta = "select * from factura";
        //List<Factura> lista = new ArrayList<>();
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            listaFacturas.clear();
            try {
                while (resultado.next()) {
                    int ID_FACTURA = resultado.getInt("ID_factura");
                    List<Producto> listaProductos = null;
                    String subConsultaProductos = "select producto.ID_producto, producto.nombre, producto.precio, producto.peso, negocio.cantidad from producto , negocio where  producto.ID_producto = negocio.ID_producto and negocio.ID_factura =" + ID_FACTURA;
                    ResultSet resultadoProductos = conexion.ejecutarStatementSELECT(subConsultaProductos, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    if (resultadoProductos != null) {
                        listaProductos = new ArrayList<>();
                        while (resultadoProductos.next()) {
                            listaProductos.add(new Producto(
                                    resultadoProductos.getInt(1),
                                    resultadoProductos.getString(2),
                                    resultadoProductos.getDouble(3),
                                    resultadoProductos.getDouble(4),
                                    resultadoProductos.getInt(5)
                            ));
                        } //end while
                    } // end if
                    listaFacturas.add(new Factura(
                            resultado.getInt(1),
                            resultado.getDate(2),
                            resultado.getString(3),
                            listaProductos,
                            resultado.getDouble(4)));
                } // end while
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        } //end if
    }

    public List<Factura> getTodasLasFacturas() {
        refrescarFacturas();
        return listaFacturas;
    }

    /**
     * Metodo para obtener una lista con todas las facturas de una persona
     *
     * @param ID_persona el codigo de la persona a las que pertenecen las
     * facturas
     * @return retorna una lista tipo Factura
     */
    public List<Factura> getFacturasPorIDPersona(int ID_persona) {
        List<Producto> listaProductos = null;
        List<Factura> listaFact = null;
        try {
            //consulta para obtener todas las facturas de una persona 
            String consulta = "select * from factura where ID_factura in (Select ID_factura from negocio where ID_persona = " + ID_persona + ")";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado != null) {
                listaFact = new ArrayList<>();
                while (resultado.next()) {
                    int ID_FACTURA = resultado.getInt("ID_factura");
                    //consulta para obtener todos los productos de una factura
                    String subConsultaProductos = "select p.ID_producto , p.nombre, p.precio, p.peso, n.cantidad from producto p , negocio n where p.id_producto = n.id_producto and n.id_factura =" + ID_FACTURA;
                    ResultSet resultadoSubConsultaProductos = conexion.ejecutarStatementSELECT(subConsultaProductos, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    if (resultadoSubConsultaProductos != null) {
                        listaProductos = new ArrayList<>();
                        while (resultadoSubConsultaProductos.next()) {
                            listaProductos.add(new Producto(
                                    resultadoSubConsultaProductos.getInt(1),
                                    resultadoSubConsultaProductos.getString(2),
                                    resultadoSubConsultaProductos.getDouble(3),
                                    resultadoSubConsultaProductos.getDouble(4),
                                    resultadoSubConsultaProductos.getInt(5)
                            ));
                        } //end while
                    } //end if
                    listaFact.add(new Factura(
                            ID_FACTURA,
                            resultado.getDate(2),
                            resultado.getString(3),
                            listaProductos,
                            resultado.getDouble(4)));
                } //end while
            } //end if
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaFact;
    }
}
