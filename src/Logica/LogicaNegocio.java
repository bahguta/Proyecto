/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dto.*;
import GUI.Main.Main;
import Gestiones.*;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Logica de Negocio.<br> Es el controlador del modelo MVC , es el responsable
 * de hacer toda la logica del programa<br>
 * Recoge las peticiones de la vista y manda consultas hacia las gestiones , las
 * que se conectan a la base de datos .
 *
 * @author Plam
 */
public class LogicaNegocio {

    private static final Logger LOG = Logger.getLogger(LogicaNegocio.class.getName());

    // gestiones 
    private ConexionBBDD conexion;
    private GestionarInventario gestionarInventario;
    private GestionarPersonas gestionarPersonas;
    private GestionarFacturas gestionarFacturas;
    private GestionarNotasLibro gestionarNotasLibro;
    private GestionarCaja gestionarCaja;
    
    //ruta para los informes que va a crear
    private File rutaInformes;
    //comprueba si esta establecida la conexion con la base de datos 
    //devuelve true si existe una conexion con base de datos y false en caso contrario
    private static boolean isConexion = false;

    /**
     * Constructor Datos necesarios para la conexion a la base de datos
     *
     * @param usuario
     * @param password
     * @param IP
     * @param puerto
     * @param nombreBBDD
     */
    public LogicaNegocio(JFrame frame, String usuario, String password, String IP, int puerto, String nombreBBDD) {
        //creo la clase conexion
        this.conexion = new ConexionBBDD();
        //inicializo la conexion 
        //this.conexion.conexionBBDD(IP, puerto, nombreBBDD, usuario, password);
        
        
        //creo las clases de gestionar 
        this.gestionarPersonas = new GestionarPersonas(conexion);
        this.gestionarFacturas = new GestionarFacturas(conexion);
        this.gestionarInventario = new GestionarInventario(conexion);
        this.gestionarNotasLibro = new GestionarNotasLibro(conexion);
        this.gestionarCaja = new GestionarCaja();

        actualizarCaja();

        this.rutaInformes = new File("informes");
        if (rutaInformes.mkdir()) {
            LOG.log(Level.INFO, "Carpeta informes creada con exito");
        } else if (rutaInformes.exists()) {
            LOG.log(Level.INFO, "La carpeta Informes existe !!! No se crea !");
        } else {
            JOptionPane.showMessageDialog(frame, "Carpeta" + rutaInformes.getPath() + " NO creada\nRevisa los persmisos del programa.");

        }
    }
    
    public boolean conectBBDD(String usuario, String password, String IP, int puerto, String nombreBBDD){
        if(this.conexion.conexionBBDDMySql(IP, puerto, nombreBBDD, usuario, password)){
            LogicaNegocio.isConexion = true;
            return true;
        }
        return false; 
    }

    public static boolean isIsConexion() {
        return LogicaNegocio.isConexion;
    }

    public static void setIsConexion(boolean isConexion) {
        LogicaNegocio.isConexion = isConexion;
    }

    
    
    public LogicaNegocio() {
        this.conexion = null;

    }

    /**
     *
     *
     *
     *
     * Gestionar Conexion BBDD
     *
     *
     *
     *
     */
    /**
     * Metodo para cambiar la conexion de la base de datos
     *
     * @param usuario
     * @param password
     * @param IP
     * @param puerto
     * @param nombreBBDD
     * @return
     */
    public boolean cambiarConexion(String usuario, String password, String IP, int puerto, String nombreBBDD) {
        ConexionBBDD conn = new ConexionBBDD();
        if (conn.conexionBBDD(IP, puerto, nombreBBDD, usuario, password)) {
            conexion = conn;
            return true;
        }
        return false;
    }

    /**
     *
     *
     *
     *
     * Gestionar Usuarios
     *
     *
     *
     *
     */
    /**
     * Metodo para añadir un usuario
     *
     * @param nombre
     * @param pass
     * @param userRole
     * @return
     */
    public int addUsuario(String nombre, String pass, String userRole) {
        String consulta = "insert into usuario (userID, nombre, pass, userRole) values ("
                + "ID_usuario_seq.nextVal,"
                + "'" + nombre + "', "
                + "'" + pass + "',"
                + "'" + userRole + "')";
        int filas = conexion.ejecutarStatementNOSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return filas;
    }

