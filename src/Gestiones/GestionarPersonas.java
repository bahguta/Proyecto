/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.Persona;
import Logica.ConexionBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Gestionar Personas
 * 
 * @author Plam
 */
public class GestionarPersonas {
    private static final Logger LOG = Logger.getLogger(GestionarPersonas.class.getName());

    private List<Persona> listaPersonas;
    private ConexionBBDD conexion;
    //private GestionarFacturas gestionarFacturas;

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
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        for (Persona persona : listaPersonas) {
            if (nombre.contains(persona.getNombre()) && nombre.contains(persona.getApellido())) {
                System.out.println("Persona enconrada: " + persona.toString());
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
     * @return return una lista tipo Persona
     */
    public List<Persona> getListaClientes() {
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
     * @return return una lista tipo Persona
     */
    public List<Persona> getListaProveedores(){
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
     * Metodo para borrar una persona segun el codigo de persona pasado como parametro
     * @param ID_persona el codigo de la persona que se va a borrar
     * @return retorna las filas actualizadas de la base de datos
     */
    public int borrarPersona(int ID_persona) {
        int filas = 0;
        String consulta = "delete from persona where ID_PERSONA = " + ID_persona;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //refresco la lista para que actualize los objetos por si hubo cambios
        refrescarListaPersonas();
        return filas;
    }

    /**
     * Metodo para modificar los datos de una persona. 
     * 
     * @param p El parametro persona ya contiene los datos nuevos, 
     * hay que sustituir estos datos con los datos de la persona con el mismo ID en la base de datos
     * 
     * @return retorna las filas actualizadas 
     */
    public int modificarPersona(Persona p) {
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
        try {
            String consulta = "select * from persona";
            ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (resultado != null) {
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
     * @return  retorna las filas actualizadas
     */
    public int addPersona(String nombre, String apellido, String direccion, long telefono, String email, String tipo) {
        int filas = 0;
//        try {
            int ID_Persona = -1;
//            String consultaNextID = "SELECT max(ID_Persona) FROM persona";
//            ResultSet resultado = conexion.ejecutarStatementSELECT(consultaNextID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            if (resultado.next()) {
//                ID_Persona = resultado.getInt(1);
//            }

            String consulta = "insert into persona (nombre, apellido, direccion, telefono, email, type) values ('" + nombre + "', '" + apellido + "', '" + direccion + "', " + telefono + ", '" + email + "', '" + tipo + "')";

            filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            refrescarListaPersonas();
            System.out.println("Personas insertadas: " + filas);
//        } catch (SQLException e) {
//            System.out.println("Error en la consulta ...");
//        }
        return filas;
    }
}
