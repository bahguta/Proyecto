/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author Plam
 */
public class Usuario {
    
    private String nombre;
    private String pass;
//    private List<Persona> listaPersonas;
//    private List<NotaLibroDiario> listaNotasLibroDiario;

    public Usuario(String nombre, String pass) {
//        this.listaPersonas = listaPersonas;
//        this.listaNotasLibroDiario = listaNotasLibroDiario;
        this.nombre = nombre;
        this.pass = pass;
    }

//    public Usuario() {
//        listaPersonas = new LinkedList<>();
//        listaNotasLibroDiario = new LinkedList<>();
//        this.nombre = nombre;
//        this.pass = pass;
//    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
}
