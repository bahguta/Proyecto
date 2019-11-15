/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Negocio;
import Dto.NotaLibroDiario;
import Dto.Persona;
import Dto.Producto;
import Logica.ConexionBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Gestionar Caja
 *
 * @author Plam
 */
public class GestionarCaja {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GestionarCaja.class);

    private ConexionBBDD conexion;
    private double CAJA;
    private List<Negocio> listaNegocio;

    public GestionarCaja(ConexionBBDD conexion) {
        this.CAJA = 0;
        this.conexion = conexion;
        this.listaNegocio = new ArrayList<>();
    }

    /**
     * Metodo para gestionar la venta y la compra de la empresa.<br>
     * Si la persona es un cliente se produce una venta y si es un proveedor<br>
     * se produce una compra
     *
     * @param frame frame para poder mostrar Dialog Box
     * @param p persona la que compra o a la que se vende
     * @param lista lista de productos en caso de venta
     * @param precio el precio de la venta/compra
     * @param g gestionarInventario para poder restar o sumar segun si es compra
     * o venta
     * @return retorna un booleano , true en caso de que se ha echo
     * correctamente la venta/compra y false en caso contrario
     */
    public boolean ventaCompra(Persona p, List<Producto> lista, double precio, GestionarInventario g) {
        System.out.println(CAJA);

        if (p.getTipo().equalsIgnoreCase("CLIENTE")) {
            //venta
            for (Producto producto : lista) {
                int cantidad = g.getProducto(producto.getCodProducto()).getCantidad() - producto.getCantidad();
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(null, "Insuficiente Producto en stock.");
                    return false;
                }
                //sumaCAJA(precio);

                g.modificarProducto(producto.getCodProducto(), producto.getNombre(), producto.getPrecio(), producto.getPeso(), cantidad);
                //g.getProducto(producto.getCodProducto()).setCantidad(cantidad);
            }
        } else {
            //compra
            for (Producto producto : lista) {
                double total = 0;
                int cantidad = g.getProducto(producto.getCodProducto()).getCantidad() + producto.getCantidad();
                total = CAJA - precio;
                if (total < 0) {
                    JOptionPane.showMessageDialog(null, "Insuficiente Dinero.");
                    return false;
                }
                // restaCAJA(total);
                //CAJA -= precio;
                g.modificarProducto(producto.getCodProducto(), producto.getNombre(), producto.getPrecio(), producto.getPeso(), cantidad);

            }
        }
        return true;
    }

    public double actualizarCaja(GestionarNotasLibro gLbro) {

        double cobros = 0;
        double gastos = 0;

        for (NotaLibroDiario nota : gLbro.getListaNotas()) {
            cobros += nota.getHaber();
            gastos += nota.getDebe();
        }

        CAJA = cobros - gastos;

        return cobros - gastos;

    }

    public void sumaCAJA(double valor) {
        this.CAJA += valor;
    }

    public void restaCAJA(double valor) {
        this.CAJA -= valor;
    }

    public void setCAJA(double valor) {
        this.CAJA = valor;
    }

    public void refrescarNegocio(){
        try {
            String consulta = "select * from negocio";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            while (resultado.next()) {
                //compruebo si hay registros
                //if (resultado.first()) {
                    //limpio la lista para añadir los nuevos registros
                    this.listaNegocio.clear();
                    try {
                        this.listaNegocio.add(new Negocio(resultado.getInt(1), resultado.getInt(2), resultado.getInt(3), resultado.getInt(4)));
                    } catch (SQLException ex) {
                        logger.info("error con la consulta", ex);
                    }
               // }
                
            }
        } catch (SQLException ex) {
            logger.info("error en la consulta ", ex);
        }
    }

    public List<Negocio> getListaNegocio() {
        refrescarNegocio();
        return listaNegocio;
    }

    

    public int borrarNegocio() {
        String consulta = "delete from negocio";
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        logger.info("borradas " + filas + " filas");
        return filas;
    }

    public int addNegocio(int val0, int val1, int val2, int val3) {
        String consulta = "insert into negocio (ID_factura, ID_persona, ID_producto, cantidad) values (" + val0 + ", " + val1 + ", " + val2 + ", " + val3 + ")";
        logger.info("Fila insertada en Negocio: ");
        logger.info("Factura: " + val0 + "\nID_persona: " + val1 + "\nID_producto: " + val2 + "\ncantidad: " + val3);
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        conexion.ejecutarStatementNOSELECT("commit", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return filas;
    }
    
    public int getIDPersonaPorFactura(int id_factura){
        int id_persona = -1;
        id_persona = this.listaNegocio.stream().filter( n -> n.getID_factura() == id_factura).findFirst().get().getID_persona();
        return id_persona;
    }
}
