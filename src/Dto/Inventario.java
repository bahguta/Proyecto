/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.*;
import java.util.List;

/**
 *
 * @author Plam
 */
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Producto> listaProductos;

    public Inventario(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public boolean addProducto(Producto p) {
        return listaProductos.add(p);
    }

    public boolean delProducto(int codProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getCodProducto() == codProducto) {
                return listaProductos.remove(producto);
            }
        }
        return false;
    }

    public Producto getProducto(int codProducto) throws NullPointerException {
        for (Producto producto : listaProductos) {
            if (producto.getCodProducto() == codProducto) {
                return producto;
            }
        }
        return null;
    }

}
