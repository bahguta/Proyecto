/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Persona;
import Dto.Usuario;
import Logica.ConexionBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 * Gestionar Personas
 *
 * @author Plam
 */
public class GestionarPersonas {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GestionarPersonas.class);

    private List<Persona> listaPersonas;
    private ConexionBBDD conexion;
    private boolean existeLaTabla = false;

    public GestionarPersonas(List<Persona> listaPersonas, ConexionBBDD conexion) {
        this.listaPersonas = listaPersonas;
        this.conexion = conexion;
    }

    public GestionarPersonas(ConexionBBDD conexion) {
        this.conexion = conexion;
        this.listaPersonas = new LinkedList<Persona>();
    }

    /**
     * Metodo para obtener una persona buscada por el nombre
     *
     * @param nombre el nombre de la persona que se va a buscar
     * @return retorna la persona tipo Persona en caso contrario retorna null
     */
    public Persona getPersonaPorNombre(String nombre) {
        if (!conexion.isConexionExitosa()) {
            return null;
        }
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        for (Persona persona : listaPersonas) {
            if (nombre.contains(persona.getNombre()) && nombre.contains(persona.getApellido())) {
                return persona;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener todas las personas
     *
     * @return retorna una lista tipo Persona
     */
    public List<Persona> getListaPersonas() {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        return listaPersonas;
    }

    /**
     * Metodo para obtener una persona buscada por el nombre
     *
     * @param ID_persona el ID de la persona que se va a buscar
     * @return retorna una persona tipo Persona , en caso contrario retorna null
     */
    public Persona getPersonaPorID(int ID_persona) {
        if (!conexion.isConexionExitosa()) {
            return null;
        }
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        for (Persona persona : listaPersonas) {
            if (persona.getCodPersona() == ID_persona) {
                return persona;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener una persona buscada por el codigo de factura
     *
     * @param ID_factura el codigo de la factura por la que se hara la busqueda
     * @return retorna una persona tipo Persona , en caso contrario retorna null
     */
    public Persona getPersonaPorIDFactura(int ID_factura) {
        if (!conexion.isConexionExitosa()) {
            return null;
        }
        String consulta = "select ID_persona from negocio where ID_factura = " + ID_factura;
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            if (resultado.next()) {
                for (Persona persona : listaPersonas) {
                    if (persona.getCodPersona() == resultado.getInt(1)) {
                        return persona;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para obtener una lista de personas cuyo tipo es CLIENTE
     *
     * @return return una lista tipo Persona
     */
    public List<Persona> getListaClientes() {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        List<Persona> lista = new ArrayList<>();
        for (Persona persona : listaPersonas) {
            if (persona.getTipo().equalsIgnoreCase("CLIENTE")) {
                lista.add(persona);
            }
        }
        return lista;
    }

    /**
     * Metodo para obtener una lista de personas cuyo tipo es PROVEEDOR
     *
     * @return return una lista tipo Persona
     */
    public List<Persona> getListaProveedores() {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        List<Persona> lista = new ArrayList<>();
        for (Persona persona : listaPersonas) {
            if (persona.getTipo().equalsIgnoreCase("PROVEEDOR")) {
                lista.add(persona);
            }
        }
        return lista;
    }

    /**
     * Metodo para borrar una persona segun el codigo de persona pasado como
     * parametro
     *
     * @param ID_persona el codigo de la persona que se va a borrar
     * @return retorna las filas actualizadas de la base de datos
     */
    public int borrarPersona(int ID_persona) {
        int filas = 0;
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        String consulta = "delete from persona where ID_PERSONA = " + ID_persona;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        return filas;
    }

    /**
     * Metodo para modificar los datos de una persona.
     *
     * @param p El parametro persona ya contiene los datos nuevos, hay que
     * sustituir estos datos con los datos de la persona con el mismo ID en la
     * base de datos
     *
     * @return retorna las filas actualizadas
     */
    public int modificarPersona(Persona p) {
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        String consulta = "update persona set nombre = '" + p.getNombre() + "', apellido = '" + p.getApellido() + "', telefono =" + p.getTelefono() + ", direccion = '" + p.getDireccion() + "', email = '" + p.getEmail() + "', type = '" + p.getTipo() + "' where ID_PERSONA = " + p.getCodPersona();
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        return filas;
    }

    /**
     * Metodo para refrescar la lista de las personas
     */
    private void refrescarListaPersonas() {
        if (!conexion.isConexionExitosa()) {
            return;
        }
        try {
            String consulta = "select * from persona";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado != null) {
                existeLaTabla = true;
                this.listaPersonas.clear();
                while (resultado.next()) {
                    this.listaPersonas.add(new Persona(
                            resultado.getInt(1),
                            resultado.getString(2),
                            resultado.getString(3),
                            resultado.getString(4),
                            resultado.getLong(5),
                            resultado.getString(6),
                            resultado.getString(7)));
                }
            } else {
                existeLaTabla = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para agregar una persona en la base de datos.
     *
     * @param nombre nombre de la persona
     * @param apellido apellidos de la persona
     * @param direccion su direccion
     * @param telefono su telefono
     * @param email su email
     * @param tipo que tipo es la persona Cliente o Proveedor
     * @return retorna las filas actualizadas
     */
    public int addPersona(String nombre, String apellido, String direccion, long telefono, String email, String tipo) {
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        int filas = 0;
        String consulta = "insert into persona (nombre, apellido, direccion, telefono, email, type) values ('" + nombre + "', '" + apellido + "', '" + direccion + "', " + telefono + ", '" + email + "', '" + tipo + "')";
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        refrescarListaPersonas();
        System.out.println("Personas insertadas: " + filas);

        return filas;
    }
    
    
    /**
     * Metodo para añadir un usuario en la BBDD
     * @param nombre
     * @param pass
     * @return 
     */
    public int addUsuario(String pass) {
        String consulta = "insert into usuario (pass) values ('" + pass +  "')";
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return filas;
    }
    
    public Usuario getUsuario(){
        Usuario u = null;
        try {
            
            String consulta = "select * from usuario";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado.next()) {
                try {
                    if (null != resultado.getString(1)) {
                        u = new Usuario(resultado.getString(1));
                    }
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return u;
    }
    
    public int cambiarPass(String pass){
        String consulta = "update persona set pass = '" + pass + "'";
        int filas = conexion.ejecutarStatementNOSELECT(pass, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        logger.info("Filas actualizadas en BBDD: " + filas);
        return filas;
    }
    
    public boolean isExisteLaTabla() {
        refrescarListaPersonas();
        return existeLaTabla;
    }
}
