/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author plam
 */
public class ConexionBBDD {

    private Connection conexion = null;
    private Statement stmtSQL = null;
    private PreparedStatement pstmtSQL = null;
    private String user;
    private String host;
    private String pass;
    private int puerto;
    private String bdName;
    private boolean isConnected = false;

    //abre una conexion a la base de datos con MySql
    public boolean conexionBBDDMySql(String nomEqui, int puerto, String nomBBDD, String usr, String pwd) {
        host = nomEqui;
        user = usr;
        pass = pwd;
        this.puerto = puerto;
        bdName = nomBBDD;

        System.out.println("------------------------");
        System.out.println("Conectando a la BBDD ...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Error con el driver,comprueba que lo has metido en el proyecto y que es el correcto");
            return false;
        }

        //defino la url que se usará para la conexion; 1521 para oracle
        //jdbc:mysql://localhost:3306/karthicraj","mysql","mysql"
        String url = "jdbc:mysql://" + nomEqui + ":" + puerto + "/" + nomBBDD;
        System.out.println(url);
        try {
            //creo la conexion
            conexion = DriverManager.getConnection(url, usr, pwd);
            if (conexion != null) {
                System.out.println("------------------------------");
                System.out.println("Conexión establecida con exito");
                isConnected = true;
                return true;
            }
        } catch (SQLException ex) {
            //System.out.println("Fallo en la conexion; verificar BBDD, nombre equipo, usuario o contraseña");
            System.out.println("Error al conectar con la BBDD, compruebe si está levantada");
            //System.exit(1);
            return false;
        }
        return false;

    }

    public boolean isConexionExitosa() {
        return isConnected;
    }

    public void conexionBBDD(Object o) {
        if (o == null) {
            isConnected = false;
        }
    }

    //abre una conexion a la base de datos con Oracle
    public boolean conexionBBDD(String nomEqui, int puerto, String nomBBDD, String usr, String pwd) {
        host = nomEqui;
        user = usr;
        pass = pwd;
        this.puerto = puerto;
        bdName = nomBBDD;

        System.out.println("------------------------");
        System.out.println("Conectando a la BBDD ...");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Error con el driver,comprueba que lo has metido en el proyecto y que es el correcto");
            return false;
        }

        //defino la url que se usará para la conexion; 1521 para oracle
        String url = "jdbc:oracle:thin:@" + nomEqui + ":" + puerto + ":" + nomBBDD;
        System.out.println(url);
        try {
            //creo la conexion
            conexion = DriverManager.getConnection(url, usr, pwd);
            if (conexion != null) {
                System.out.println("------------------------------");
                System.out.println("Conexión establecida con exito");
                isConnected = true;
                return true;
            }
        } catch (SQLException ex) {
            //System.out.println("Fallo en la conexion; verificar BBDD, nombre equipo, usuario o contraseña");
            System.out.println("Error al conectar con la BBDD, compruebe si está levantada");
            //System.exit(1);
            return false;
        }
        return false;

    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getBBDDName() {
        return bdName;
    }

    //cierra una conexion de la BBDD
    public void cerrarConexion() {
        try {
            if (stmtSQL != null) {
                stmtSQL.close();
            }
            if (pstmtSQL != null) {
                pstmtSQL.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(MetodosBBDD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cerrar la conexion a la BBDD");
        } finally {
            isConnected = false;
        }
    }

    //metodo para ejecutar una Statement SELECT
    public ResultSet ejecutarStatementSELECT(String sql, int tipoResultado, int tipoActualizacion) {
        if (!isConnected) {
            return null;
        }
        ResultSet juegoResultados = null;
        try {
            // Para crear un Statement
            if (conexion == null) {
                System.out.println("conexion null");
            } else {
                stmtSQL = conexion.createStatement();
                juegoResultados = stmtSQL.executeQuery(sql);
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta Statement, comprobar la SQL1");

        }
        return juegoResultados;
    }

    //metodo para ejecutar una Statement UPDATE, INSERT, UPDATE
    public int ejecutarStatementNOSELECT(String sql, int tipoResultado, int tipoActualizacion) {
        if (!isConnected) {
            return -1;
        }
        int numFilasResultadoConsulta = 0;
        try {
            // Para crear un Statement
            stmtSQL = conexion.createStatement(tipoResultado, tipoActualizacion);
            numFilasResultadoConsulta = stmtSQL.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error en la consulta Statement, comprobar la SQL2");
        }
        return numFilasResultadoConsulta;
    }

    //metodo para preparar una preparedStatement que se ejecutara en el metodo 
    // ejecutarPreparedStatementSelect o ejecutarPreparedStatementNoSelect
    //carga en el atributo pstmt el prepare statement
    public void crearPreparedStatement(String sql, int tipoResultado, int tipoActualizacion) {
        if (!isConnected) {
            return;
        }
        try {
            // Para crear un PreparedStatement para ejecutar posteriormente
            if (conexion == null) {
                System.out.println("conexion null");
            } else {
                pstmtSQL = conexion.prepareStatement(sql, tipoResultado, tipoActualizacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta Statement, comprobar la SQL3");

        }

    }

    //metodo para ejecutar una PreparedStatement SELECT
    //usando la preparedStatement almacenada en el atributo pstmt
    public ResultSet ejecutarPreparedStatementSELECT() {
        if (!isConnected) {
            return null;
        }
        ResultSet resultadoConsulta = null;
        try {
            resultadoConsulta = pstmtSQL.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error en la consulta Statement, comprobar la SQL4");
        }
        return resultadoConsulta;
    }

    //metodo para ejecutar una PreparedStatement UPDATE, INSERT, UPDATE
    //usando la preparedStatement almacenada en el atributo pstmt
    public int ejecutarPreparedStatementNOSELECT() {
        if (!isConnected) {
            return -1;
        }
        int numFilasAfectadasSQL = 0;
        try {
            numFilasAfectadasSQL = pstmtSQL.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en la consulta Statement, comprobar la SQL5");
        }
        return numFilasAfectadasSQL;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getStmtSQL() {
        return stmtSQL;
    }

    public void setStmtSQL(Statement stmtSQL) {
        this.stmtSQL = stmtSQL;
    }

    public PreparedStatement getPstmtSQL() {
        return pstmtSQL;
    }

    public void setPstmtSQL(PreparedStatement pstmtSQL) {
        this.pstmtSQL = pstmtSQL;
    }

}
