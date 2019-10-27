/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Date;

/**
 * Objeto Nota del libro diario
 * 
 * @author Plam
 */
public class NotaLibroDiario implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int codNota;
    private Date fecha;
    private String detalle;
    private double debe;
    private double haber;

    public NotaLibroDiario(int codNota, Date fecha, String detalle, double debe, double haber) {
        this.codNota = codNota;
        this.fecha = fecha;
        this.detalle = detalle;
        this.debe = debe;
        this.haber = haber;
    }

    public NotaLibroDiario() {
        fecha = new Date();
        detalle = "";
        debe = 0d;
        haber = 0d;
    }

    public int getCodNota() {
        return codNota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    @Override
    public String toString() {
        return "NotaLibroDiario{" + "codNota=" + codNota + ", fecha=" + fecha + ", detalle=" + detalle + ", debe=" + debe + ", haber=" + haber + '}';
    }
    
    
    
}
