/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.*;

/**
 *
 * @author Plam
 */
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int codProducto;
    private String nombre; 
    private double precio;
    private double peso;
    private int cantidad;

    public Producto(int codProducto, String nombre, double precio, double peso, int cantidad) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCodProducto() {
        return codProducto;
    }
}
