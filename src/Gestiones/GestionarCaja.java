/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.NotaLibroDiario;
import Dto.Persona;
import Dto.Producto;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Gestionar Caja
 *
 * @author Plam
 */
public class GestionarCaja {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GestionarCaja.class);

    private double CAJA;

    public GestionarCaja() {
        this.CAJA = 0;
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
    public boolean ventaCompra(JFrame frame, Persona p, List<Producto> lista, double precio, GestionarInventario g) {
        System.out.println(CAJA);
        
        
        if (p.getTipo().equalsIgnoreCase("CLIENTE")) {
            //venta
            for (Producto producto : lista) {
                int cantidad = g.getProducto(producto.getCodProducto()).getCantidad() - producto.getCantidad();
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(frame, "Insuficiente Producto en stock.");
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
                    JOptionPane.showMessageDialog(frame, "Insuficiente Dinero.");
                    return false;
                }
               // restaCAJA(total);
                //CAJA -= precio;
                g.modificarProducto(producto.getCodProducto(), producto.getNombre(), producto.getPrecio(), producto.getPeso(), cantidad);

            }
        }
        return true;
    }
    
    public double actualizarCaja(GestionarNotasLibro gLbro){
        
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
    
    public void restaCAJA(double valor){
        this.CAJA -= valor;
    }

    public  void setCAJA(double valor) {
        this.CAJA = valor;
    }

   
    

}
