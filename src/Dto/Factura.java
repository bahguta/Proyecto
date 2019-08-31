/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;
import java.io.*;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Plam
 */
public class Factura implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Membros de la clase
     */
    
    //Codigo unico de la factura
    private int codFactura;
    //Fecha de la factura
    private Date fecha;
    //actividades realizados 
    private String trabajos;
    //lista con productos que contiene la factura
    private List<Producto> listaProductos;
    //el precio de la factura SIN IVA
    private double precio;

    //constructor
    public Factura(int codFactura, Date fecha, String trabajos, List<Producto> listaProductos, double precio) {
        this.codFactura = codFactura;
        this.fecha = fecha;
        this.trabajos = trabajos;
        this.listaProductos = listaProductos;
        this.precio = precio;
    }
    
    ////// Getter Setter //////

    public String getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(String trabajos) {
        this.trabajos = trabajos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodFactura() {
        return codFactura;
    }
    
    ////// Fin Getter Setter //////
    
}