    /**
     * Metodo para obtener un usuario
     *
     * @param nombre
     * @param pass
     * @return
     */
    public Usuario getUsuario(String nombre, String pass) {
        String consulta = "Select * from usuario where nombre = '" + nombre + "' and pass = '" + pass + "'";
        ResultSet resultado = conexion.ejecutarStatementSELECT(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    Usuario u = new Usuario(resultado.getString("Nombre"), resultado.getString("Pass"));
                    return u;
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return null;
    }

    /**
     *
     *
     *
     *
     * ComboBox Models
     *
     *
     *
     *
     */
    /**
     * Metodo para obtener el vector con las fechas de todas las notas del libro
     * diario
     *
     * @return retorna el vector tipo String con las fechas tipo String
     */
    public DefaultComboBoxModel<String> getFechasComboboxModel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DefaultComboBoxModel<String> listaFechas = new DefaultComboBoxModel<>();
        gestionarNotasLibro.getListaNotas().forEach((nota) -> {
            listaFechas.addElement(sdf.format(nota.getFecha()));
        });
        return listaFechas;
    }

    /**
     * Metodo para obtener el vector con las fechas ( INICIO - FIN) de las notas
     * del libro diario
     *
     * @return retorna el vector tipo String con las fechas
     */
    public DefaultComboBoxModel<String> getFechasComboboxModel(Date fechaInicio, Date fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DefaultComboBoxModel<String> listaFechas = new DefaultComboBoxModel<>();
        gestionarNotasLibro.getListaNotas().stream().filter((nota) -> (nota.getFecha().equals(fechaInicio)
                && nota.getFecha().after(fechaInicio)
                && nota.getFecha().before(fechaFin)
                && nota.getFecha().equals(fechaFin))).forEachOrdered((nota) -> {
            listaFechas.addElement(sdf.format(nota.getFecha()));
        });
        return listaFechas;
    }

    /**
     * Metodo para obtener el vector con los nombres de todos los proveedores
     *
     * @return retorna el vector tipo String con los nombres de todos los
     * proveedores
     */
    public DefaultComboBoxModel<String> getProveedoresComboBoxModel() {
        DefaultComboBoxModel<String> listaNombres = new DefaultComboBoxModel<>();
        List<Persona> lista = new ArrayList<>();
        getListaPersonas().stream().filter((persona) -> (persona.getTipo().equals("PROVEEDOR"))).forEachOrdered((persona) -> {
            listaNombres.addElement(persona + " " + persona.getApellido());
        });
        return listaNombres;
    }

    /**
     * Metodo para obtener el vector con los nombres de todos los clientes
     *
     * @return retorna el vector tipo String con los nombres de todos los
     * clientes
     */
    public DefaultComboBoxModel<String> getClientesComboBoxModel() {
        DefaultComboBoxModel<String> listaNombres = new DefaultComboBoxModel<>();
        getListaPersonas().stream().filter((persona) -> (persona.getTipo().equals("CLIENTE"))).forEachOrdered((persona) -> {
            listaNombres.addElement(persona.getNombre() + " " + persona.getApellido());
        });
        return listaNombres;
    }

    /**
     * Metodo para obtener el vector con los nombres de todas las personas -
     * Clientes y Proveedores
     *
     * @return retorna el vector tipo String con los nombres de todos los
     * proveedores
     */
    public DefaultComboBoxModel<String> getPersonasComboBoxModel() {
        DefaultComboBoxModel<String> listaNombres = new DefaultComboBoxModel<>();
        List<Persona> lista = getListaPersonas();
        if (!lista.isEmpty()) {
            lista.forEach((persona) -> {
                listaNombres.addElement(persona.getNombre() + " " + persona.getApellido());
            });
        } else {
            System.out.println("lista is empty");
        }
        return listaNombres;
    }

    /**
     * Metodo para obtener el vector con los nombres de todos los productos
     *
     * @return retorna un vector tipo String con los nombres de todos los
     * productos
     */
    public DefaultComboBoxModel<String> getProductosComboBoxModel() {
        List<Producto> lista = gestionarInventario.getListaProductos();
        DefaultComboBoxModel<String> listaNombres = new DefaultComboBoxModel<>();
        if (!lista.isEmpty()) {
            listaNombres.addElement("Todos los Productos");
            for (int i = 0; i < lista.size(); i++) {
                listaNombres.addElement(lista.get(i).getNombre());
            }
        }
        return listaNombres;
    }

