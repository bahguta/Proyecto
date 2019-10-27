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
    
    private int ID_usuario;
    private String nombre;
    private String pass;
    private String passCripted;
    private String salt;
    private String userRole;

    public Usuario(int ID_usuario, String nombre, String pass, String passCripted, String salt, String userRole) {
        this.ID_usuario = ID_usuario;
        this.nombre = nombre;
        this.pass = pass;
        this.passCripted = passCripted;
        this.salt = salt;
        this.userRole = userRole;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassCripted() {
        return passCripted;
    }

    public void setPassCripted(String passCripted) {
        this.passCripted = passCripted;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Usuario{" + "ID_usuario=" + ID_usuario + ", nombre=" + nombre + ", pass=" + pass + ", passCripted=" + passCripted + ", salt=" + salt + ", userRole=" + userRole + '}';
    }

    
}
