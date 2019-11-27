/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dto.*;
import GUI.Main.App;
import Gestiones.*;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogicaNegocio.class);

    //rutas ficheros
    private static String RUTA_PRODUCTOS = "datos" + File.separator + "productos.txt";
    private static String RUTA_FACTURAS = "datos" + File.separator + "facturas.txt";
    private static String RUTA_PERSONAS = "datos" + File.separator + "personas.txt";
    private static String RUTA_USUARIO = "datos" + File.separator + "usuario.txt";
    private static String RUTA_NEGOCIO = "datos" + File.separator + "negocio.txt";
    private static String RUTA_NOTAS = "datos" + File.separator + "notas.txt";
    private static String RUTA_REGISTROS_INICIALES = "datos" + File.separator + "registros.sql";
    private static String RUTA_CREAR_TABLAS = "datos" + File.separator + "crear_tablas.sql";

    // gestiones 
    private ConexionBBDD conexion;
    private GestionarInventario gestionarInventario;
    private GestionarPersonas gestionarPersonas;
    private GestionarFacturas gestionarFacturas;
    private GestionarNotasLibro gestionarNotasLibro;
    private GestionarCaja gestionarCaja;

    //ruta para los informes que va a crear
    private File rutaInformes;
    private Usuario usuario;
    //comprueba si esta establecida la conexion con la base de datos 
    //devuelve true si existe una conexion con base de datos y false en caso contrario
    private static boolean isConexion = false;

    public LogicaNegocio() {
        conexion = new ConexionBBDD();
        this.gestionarPersonas = new GestionarPersonas(conexion);
        this.gestionarFacturas = new GestionarFacturas(conexion);
        this.gestionarInventario = new GestionarInventario(conexion);
        this.gestionarNotasLibro = new GestionarNotasLibro(conexion);
        this.gestionarCaja = new GestionarCaja(conexion);

        //creo las carpetas necesarias para el funcionamiento del programa
        crearCarpeta("ireport");
        crearCarpeta("informes");
        crearCarpeta("datos");
    }

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
        this.conexion = new ConexionBBDD();
        //inicializo la conexion 
        //this.conexion.conexionBBDD(IP, puerto, nombreBBDD, usuario, password);

        //creo las clases de gestionar 
        this.gestionarPersonas = new GestionarPersonas(conexion);
        this.gestionarFacturas = new GestionarFacturas(conexion);
        this.gestionarInventario = new GestionarInventario(conexion);
        this.gestionarNotasLibro = new GestionarNotasLibro(conexion);
        this.gestionarCaja = new GestionarCaja(conexion);

        //creo las carpetas necesarias para el funcionamiento del programa
        //crearCarpeta("ireport");
        crearCarpeta("informes");
        crearCarpeta("datos");
        crearCarpeta("estadistica");

        //actualizo la caja 
        actualizarCaja();

        this.rutaInformes = new File("informes");
        if (rutaInformes.mkdir()) {
            logger.info("Carpeta informes creada con exito");
        } else if (rutaInformes.exists()) {
            logger.info("La carpeta Informes existe !!! No se crea !");
        } else {
            JOptionPane.showMessageDialog(frame, "Carpeta" + rutaInformes.getPath() + " NO creada\nRevisa los persmisos del programa.");

        }
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
     * Metodo para añadir usuario
     *
     * @param nombre
     * @param pass
     * @param userRole
     * @return
     */
    /**
     * Metodo para generar una contraseña criptada
     */
    public String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * Metodo para comprobar si 2 parolas son iguales
     */
    public boolean son2PassIguales(String pass) {
        //Usuario usuario = gestionarPersonas.getUsuario();
        //si el resultado es null significa que no hay usuario, 
        //o sea no se ha establecido contraseña nunca 
        getUsuarioRegistro();
        if (null == this.usuario) {
            gestionarPersonas.addUsuario(cryptWithMD5(pass));
            getUsuarioRegistro();
            return true;
        } else if (cryptWithMD5(pass).equals(this.usuario.getPass())) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para cambiar la constraseña del administrador
     */
    public int cambiarPass(String passActual, String newpass) {
        if (son2PassIguales(passActual)) {
            return gestionarPersonas.cambiarPass(cryptWithMD5(newpass));
        }
        return -1;
    }

    /**
     * Metodo para obtener el usuario del sistema
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Metodo para obtener el usuario desde la BBDD
     *
     * @return
     */
    private void getUsuarioRegistro() throws NullPointerException {
        this.usuario = gestionarPersonas.getUsuario();
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
     * @param p
     * @param f
     * @param precio
     * @param g
     * @return
     */
    public boolean ventaCompra(Persona p, Factura f, double precio, GestionarInventario g) {
        if (f != null) {
            System.out.println(p.getCodPersona());
            System.out.println(p.getNombre());
            System.out.println(p.getApellido());
            System.out.println(f.getTrabajos());
            String trabajos = "#" + p.getCodPersona() + " " + p.getNombre() + " " + p.getApellido() + " - " + f.getTrabajos();
            if (!gestionarCaja.ventaCompra(p, f.getListaProductos(), precio, g)) {
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
            App.actualizarPaneles();
            return true;
        }
        return false;
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
        return gestionarFacturas.addFactura(precio, ID_persona, listaProductos, trabajos, this, gestionarInventario);
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
     * Metodo para obtener una lista con las notas del libro diario entre dos
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
            JasperExportManager.exportReportToPdfFile(print, "informes/InformeNotas_" + System.currentTimeMillis() + ".pdf");
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
            //creo las carpetas pos si no existen 
            //crearCarpetasEFicheros("ireport", "InformeNotasFecha.jasper");
            //crearCarpetasEFicheros("informes", "InformeNotasFecha_" + System.currentTimeMillis() + ".pdf");

            String rutaJasper = "ireport" + File.separator + "InformeNotasFecha.jasper";
            String rutaPDF = "informes" + File.separator + "InformeNotasFecha_" + System.currentTimeMillis() + ".pdf";

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Fecha", nota.getFecha());

            JasperPrint print = JasperFillManager.fillReport(rutaJasper, parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, rutaPDF);
            if (Desktop.isDesktopSupported()) {
                File myFile = new File(rutaPDF);
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
            String rutaJasper = "ireport" + File.separator + "InformeFactura.jasper";
            String rutaPDF = "informes" + File.separator + "InformeFactura_" + System.currentTimeMillis() + ".pdf";

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_FACTURA", f.getCodFactura());
            JasperPrint print = JasperFillManager.fillReport(rutaJasper, parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, rutaPDF);
            if (Desktop.isDesktopSupported()) {
                File myFile = new File(rutaPDF);
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
     * Metodo para conectar a la base de datos con MySQL
     *
     * @param usuario
     * @param password
     * @param IP
     * @param puerto
     * @param nombreBBDD
     * @return
     */
    public boolean conectBBDD(String usuario, String password, String IP, int puerto, String nombreBBDD) {
        if (this.conexion.conexionBBDDMySql(IP, puerto, nombreBBDD, usuario, password)) {
            LogicaNegocio.isConexion = true;
            return true;
        }
        return false;
    }

    /**
     * Metodo para comprobar si hay conexion con la bbdd
     *
     * @return
     */
    public boolean isConexionExitosa() {
        return conexion.isConexionExitosa();
    }

    /**
     * Metodo para cambiar la conexion
     */
    public boolean cambiarConexion(String usuario, String password, String IP, int puerto, String nombreBBDD, boolean cargarRegistros) {
        if (cargarRegistros) {
            cargarRegistrosIniciales();

        }
        if (this.conexion.conexionBBDDMySql(IP, puerto, nombreBBDD, usuario, password)) {
            if (hayQueCrearTablas()) {
                cargarTablas();
            }
            return true;
        }
        return false;
    }

    /**
     * Metodo para comprobar si hay que crear las tablas
     *
     * @return
     */
    public boolean hayQueCrearTablas() {
        if (gestionarPersonas.isExisteLaTabla()
                || gestionarNotasLibro.isExisteLaTabla()
                || gestionarInventario.isExisteLaTabla()
                || gestionarFacturas.isExisteLaTabla()) {
            return true;
        }
        return false;
    }

    /**
     *
     *
     *
     *
     * Gestionar Ficheros
     *
     *
     *
     *
     */
    /**
     * Metodo para crear carpeta
     *
     * @param fichero el nombre del fichero que se va a crear
     */
    public void crearCarpeta(String fichero) {
        File rutaDir = new File(fichero);
        if (!rutaDir.exists()) {
            rutaDir.mkdir();
        }
    }

    /**
     * Metodo para crear carpetas y fichero
     *
     * @param dirPath la ruta (relativa o absoluta)
     * @param fichero el nombre del fichero que se va a crear
     * @return
     */
    public File crearCarpetasEFicheros(String dirPath, String fichero) {
        File ruta = null;
        try {
            File rutaDir = new File(dirPath);
            if (!rutaDir.exists()) {
                rutaDir.mkdirs();
            }
            File RutaFichero = new File(fichero);
            if (!ruta.exists()) {
                ruta.createNewFile();
            }
            ruta = new File(dirPath, fichero);
        } catch (Exception e) {
            logger.error("error al crear el fichero", e);
        }
        return ruta;
    }

    /**
     * Leer fichero Tablas
     */
    private boolean cargarTablasSQL(String rutaFichero) {
        BufferedReader br = null;
        String cadena = "";
        String ficheroCompleto = "";
        String[] query;
        try {
            br = new BufferedReader(new FileReader(rutaFichero));
            while ((cadena = br.readLine()) != null) {
                ficheroCompleto += cadena;
            }
            query = ficheroCompleto.split(";");
            for (String q : query) {
                conexion.ejecutarStatementNOSELECT(q, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            }
            return true;
        } catch (FileNotFoundException e) {
            logger.error("Fichero NO encontrado !!!");
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                }
            }
        }
        return false;
    }

    /**
     * Metodo para cargar registros iniciales desde un fichero SQL
     *
     * @return
     */
    public boolean cargarRegistrosIniciales() {
        return cargarTablasSQL(RUTA_REGISTROS_INICIALES);
    }

    /**
     * Metodo para crear las tablas en una base de datos, se carga desde un
     * fichero sql
     *
     * @return
     */
    public boolean cargarTablas() {
        return cargarTablasSQL(RUTA_CREAR_TABLAS);
    }

    /**
     * Metodo para crear copia de seguridad
     *
     * @return
     */
    public boolean crearCopiaSeguridad() {
        List<NotaLibroDiario> listaNotas = gestionarNotasLibro.getListaNotas();
        List<Producto> listaProductos = gestionarInventario.getListaProductos();
        List<Factura> listaFacturas = gestionarFacturas.getListaFacturas();
        List<Persona> listaPersonas = gestionarPersonas.getListaPersonas();
        List<Negocio> listaNegocio = gestionarCaja.getListaNegocio();
        Usuario u = gestionarPersonas.getUsuario();
        if (crearFichero(listaNotas, listaProductos, listaFacturas, listaPersonas, u, listaNegocio)) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para guardar todas las tablas en un fichero
     *
     * @param listaNotas
     * @param listaProductos
     * @param listaFacturas
     * @param listaPersonas
     * @param u
     * @param listaNegocio
     * @return
     */
    private boolean crearFichero(List listaNotas,
            List listaProductos,
            List listaFacturas,
            List listaPersonas,
            Usuario u,
            List<Negocio> listaNegocio) {

        if (escribirListas(RUTA_PRODUCTOS, listaProductos)
                && escribirListas(RUTA_NOTAS, listaNotas)
                && escribirListas(RUTA_FACTURAS, listaFacturas)
                && escribirListas(RUTA_PERSONAS, listaPersonas)
                && escribirUsuario(RUTA_USUARIO)
                && escribirNegocio(RUTA_NEGOCIO, listaNegocio)) {
            return true;
        }

        return false;
    }

    /**
     * Metodo para escribir el usuario en un fichero
     *
     * @param fichero la ruta del fichero para guardar el usuario
     * @return
     */
    private boolean escribirUsuario(String fichero) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fichero)));
            if (null == gestionarPersonas.getUsuario()) {

                oos.writeObject(new Usuario(""));
            } else {
                oos.writeObject(gestionarPersonas.getUsuario());
            }
            logger.info("Usuario " + gestionarPersonas.getUsuario().toString());
            try {
                oos.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        } catch (FileNotFoundException ex) {
            logger.info("Datos no encontrados ", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Metodo para escribir el negocio del programa
     *
     * @param fichero la ruta del fichero para guardar el negocio
     * @return
     */
    private boolean escribirNegocio(String fichero, List<Negocio> lista) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fichero)));
            if (null != lista && !lista.isEmpty()) {
                lista.stream().forEach(n -> {
                    try {
                        oos.writeObject(n);
                        logger.info("Negocio " + n.toString());
                    } catch (IOException ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                });
            }

            try {
                oos.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        } catch (FileNotFoundException ex) {
            logger.info("Datos no encontrados ", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return false;
    }

    /**
     * Metodo para escribir el lista en un fichero
     *
     * @param fichero la ruta del fichero para guardar la lista
     * @param lista la lista que se va a escribir
     * @return
     */
    private boolean escribirListas(String fichero, List lista) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fichero)));
            lista.stream().forEach(o -> {
                try {
                    oos.writeObject(o);
                    logger.info("objeto " + o.toString());
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            });
            try {
                oos.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        } catch (FileNotFoundException ex) {
            logger.info("Datos no encontrados ", ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
        return false;
    }

    /**
     * Metodo para obtener el usuario desde fichero
     *
     * @return retorna el usuario
     */
    private Usuario getDatosUsuario() {
        Usuario u = new Usuario("");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_USUARIO)));
            if (ois.available() > 0) {
                u = (Usuario) ois.readObject();
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.info("Datos no encontrados ", ex);
        } catch (IOException ex) {
            logger.error(ex);
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        }
        return u;
    }

    /**
     * Metodo para obtener el negocio del programa
     *
     * @return retorna el negocio
     */
    private List<Negocio> getNegocio() {
        List<Negocio> lista = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_NEGOCIO)));
            Negocio n = null;
            while ((n = (Negocio) ois.readObject()) != null) {
                lista.add(n);
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            logger.info("Datos no encontrados ", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        }
        return lista;
    }

    /**
     * Metodo para obtener la lista de las personas desde un fichero
     *
     * @return retorna la lista de las personas
     */
    private List<Persona> getListPersonas() {
        List<Persona> lista = new LinkedList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_PERSONAS)));
            Persona p = null;
            while ((p = (Persona) ois.readObject()) != null) {
                lista.add(p);
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.error("Fichero no encontrado", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        return lista;
    }

    /**
     * Metodo para obtener la lista de las facturas desde un fichero
     *
     * @return retorna la lista de las facturas
     */
    private List<Factura> getListFacturas() {
        List<Factura> lista = new LinkedList<>();
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_FACTURAS)));
            Factura f = null;
            while ((f = (Factura) ois.readObject()) != null) {
                lista.add(f);
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.error("Fichero no encontrado", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        return lista;
    }

    /**
     * Metodo para obtener la lista de los productos desde un fichero
     *
     * @return retorna la lista de los productos
     */
    private List<Producto> getListProductos() {
        List<Producto> lista = new LinkedList<>();
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_PRODUCTOS)));
            Producto p = null;
            while ((p = (Producto) ois.readObject()) != null) {
                lista.add(p);
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.error("Fichero no encontrado", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        return lista;
    }

    /**
     * Metodo para obtener la lista de las notas desde un fichero
     *
     * @return retorna la lista de las notas
     */
    private List<NotaLibroDiario> getListNotas() {
        List<NotaLibroDiario> lista = new LinkedList<>();
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(RUTA_NOTAS)));
            NotaLibroDiario n = null;
            while ((n = (NotaLibroDiario) ois.readObject()) != null) {
                lista.add(n);
            }
            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.error("Fichero no encontrado", ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        return lista;
    }

    /**
     * Metodos para obtener el registro de las personas desde un fichero
     *
     * @return
     */
    private List<Persona> getRegistrosPersonas() {
        if (null == getListPersonas()) {
            return new ArrayList<>();
        }
        return getListPersonas();
    }

    /**
     * Metodos para obtener el registro de las facturas desde un fichero
     *
     * @return
     */
    private List<Factura> getRegistrosFactura() {
        if (null == getListFacturas()) {
            return new ArrayList<>();
        }
        return getListFacturas();
    }

    /**
     * Metodos para obtener el registro de los productos desde un fichero
     *
     * @return
     */
    private List<Producto> getRegistrosProducto() {
        if (null == getListProductos()) {
            return new ArrayList<>();
        }
        return getListProductos();
    }

    /**
     * Metodos para obtener el registro de las notas desde un fichero
     *
     * @return
     */
    private List<NotaLibroDiario> getRegistrosNota() {
        if (null == getListNotas()) {
            return new ArrayList<>();
        }
        return getListNotas();
    }

    /**
     * Metodos para obtener el registro del usuario desde un fichero
     *
     * @return
     */
    private Usuario getRegistroUsuario() {
        if (null == getDatosUsuario()) {
            return new Usuario("");
        }
        return getDatosUsuario();
    }

    /**
     * Metodos para obtener el registro del negocio desde un fichero
     *
     * @return
     */
    private List<Negocio> getRegistrosNegocio() {
        if (null == getNegocio()) {
            return new ArrayList<>();
        }
        return getNegocio();
    }

    /**
     * Metodo para cargar todos los registros desde ficheros y guardarlos en la
     * bbdd
     *
     * @return
     */
    public boolean cargarRegistros() {
        cargarTablas();
        logger.info("Cargando registros ... ");

        logger.info("NOTAS DEL LIBRO DIARIO: ");
        getRegistrosNota().forEach(nota -> {
            gestionarNotasLibro.addNota(nota.getFecha(), nota.getDebe(), nota.getHaber(), nota.getDetalle());
            logger.info("Nota: " + nota.toString());
        });

        logger.info("PERSONAS: ");
        getRegistrosPersonas().forEach(per -> {
            gestionarPersonas.addPersona(per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getEmail(), per.getTipo());
            logger.info("Persona: " + per.toString());
        });

        logger.info("PRODUCTOS: ");
        getRegistrosProducto().forEach(p -> {
            gestionarInventario.addProducto(p.getNombre(), p.getPrecio(), p.getPeso(), p.getCantidad());
            logger.info("Producto: " + p.toString());
        });
        logger.info("NEGOCIO:");

        List<Negocio> listaNegocio = getRegistrosNegocio();
        listaNegocio.forEach(neg -> {
            gestionarCaja.addNegocio(neg.getID_factura(), neg.getID_persona(), neg.getID_producto(), neg.getCantidad());
            logger.info("Negocio: " + neg.toString());

        });

        logger.info("FACTURAS:");
        getRegistrosFactura().forEach(f -> {
            final int id_persona = listaNegocio.stream().filter(fac -> fac.getID_factura() == fac.getID_factura()).findFirst().get().getID_persona();
            if (getListaPersonas().stream().anyMatch(p -> p.getCodPersona() == id_persona)) {
                gestionarFacturas.addFactura(f.getPrecio(), id_persona,
                        f.getListaProductos(), f.getTrabajos(), this, gestionarInventario);
                logger.info("Factura: " + f.toString());
            }
        });

        logger.info("USUARIOS:");
        this.usuario = getRegistroUsuario();
        logger.info("Usuario: " + this.usuario.toString());

        //actualizo las listas 
        actualizarListas();
        return true;
    }

    /**
     * Metodo para borrar todos los registros
     *
     * @return
     */
    public int borrarRegistros() {
        int filas = 0;
        filas += gestionarFacturas.borrarFacturas();
        filas += gestionarInventario.borrarProductos();
        filas += gestionarNotasLibro.borrarNotas();
        filas += gestionarPersonas.borrarPersonas();
        filas += gestionarCaja.borrarNegocio();
        return filas;
    }

    /**
     * Metodo para actualizar las listas y despues actualizar los paneles
     */
    private void actualizarListas() {
        gestionarCaja.refrescarNegocio();
        gestionarFacturas.refrescarFacturas();
        gestionarInventario.refrescarListaProductos();
        gestionarNotasLibro.refrescarListaNotas();
        gestionarPersonas.refrescarListaPersonas();
        this.usuario = gestionarPersonas.getUsuario();
        App.actualizarPaneles();
    }

}