    /**
     *
     *
     *
     *
     * Gestionar Caja
     *
     *
     *
     *
     */
    /**
     * Metodo para hacer la venta - compra
     *
     * @param frame
     * @param p
     * @param f
     * @param precio
     * @param g
     * @return
     */
    public boolean ventaCompra(JFrame frame, Persona p, Factura f, double precio, GestionarInventario g) {
        String trabajos = "#" + p.getCodPersona() + " " + p.getNombre() + " " + p.getApellido() + " - " + f.getTrabajos();
        if (!gestionarCaja.ventaCompra(frame, p, f.getListaProductos(), precio, g)) {
            System.out.println("ventaCompra false");
            return false;
        }
        System.out.println(f.toString());
        System.out.println(p);
        if (p.getTipo().equalsIgnoreCase("cliente")) {
            System.out.println("venta cliente");
            gestionarNotasLibro.addNota(f.getFecha(), 0d, precio, trabajos);
        } else {
            System.out.println("compra proveedor");
            gestionarNotasLibro.addNota(f.getFecha(), precio, 0d, trabajos);
        }
        actualizarCaja();
        Main.actualizarPanelCaja();
        return true;
    }

    /**
     * Metodo para actualizar la caja
     *
     * @return
     */
    public double actualizarCaja() {
        double cobros = 0;
        double gastos = 0;

        for (NotaLibroDiario nota : gestionarNotasLibro.getListaNotas()) {
            cobros += nota.getHaber();
            gastos += nota.getDebe();
        }

        setCaja(cobros - gastos);
        return cobros - gastos;
    }

    public void setCaja(double valor) {
        gestionarCaja.setCAJA(valor);
    }

    public void sumaCaja(double valor) {
        gestionarCaja.sumaCAJA(valor);
    }

    /**
     *
     *
     *
     *
     * Gestionar Facturas
     *
     *
     *
     *
     */
    /**
     * Metodo para obtener todas las facturas
     *
     * @return retorna una lista con las facturas
     */
    public List<Factura> getTodasLasFacturas() {
        return gestionarFacturas.getTodasLasFacturas();
    }

    /**
     * Metodo para obtener todas las facturas de una persona
     *
     * @param codPersona El codigo de la persona
     * @return retorna una lista con las facturas de dicha persona
     */
    public List<Factura> getListaFacturas(int codPersona) {
        return gestionarFacturas.getFacturasPorIDPersona(codPersona);
    }

    /**
     * Metodo para obtener las facturas de todos los clientes
     *
     * @return retorna una lista con los clientes
     */
    public List<Factura> getFacturasClientes() {
        return gestionarFacturas.getFacturasTypo("CLIENTE", this);
    }

    /**
     * Metodo para obtener las facturas de todos los proveedores
     *
     * @return retorna una lista con los proveedores
     */
    public List<Factura> getFacturasProveedores() {
        return gestionarFacturas.getFacturasTypo("PROVEEDOR", this);
    }

    /**
     * Metodo para crear una factura
     *
     * @param precio precio de la factura
     * @param ID_persona codigo de persona a la que pertenece dicha factura
     * @param listaProductos los productos de la factura
     * @param trabajos Actividades realizados para la empresa
     * @param frame Esta usado en la clase GestionarCaja para poder abrir dialog
     * @return retorna un int - las filas actualizadas en la base de datos
     */
    public int addFactura(double precio, int ID_persona, List<Producto> listaProductos, String trabajos, JFrame frame) {
        return gestionarFacturas.addFactura(precio, ID_persona, listaProductos, trabajos, this, frame, gestionarInventario);
    }

    /**
     * Metodo para obtener una factura segun el parametro pasado
     *
     * @param ID_factura El codigo de la factura que se va a buscar
     * @return retorna la factura encontrada , en caso contrario retorna null
     */
    public Factura getFacturaPorID(int ID_factura) {
        return gestionarFacturas.getFacturaPorID(ID_factura);
    }

