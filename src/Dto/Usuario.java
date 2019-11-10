/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 * Objeto Usuario, usado para controlar las cuentas del programa
 * 
 * @author Plam
 */
public class Usuario implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    
    private String pass;
    private boolean admin; 


    public Usuario(String pass) {
        this.pass = pass;
        this.admin = false;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Usuario{" + "pass=" + pass + ", admin=" + admin + '}';
    }

    
}
