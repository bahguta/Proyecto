/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.logging.Logger;

/**
 *
 * @author Plam
 */
public class Persona {
    
    private static final long serialVersionUID = 1L;
    
    private int cod;
    private String nombre;
    private String apellido;
    private String direccion;
    private long telefono;
    private String email;
    private String tipo;

    public Persona(int cod, String nombre, String apellido, String direccion, long telefono, String email, String tipo) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getCodPersona() {
        return cod;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Persona{" + "cod=" + cod + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + ", tipo=" + tipo + '}';
    }
    private static final Logger LOG = Logger.getLogger(Persona.class.getName());
    
}
