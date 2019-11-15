/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author bahguta
 */
public class Negocio implements java.io.Serializable{
    
    private int ID_factura;
    private int ID_persona;
    private int ID_producto;
    private int cantidad;

    public Negocio(int ID_factura, int ID_persona, int ID_producto, int cantidad) {
        this.ID_factura = ID_factura;
        this.ID_persona = ID_persona;
        this.ID_producto = ID_producto;
        this.cantidad = cantidad;
    }

    public int getID_factura() {
        return ID_factura;
    }

    public void setID_factura(int ID_factura) {
        this.ID_factura = ID_factura;
    }

    public int getID_persona() {
        return ID_persona;
    }

    public void setID_persona(int ID_persona) {
        this.ID_persona = ID_persona;
    }

    public int getID_producto() {
        return ID_producto;
    }

    public void setID_producto(int ID_producto) {
        this.ID_producto = ID_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Negocio{" + "ID_factura=" + ID_factura + ", ID_persona=" + ID_persona + ", ID_producto=" + ID_producto + ", cantidad=" + cantidad + '}';
    }
    
    
    
}
