/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestiones;

import Dto.NotaLibroDiario;
import Logica.ConexionBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Gestionar Notas del Libro Diario
 * @author Plam
 */
public class GestionarNotasLibro {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GestionarNotasLibro.class);

    private List<NotaLibroDiario> listaNotas;
    private ConexionBBDD conexion;
    private SimpleDateFormat sdf;
    private boolean existeLaTabla;

    /**
     * Constructor
     * @param conexion la conexion a la base de datos para hacer las consultas
     */
    public GestionarNotasLibro(ConexionBBDD conexion) {
        this.conexion = conexion;
        this.listaNotas = new ArrayList<>();
        this.sdf = new SimpleDateFormat("yyyy/MM/dd");
    }

    /**
     * Metodo para obtener una nota de la lista 
     * @param codNoda el codigo de la nota que se va a buscar
     * @return returna la nota tipo NotaLibroDiario
     */
    public NotaLibroDiario getNota(int codNoda){
        if (!conexion.isConexionExitosa()) {
            return null;
        }
        refrescarListaNotas();
        for (int i = 0; i < listaNotas.size(); i++) {
            if (listaNotas.get(i).getCodNota() == codNoda) {
                return listaNotas.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo para borrar una nota de la base de datos
     * @param codNota el codigo de la nota que se va a borar 
     * @return retorna las filas actualizadas 
     */
    public int borrarNota(int codNota){
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        int filas = 0;
        String consulta = "delete from nota where ID_nota = " + codNota;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Notas borradas: " + filas);
        refrescarListaNotas();
        return filas;
    }

    /**
     * Metodo para crear una nota del libro diario
     * 
     * @param fecha la fecha de la nota
     * @param debe lo que se debe
     * @param haber lo que se gana
     * @param detalle los detalles sobre la nota
     * @return  retorna las filas actualizadas 
     */
    public int addNota(Date fecha, Double debe, Double haber, String detalle) {
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        int filas = 0;
        String consulta = "insert into nota (fecha, detalle, debe, haber) values ('" + sdf.format(fecha) + "','" + detalle + "', " + debe + ", " + haber + ")";
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Notas Insertadas: " + filas);
        System.out.println(sdf.format(fecha));
        refrescarListaNotas();
        conexion.ejecutarStatementNOSELECT("commit", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return filas;
    }

    /**
     * Metodo para modificar una nota del libro diario
     * @param ID_nota el codigo de la nota que se va a modificar
     * @param detalle los nuevos detalles sobre la nota
     * @param debe lo que debe
     * @param haber lo que gana
     * @return  retorna las filas actualizadas
     */
    public int moidifcarNota(int ID_nota, String detalle, Double debe, Double haber) {
        if (!conexion.isConexionExitosa()) {
            return -1;
        }
        int filas = 0;
        String consulta = "update nota set  detalle = '" + detalle + "', debe = " + debe + ", haber = " + haber + " where id_nota = " + ID_nota;
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Notas modificadas: " + filas);
        refrescarListaNotas();
        return filas;
    }

    /**
     * Metodo para obtener una lista con las notas del libro diario
     * @return  retorna una lista tipo NotaLibroDiario
     */
    public List<NotaLibroDiario> getListaNotas() {
        if (!conexion.isConexionExitosa()) {
            return new ArrayList<>();
        }
        refrescarListaNotas();
        return listaNotas;
    }

    /**
     * Metodo para refrescar la lista de las notas
     */
    public void refrescarListaNotas() {
        if (!conexion.isConexionExitosa()) {
            return ;
        }
        String consulta = "select * from nota";
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            existeLaTabla = true;
            try {
                //creo la lista si no esta inicializada 
                if (listaNotas == null) {
                    listaNotas = new ArrayList<>();
                }
                //vacio la lista para obtener resultados actuales 
                listaNotas.clear();
                while (resultado.next()) {
                    listaNotas.add(new NotaLibroDiario(
                            resultado.getInt(1),
                            resultado.getDate(2),
                            resultado.getString(3),
                            resultado.getDouble(4),
                            resultado.getDouble(5)));
                }
            } catch (SQLException ex) {
            logger.info(ex.getMessage(), ex);
            }
        } else {
            existeLaTabla = false;
        }

    }

    /**
     * Metodo para obtener la fecha mas antigua entre las notas del libro diario
     * @return  retorna la fecha tipo Date
     */
    public Date getFechaIn() {
        if (!conexion.isConexionExitosa()) {
            return new Date();
        }
        refrescarListaNotas();
        Date fechaIn = null;
        if (listaNotas != null && !listaNotas.isEmpty()) {
            fechaIn = listaNotas.get(0).getFecha();
            for (NotaLibroDiario nota : listaNotas) {
                if (fechaIn.after(nota.getFecha()) || fechaIn.compareTo(nota.getFecha()) == 0) {
                    fechaIn = nota.getFecha();
                }
            }
        }
        return fechaIn;
    }

    /**
     * Metodo para obtener la fecha mas reciente entre las notas del libro diario
     * @return  retorna la fecha tipo Date
     */
    public Date getFechaFin() {
        if (!conexion.isConexionExitosa()) {
            return new Date();
        }
        Date fechaFin = null;
        if (listaNotas != null) {
            fechaFin = listaNotas.get(0).getFecha();
            for (NotaLibroDiario nota : listaNotas) {
                if (fechaFin.before(nota.getFecha()) || fechaFin.compareTo(nota.getFecha()) == 0) {
                    fechaFin = nota.getFecha();
                }
            }
        }
        return fechaFin;
    }

    public boolean isExisteLaTabla() {
        refrescarListaNotas();
        return existeLaTabla;
    }
    
    public int borrarNotas(){
        String consulta = "delete from nota";
        return conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
}
