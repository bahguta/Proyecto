/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Producto;
import Logica.ConexionBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 * Gestionar Inventario
 *
 * @author Plam
 */
public class GestionarInventario {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GestionarInventario.class);

    private List<Producto> listaProductos;
    private ConexionBBDD conexion;
    private boolean existeLaTabla = false;

    /**
     * Constructor
     *
     * @param conexion 
     */
    public GestionarInventario(ConexionBBDD conexion) {
        this.conexion = conexion;
        this.listaProductos = new ArrayList<>();

    }

    /**
     * Metodo para refrescar la lista de los productos
     *
     * @return retorna una lista tipo Producto
     */
    public List<Producto> getListaProductos() {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        refrescarListaProductos();
        return this.listaProductos;
    }

    /**
     * Metodo para obtener una lista con los productos de una factura
     *
     * @param ID_FACTURA el codigo de la factura a la que se va a buscar
     * @return retorna una lista tipo Producto
     */
    public List<Producto> getProductosPorFactura(int ID_FACTURA) {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        List<Producto> lista = new ArrayList<>();
        String consulta = "select p.ID_producto , p.nombre, p.precio, p.peso, n.cantidad from producto p inner join negocio n on p.id_producto = n.id_producto and n.id_factura = " + ID_FACTURA;
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            try {
                while (resultado.next()) {
                    lista.add(new Producto(
                            resultado.getInt(1),
                            resultado.getString(2),
                            resultado.getDouble(3),
                            resultado.getDouble(4),
                            resultado.getInt(5)
                    ));
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return lista;
    }

    /**
     * Metodo para refrescar la lista de los productos
     *
     * @return retorna la lista de los productos
     */
    public void refrescarListaProductos() {
        if (!conexion.isConexionExitosa()) {
            return;
        }
        try {
            String consulta = "select * from producto";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado != null) {
                existeLaTabla = true;
                listaProductos = new ArrayList<>();
                while (resultado.next()) {
                    listaProductos.add(
                            new Producto(
                                    resultado.getInt("ID_producto"),
                                    resultado.getString("nombre"),
                                    resultado.getDouble("precio"),
                                    resultado.getDouble("peso"),
                                    resultado.getInt("cantidad")));
                } //end while
            }  else {
                existeLaTabla = false;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Metodo para crear un producto en la base de datos (Compra)
     *
     * @param nombre el nombre del producto
     * @param precio su precio
     * @param peso su peso
     * @param cantidad cuanta cantidad se ha comprado
     * @return returna las filas actualizadas
     */
    public int addProducto(String nombre, double precio, double peso, int cantidad) {
        int filas = -1;
        if (!conexion.isConexionExitosa()) {
            return filas;
        }
        String consulta = "insert into producto (nombre, peso, precio, cantidad) "
                + "values ('" + nombre + "', " + peso + ", " + precio + ", " + cantidad + ")";
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Productos insertados: " + filas);
        refrescarListaProductos();
        conexion.ejecutarStatementNOSELECT("commit", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return filas;
    }

    /**
     * Metodo para borrar un producto de la base de datos
     *
     * @param codProducto el codigo del producto que se va a borrar
     * @return returna las filas actualizadas
     */
    public int borrarProducto(int codProducto) {
        int filas = -1;
        if (!conexion.isConexionExitosa()) {
            return filas;
        }
        String consulta = "delete from producto where ID_producto =" + codProducto;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        refrescarListaProductos();
        System.out.println("Filas borradas: " + filas);
        return filas;
    }

    /**
     * Metodo para actualizar la cantidad de un producto
     *
     * @param codProducto el codigo del producto al que se va a hacer la
     * actualizacion
     * @param cantidad la nueva cantidad con la que se va a sustituir la antigua
     * cantidad
     * @return retorna las filas actualizadas
     */
    public int setCantidadProducto(int codProducto, int cantidad) {
        if (cantidad == -1) {
            return -1;
        }
        String consulta = "update producto set cantidad = '" + cantidad + "' where ID_producto = " + codProducto;
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Productos actualizados: " + filas);
        refrescarListaProductos();
        return filas;
    }

    /**
     * Metodo para obtener un producto de la lista
     *
     * @param codProducto el codigo del producto que se va a buscar
     * @return retorna un producto tipo Producto, en caso contrario retorna null
     */
    public Producto getProducto(int codProducto){
        if (!conexion.isConexionExitosa()) {
            return null;
        }
        refrescarListaProductos();
        for (Producto producto : listaProductos) {
            if (producto.getCodProducto() == codProducto) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Metodo para modificar un producto con los parametros
     *
     * @param codProducto el codigo del producto que se va a actualizar
     * @param nombre el nuevo nombre del producto
     * @param precio el nuevo precio del producto
     * @param peso el nuevo peso del producto
     * @param cantidad la nueva cantidad del producto
     * @return retorna las filas actualizadas
     */
    public int modificarProducto(int codProducto, String nombre, double precio, double peso, int cantidad) {
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        int filas = 0;
        String consulta = "update producto set nombre = '" + nombre + "', peso = " + peso + ", precio =" + precio + ", cantidad = " + cantidad + " where ID_producto = " + codProducto;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        refrescarListaProductos();
        System.out.println("Productos actualizados: " + filas);
        return filas;
    }
    
     public boolean isExisteLaTabla() {
        refrescarListaProductos();
        return existeLaTabla;
    }
     
     public int borrarProductos(){
        String consulta = "delete from producto";
        return conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

}
