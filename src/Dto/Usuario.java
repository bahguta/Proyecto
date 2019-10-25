/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.logging.Logger;

/**
 * Objeto Usuario, usado para controlar las cuentas del programa
 * 
 * @author Plam
 */
public class Usuario implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());
    
    private String nombre;
    private String pass;


    public Usuario(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }


    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", pass=" + pass + '}';
    }
    
}
