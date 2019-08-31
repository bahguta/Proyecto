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
import javax.swing.JFrame;
import org.openide.util.Exceptions;

/**
 *
 * @author Plam
 */
public class GestionarFacturas {

    private List<Factura> listaFacturas;
    private ConexionBBDD conexion;

    /**
     * Constructor
     *
     * @param conexion la conexion para la base de datos
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {

            int ID_factura = -1;
            int productosInsertados = 0;
            String consultaNextID = "select ID_FACTURA_SEQ.nextVal from dual";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consultaNextID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado.next()) {
                ID_factura = resultado.getInt(1);
            }
            if (!GestionarCaja.ventaCompra(frame, logica, logica.getPersonaPorID(ID_persona), listaProductos, precio, gestionarInventario)) {
                return 0;
            }
            String consulta = "insert into factura (ID_factura, fecha, trabajo, precio) values ( " + ID_factura + " , TO_DATE('" + sdf.format(new Date()) + "'), '" + trabajo + "', " + precio + ")";
            filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (Producto producto : listaProductos) {
                String subConsulta = "insert into negocio (ID_factura, ID_persona, ID_producto, cantidad ) values (" + ID_factura + " , " + ID_persona + ", " + producto.getCodProducto() + ", " + producto.getCantidad() + ")";
                productosInsertados = conexion.ejecutarStatementNOSELECT(subConsulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            }
            System.out.println("Facturas insertadas: " + filas);
            System.out.println("Productos insertados para factura con ID " + ID_factura + ": " + productosInsertados);
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
        String consulta = "select distinct ID_FACTURA from negocio where ID_PERSONA in (select ID_PERSONA from negocio) order by ID_FACTURA";
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            lista = new ArrayList<>();
            try {
                while (resultado.next()) {
                    lista.add(resultado.getInt("ID_FACTURA"));
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
        List<Factura> listaFact = new ArrayList<>();
        List<Producto> listaProductos = null;
        List<Integer> listaIDFacturas = getIDFacturasNegocio();
        int ID_persona = 0;
        try {
            for (Integer ID_factura : listaIDFacturas) {
                System.out.println(ID_factura);
                String cons = "select ID_PERSONA, ID_PRODUCTO, CANTIDAD from negocio where ID_FACTURA = " + ID_factura + " order by ID_PERSONA";
                ResultSet resultadocons = conexion.ejecutarStatementSELECT(cons, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                if (resultadocons != null) {
                    listaProductos = new ArrayList<>();
                    while (resultadocons.next()) {

                        if (ID_persona != resultadocons.getInt("ID_PERSONA")) {
                            ID_persona = resultadocons.getInt("ID_PERSONA");
                            int ID_producto = resultadocons.getInt("ID_PRODUCTO");
                            int cantidad = resultadocons.getInt("CANTIDAD");
                            Producto p = logica.getProductoPorID(ID_producto);

                            listaProductos.add(new Producto(
                                    p.getCodProducto(),
                                    p.getNombre(),
                                    p.getPrecio(),
                                    p.getPeso(),
                                    cantidad
                            ));

                            String conFacturas = "select * from factura where ID_FACTURA = " + ID_factura;
                            ResultSet resultadoFacturas = conexion.ejecutarStatementSELECT(conFacturas, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                            if (resultadoFacturas.next() && logica.getPersonaPorID(ID_persona).getTipo().equals(type)) {

                                listaFact.add(new Factura(
                                        ID_factura,
                                        resultadoFacturas.getDate("FECHA"),
                                        resultadoFacturas.getString("TRABAJO"),
                                        listaProductos,
                                        resultadoFacturas.getDouble("PRECIO")));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFact;
    }

//    public List<Factura> getFacturasPorType(String type, LogicaNegocio logica) throws NullPointerException{
//        //creo una lista nueva para las facturas
//        List<Factura> listaFact = null;
//
//        int ID_factura = 0, ID_persona = 0, ID_producto = 0, cantidad = 0;
//        //consulta para obtener el codigo de todas las personas
//        String consulta = "select distinct ID_PERSONA from negocio order by ID_PERSONA";
//        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//        if (resultado != null) {
//            listaFact = new ArrayList<>();
//            try {
//                while (resultado.next()) {
//                    //creo una lista nueva para los productos
//                    List<Producto> listaProductos = null;
//                    //asigno el valor para ID_persona
//                    ID_persona = resultado.getInt("ID_PERSONA");
//                    //subconsulta para obtener las facturas de la persona con ID_persona
//                    String subcon = "select distinct ID_FACTURA from negocio where ID_PERSONA in (select ID_PERSONA from negocio) order by ID_FACTURA";
//                    ResultSet resultadoSubCons = conexion.ejecutarStatementSELECT(subcon, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    if (resultadoSubCons != null) {
//                        while (resultadoSubCons.next()) {
//                            ID_factura = resultadoSubCons.getInt("ID_FACTURA");
//                            //subconsulta para obtener pros productos con la cantidad que se ha vendido 
//                            String cons = "select ID_PRODUCTO, CANTIDAD from negocio where ID_FACTURA = " + ID_factura;
//                            ResultSet resultadocons = conexion.ejecutarStatementSELECT(cons, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                            if (resultadocons != null) {
//                                while (resultadocons.next()) {
//                                    ID_producto = resultadocons.getInt("ID_PRODUCTO");
//                                    cantidad = resultadocons.getInt("CANTIDAD");
//                                    
//                                    Producto p = logica.getProductoPorID(ID_producto);
//
//                                    listaProductos.add(new Producto(
//                                            p.getCodProducto(),
//                                            p.getNombre(),
//                                            p.getPrecio(),
//                                            p.getPeso(),
//                                            cantidad // la cantidad se coge de los productos de la factura
//                                    ));
//                                    //subconsulta para obtener la factura con el ID_factura
//                                    String conFacturas = "select * from factura where ID_FACTURA = " + ID_factura;
//                                    ResultSet resultadoFacturas = conexion.ejecutarStatementSELECT(conFacturas, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                                    //compruebo si existe un registro && compruebo que filtre solo las personas del tipo que buscamos
//                                    if (resultadoFacturas.next() && logica.getPersonaPorID(ID_persona).getTipo().equals(type)) {
//                                        //agrego la factura en la lista
//                                        listaFact.add(new Factura(
//                                                ID_factura,
//                                                resultadoFacturas.getDate("FECHA"),
//                                                resultadoFacturas.getString("TRABAJO"),
//                                                listaProductos,
//                                                resultadoFacturas.getDouble("PRECIO")));
//                                    } // end if
//                                } // end while
//                            } //end if
//                        } //end while
//                    } //end if
//                } // end while
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                throw new NullPointerException();
//            } catch (NullPointerException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }
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
//    
//    public Factura getFacturaPorID(int ID_factura) throws NullPointerException {
//        Factura f = null;
//        String consulta = "Select * from factura where ID_factura = " + ID_factura;
//        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        try {
//            if (resultado.next()) {
//                try {
//                    List<Producto> listaProductos = null;
//                    String subConsulta = "select p.ID_producto , p.nombre, p.precio, p.peso, n.cantidad from producto p , negocio n where p.id_producto = n.id_producto and n.id_factura =" + ID_factura;
//                    ResultSet resultadoProductos = conexion.ejecutarStatementSELECT(subConsulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    if (resultadoProductos != null) {
//                        listaProductos = new ArrayList<>();
//                        while (resultadoProductos.next()) {
//                            listaProductos.add(new Producto(
//                                    resultadoProductos.getInt(1),
//                                    resultadoProductos.getString(2),
//                                    resultadoProductos.getDouble(3),
//                                    resultadoProductos.getDouble(4),
//                                    resultadoProductos.getInt(5)
//                            ));
//                        }
//                    }
//                    f = new Factura(
//                            ID_factura,
//                            resultado.getDate(2),
//                            resultado.getString(3),
//                            listaProductos,
//                            resultado.getDouble(4));
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return f;
//    }

//    public List<Factura> getFacturasClientes() throws NullPointerException {
//        List<Factura> lista = new ArrayList<>();
//        String consulta = "Select * from persona where type = 'CLIENTE'; "; //(Select ID_persona from persona where type = 'CLIENTE');";
//        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        if (resultado != null) {
//            try {
//                while (resultado.next()) {
//                    int ID_factura = resultado.getInt(1);
//                    List<Producto> listaProductos = null;
//                    String subConsulta = "select p.ID_producto , p.nombre, p.precio, p.peso, n.cantidad from producto p , negocio n where p.id_producto = n.id_producto and n.id_factura =" + ID_factura;
//                    ResultSet resultadoProductos = conexion.ejecutarStatementSELECT(subConsulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    if (resultadoProductos != null) {
//                        listaProductos = new ArrayList<>();
//                        while (resultadoProductos.next()) {
//                            listaProductos.add(new Producto(
//                                    resultadoProductos.getInt(1),
//                                    resultadoProductos.getString(2),
//                                    resultadoProductos.getDouble(3),
//                                    resultadoProductos.getDouble(4),
//                                    resultadoProductos.getInt(5)
//                            ));
//                        }
//                    }
//                    lista.add(new Factura(
//                            ID_factura,
//                            resultado.getDate(2),
//                            resultado.getString(3),
//                            listaProductos,
//                            resultado.getDouble(4)));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return lista;
//    }
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
                    String subConsultaProductos = "select p.ID_producto , p.nombre, p.precio, p.peso, n.cantidad from producto p , negocio n where p.id_producto = n.id_producto and n.id_factura =" + ID_FACTURA;
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
            String consulta = "select * from factura where ID_FACTURA in (Select ID_FACTURA from negocio where ID_persona = " + ID_persona + ")";
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
