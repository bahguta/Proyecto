/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Persona;
import Dto.Producto;
import Logica.LogicaNegocio;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Plam
 */
public class GestionarCaja {

    public static double CAJA = 0d;

    /**
     * Metodo para gestionar la venta y la compra de la empresa.
     *
     * @param frame frame para poder mostrar Dialog Box
     * @param logica logica para gestionar los datos
     * @param p persona la que compra o a la que se vende
     * @param lista lista de productos en caso de venta
     * @param precio el precio de la venta/compra
     * @param g gestionarInventario para poder restar o sumar segun si es compra
     * o venta
     * @return retorna un booleano , true en caso de que se ha echo
     * correctamente la venta/compra y false en caso contrario
     */
    public static boolean ventaCompra(JFrame frame, LogicaNegocio logica, Persona p, List<Producto> lista, double precio, GestionarInventario g) {
        if (p.getTipo().equals("CLIENTE")) {
            //venta
            for (Producto producto : lista) {
                int cantidad = g.getProducto(producto.getCodProducto()).getCantidad() - producto.getCantidad();
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(frame, "Insuficiente Producto en stock.");
                    return false;
                }
                CAJA += precio;
                g.modificarProducto(producto.getCodProducto(), producto.getNombre(), producto.getPrecio(), producto.getPeso(), cantidad);
                g.getProducto(producto.getCodProducto()).setCantidad(cantidad);
            }
        } else {
            //compra
            for (Producto producto : lista) {
                int cantidad = g.getProducto(producto.getCodProducto()).getCantidad() + producto.getCantidad();
                double total = CAJA - precio;
                if (CAJA < 0) {
                    JOptionPane.showMessageDialog(frame, "Insuficiente Dinero.");
                    return false;
                }
                CAJA -= precio;
                g.modificarProducto(producto.getCodProducto(), producto.getNombre(), producto.getPrecio(), producto.getPeso(), cantidad);

            }
        }
        return true;
    }

}
