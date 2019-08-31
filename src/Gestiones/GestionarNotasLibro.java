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
import org.openide.util.Exceptions;

/**
 *
 * @author Plam
 */
public class GestionarNotasLibro {

    private List<NotaLibroDiario> listaNotas;
    private ConexionBBDD conexion;
    private SimpleDateFormat sdf;

    /**
     * Constructor
     * @param conexion la conexion a la base de datos para hacer las consultas
     */
    public GestionarNotasLibro(ConexionBBDD conexion) {
        this.conexion = conexion;
        this.listaNotas = new ArrayList<>();
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * Metodo para obtener una nota de la lista 
     * @param codNoda el codigo de la nota que se va a buscar
     * @return returna la nota tipo NotaLibroDiario
     */
    public NotaLibroDiario getNota(int codNoda){
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
        int filas = 0;
        String consulta = "insert into nota (ID_nota, fecha, detalle, debe, haber) values ( ID_NOTA_SEQ.nextVal , TO_DATE('" + sdf.format(fecha) + "'),'" + detalle + "', " + debe + ", " + haber + ")";
        filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Notas Insertadas: " + filas);
        refrescarListaNotas();
        return filas;
    }

    /**
     * Metodo para modificar una nota del libro diario
     * @param ID_nota el codigo de la nota que se va a modificar
     * @param fecha la nueva fecha
     * @param detalle los nuevos detalles sobre la nota
     * @param debe lo que debe
     * @param haber lo que gana
     * @return  retorna las filas actualizadas
     */
    public int moidifcarNota(int ID_nota, Date fecha, String detalle, Double debe, Double haber) {
        int filas = 0;
        String consulta = "update nota set fecha = TO_DATE('" + sdf.format(fecha) + "'),  detalle = '" + detalle + "', debe = " + debe + ", haber = " + haber + " where id_nota = " + ID_nota;
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
        refrescarListaNotas();
        return listaNotas;
    }

    /**
     * Metodo para refrescar la lista de las notas
     */
    private void refrescarListaNotas() {
        String consulta = "Select * from nota";
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            try {
                if (listaNotas == null) {
                    listaNotas = new ArrayList<>();
                }
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
                Exceptions.printStackTrace(ex);
            }
        }

    }

    /**
     * Metodo para obtener la fecha mas antigua entre las notas del libro diario
     * @return  retorna la fecha tipo Date
     */
    public Date getFechaIn() {
        refrescarListaNotas();
        Date fechaIn = null;
        if (listaNotas != null) {
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

}