    /**
     *
     *
     *
     *
     * Gestionar Inventario
     *
     *
     *
     *
     */
    /**
     * Metodo para modificar la cantidad de un producto (Venta)
     *
     * @param p el producto al que se va a modificar la cantidad
     * @param cantidad cuantas cantidades se han vendido. La cantidad se va a
     * restar de la cantidad que tiene el producto
     * @return retorna las filas actualizadas en la base de datos
     */
    public int setCantidadProducto(Producto p, int cantidad) {
        int cant = p.getCantidad() - cantidad;
        if (cant < 0) {
            return -1;
        } else {
            return gestionarInventario.setCantidadProducto(p.getCodProducto(), cant);
        }
    }

    /**
     * Metodo para modificar un producto
     *
     * @param p el producto que se va a modificar
     * @return return las filas actualizadas en la base de datos
     */
    public int modificarProducto(Producto p) {
        return gestionarInventario.modificarProducto(p.getCodProducto(), p.getNombre(), p.getPrecio(), p.getPeso(), p.getCantidad());
    }

    /**
     * Metodo para comprobar si existe un producto
     *
     * @param nombre el nombre del producto que se va a buscar
     * @return retorna true si lo encuentra y false en caso contrario
     */
    public boolean existeProducto(String nombre) {
        if (gestionarInventario.getListaProductos().stream().anyMatch((producto) -> (producto.getNombre().equals(nombre)))) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para obtener un producto buscando lo por nombre
     *
     * @param nombre el nombre del producto que se va a buscar
     * @return retorna el producto y null en caso contrario
     * @throws NullPointerException
     */
    public Producto getProductoPorNombre(String nombre) throws NullPointerException {
        for (Producto producto : gestionarInventario.getListaProductos()) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Metodo para refrescar la lista de los productos
     */
    public void refrescarListaProductos() {
        gestionarInventario.getListaProductos();
    }

    /**
     * Metodo para crear un producto (Compra)
     *
     * @param nombre el nombre del producto
     * @param precio el precio
     * @param peso su peso
     * @param cantidad cuanta cantidad se ha comprado
     * @return retorna las filas actualizadas en la base de datos
     */
    public int addProducto(String nombre, double precio, double peso, int cantidad) {
        return gestionarInventario.addProducto(nombre, precio, peso, cantidad);
    }

    /**
     * Metodo para borar un producto
     *
     * @param codProducto el codigo del producto que se va a borar
     * @return retorna las filas actualizadas en la base de datos
     */
    public int borrarProducto(int codProducto) {
        return gestionarInventario.borrarProducto(codProducto);
    }

    /**
     * Metodo para obtener la lista de los productos
     *
     * @return retorna una lista tipo Producto
     */
    public List<Producto> getListaProductos() {
        return gestionarInventario.getListaProductos();
    }

    /**
     * Metodo para obtener los productos de una factura
     *
     * @param ID_FACTURA el codigo de la factura a la que pertenecen los
     * productos
     * @return retorna una lista tipo producto
     */
    public List<Producto> getProductosPorFactura(int ID_FACTURA) {
        return gestionarInventario.getProductosPorFactura(ID_FACTURA);
    }

    /**
     * Metodo para obtener un producto segun el codigo pasado como parametro
     *
     * @param ID_producto el codigo del producto que se vaa buscar
     * @return retorna el producto , en caso contrario retorna null
     * @throws NullPointerException
     */
    public Producto getProductoPorID(int ID_producto) {
        return gestionarInventario.getProducto(ID_producto);
    }

    /**
     *
     *
     *
     *
     * Gestionar Notas Libro Diario
     *
     *
     *
     *
     */
    //String consulta = "insert into Table (fecha) values (to_date('" + sdf.format(fecha) + "', 'dd/MM/yyyy');
    /**
     * Metodo para obtener todas las notas del libro diario
     *
     * @return retorna una lista tipo NotaLibroDiario
     */
    public List<NotaLibroDiario> getNotaLibroDiarios() {
        return gestionarNotasLibro.getListaNotas();
    }

    /**
     * Metodo para obtener la fecha mas antigua entre las notas del libro diario
     *
     * @return retorna la fecha mas antigua
     */
    public Date getNotasFechaIn() {
        return gestionarNotasLibro.getFechaIn();
    }

    /**
     * Metodo para obtener la fecha mas reciente entre las notas del libro
     * diario
     *
     * @return retorna la fecha mas reciente
     */
    public Date getNotasFechaFin() {
        return gestionarNotasLibro.getFechaFin();
    }

    /**
     * Metodo para obtener una lista con las notas del libro diario entre 2
     * fechas
     *
     * @param fechaInicio fecha inicio de la busqueda
     * @param fechaFin fecha fin de la busqueda
     * @return retorna una lista tipo NotaLibroDiario
     */
    public List<NotaLibroDiario> getNotasEntreFechas(Date fechaInicio, Date fechaFin) {
        List<NotaLibroDiario> lista = new ArrayList<>();
        gestionarNotasLibro.getListaNotas().stream().filter((nota) -> (nota.getFecha().after(fechaInicio)
                && nota.getFecha().before(fechaFin)
                || nota.getFecha().equals(fechaInicio)
                || nota.getFecha().equals(fechaFin))).forEachOrdered((nota) -> {
            lista.add(nota);
            System.out.println(nota.toString());
        });
        return lista;
    }

    /**
     * Metodo para crear una nota del libro diario
     *
     * @param fecha fecha a la que se creo la nota
     * @param debe lo que debe
     * @param haber lo que se gana
     * @param detalle los detalles sobre la nota
     * @return retorna las filas actualizadas en la base de datos
     */
    public int addNota(Date fecha, Double debe, Double haber, String detalle) {
        return gestionarNotasLibro.addNota(fecha, debe, haber, detalle);
    }

    /**
     * Metodo para modificar una nota
     *
     * @param ID_nota el codigo de la nota
     * @param debe lo que debe
     * @param haber lo que gana
     * @param detalle detalles sobre la nota
     * @return retorna las filas actualizadas en la base de datos
     */
    public int moidifcarNota(int ID_nota, Double debe, Double haber, String detalle) {
        return gestionarNotasLibro.moidifcarNota(ID_nota, detalle, debe, haber);
    }

    /**
     * Metodo para borrar una nota
     *
     * @param ID_nota el codigo de la nota que se va a borrar
     * @return retorna las filas actualizadas en la base de datos
     */
    public int borrarNota(int ID_nota) {
        return gestionarNotasLibro.borrarNota(ID_nota);
    }

    /**
     * Metodo para obtener una nota del libro diario
     *
     * @param ID_nota el codigo de la nota que se va a buscar
     * @return retorna un la nota tipo NotaLibroDiario
     */
    public NotaLibroDiario getNotaPorID(int ID_nota) {
        return gestionarNotasLibro.getNota(ID_nota);
    }

    /**
     *
     *
     *
     *
     * Gestionar Personas
     *
     *
     *
     *
     */
    /**
     * Metodo para crear una persona - Cliente o Proveedor
     *
     * @param nombre el nombre de la persona
     * @param apellido los apellidos de la persona
     * @param direccion su direccion
     * @param telefono su telefono
     * @param email su email
     * @param tipo Que tipo es Cliente o Proveedor
     * @return retorna las filas actualizadas en la base de datos
     */
    public int addPersona(String nombre, String apellido, String direccion, long telefono, String email, String tipo) {
        return gestionarPersonas.addPersona(nombre, apellido, direccion, telefono, email, tipo);
    }

    /**
     * Metodo para obtener una persona buscada por nombre
     *
     * @param nombre el nombre de la persona que se vaa buscar
     * @return retorna la persona tipo Persona
     */
    public Persona getPersonaPorNombre(String nombre) {
        return gestionarPersonas.getPersonaPorNombre(nombre);
    }

    /**
     * Metodo para obtener todas las personas Clientes y Proveedores
     *
     * @return retorna una lista tipo Persona
     */
    public List<Persona> getListaPersonas() {
        return gestionarPersonas.getListaPersonas();
    }

    /**
     * Metodo para obtener la lista de los Clientes
     *
     * @return retorna una lista tipo Persona
     */
    public List<Persona> getListaClientes() {
        return gestionarPersonas.getListaClientes();
    }

    /**
     * Metodo para obtener la lista de los Proveedores
     *
     * @return retorna una lista tipo Persona
     */
    public List<Persona> getListaProveedores() {
        return gestionarPersonas.getListaProveedores();
    }

    /**
     * Metodo par obtener una persona por el codigo de una factura
     *
     * @param ID_factura el codigo de la factura
     * @return retorna la persona, en caso contrario retorna null
     * @throws NullPointerException
     */
    public Persona getPersonaPorIDFactura(int ID_factura) throws NullPointerException {
        return gestionarPersonas.getPersonaPorIDFactura(ID_factura);
    }

    /**
     * Metodo para obtener una persona por el codigo pasado como parametro
     *
     * @param ID_persona el codigo de la persona que se vaa buscar
     * @return retorna la persona tipo Persona
     */
    public Persona getPersonaPorID(int ID_persona) {
        return gestionarPersonas.getPersonaPorID(ID_persona);
    }

    /**
     * Metodo para borrar una persona
     *
     * @param ID_persona el codigo de la persona que se va a borrar
     */
    public void borrarPersona(int ID_persona) {
        gestionarPersonas.borrarPersona(ID_persona);
    }

    /**
     * Metodo para modificar una persona
     *
     * @param p la persona que se va a modificar
     */
    public void modificarPersona(Persona p) {
        gestionarPersonas.modificarPersona(p);
    }

    /**
     *
     *
     *
     *
     *
     * Gestionar Informes
     *
     *
     *
     *
     */
    /**
     * Metodo para imprimir informe de una nota pasada como parametro Se crea un
     * pdf con la informacion de la nota
     *
     * @param nota la nota a la que se va a hacer el informe
     */
    public void imprimirInformeNotas(NotaLibroDiario nota) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_NOTA", nota.getCodNota());
            JasperPrint print = JasperFillManager.fillReport("ireport/InformeNotas.jasper", parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, "ireport/InformeNotas.pdf");
            if (Desktop.isDesktopSupported()) {
                File myFile = new File("ireport/InformeNotas.pdf");
                Desktop.getDesktop().open(myFile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metodo para imprimir informe de notas de una fecha pasada como parametro
     * Se crea un pdf con la informacion de la nota
     *
     * @param nota la nota a la que se va a hacer el informe ? ???????
     */
    public void imprimirInformeNotasFecha(NotaLibroDiario nota) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Fecha", nota.getFecha());
            JasperPrint print = JasperFillManager.fillReport("ireport/InformeNotasFecha.jasper", parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, "ireport/InformeNotasFecha.pdf");
            if (Desktop.isDesktopSupported()) {
                File myFile = new File("ireport/InformeNotasFecha.pdf");
                Desktop.getDesktop().open(myFile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metodo para imprimir informe de una factura pasada como parametro Se crea
     * un pdf con la informacion de la factura
     *
     * @param f la factura a la que se va a hacer el informe
     */
    public void imprimirInformeFactura(Factura f) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_FACTURA", f.getCodFactura());
            JasperPrint print = JasperFillManager.fillReport("ireport/InformeFactura.jasper", parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, "ireport/InformeFactura.pdf");
            if (Desktop.isDesktopSupported()) {
                File myFile = new File("ireport/InformeFactura.pdf");
                Desktop.getDesktop().open(myFile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     *
     *
     *
     * Gestionar Conexion
     *
     *
     *
     *
     */
    public String getHost() {
        return conexion.getHost();
    }

    public String getUser() {
        return conexion.getUser();
    }

    public String getPass() {
        return conexion.getPass();
    }

    public int getPuerto() {
        return conexion.getPuerto();
    }

    public String getBBDDName() {
        return conexion.getBBDDName();
    }

    /**
     * Leer fichero Tablas
     */
    private void cargarTablasSQL() {
        String sqlFichero = "crear_tablas.sql";
        BufferedReader br = null;
        String cadena = "";
        String ficheroCompleto = "";
        String[] query;
        try {
            br = new BufferedReader(new FileReader(sqlFichero));
            while ((cadena = br.readLine()) != null) {
                ficheroCompleto += cadena;
            }
            query = ficheroCompleto.split(";");
            for (String q : query) {
                conexion.ejecutarStatementNOSELECT(q, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            }
        } catch (FileNotFoundException e) {
            LOG.log(Level.WARNING, "Fichero NO encontrado !!!");
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    
}


