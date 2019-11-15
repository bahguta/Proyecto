/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Objeto Factura 
 * 
 * @author Plam
 */
public class Factura implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Factura.class.getName());
    
    /**
     * Membros de la clase
     */
    
    private int codFactura;
    private Date fecha;
    private String trabajos;
    private List<Producto> listaProductos;
    private double precio;

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

    @Override
    public String toString() {
        return "Factura{" + "codFactura=" + codFactura + ", fecha=" + fecha + ", trabajos=" + trabajos + ", listaProductos=" + listaProductos + ", precio=" + precio + '}';
    }
    
    
    
    
}
