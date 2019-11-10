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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.openide.util.Exceptions;

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

    private static String RUTA_PRODUCTOS = "productos.txt";
    private static String RUTA_FACTURAS = "facturas.txt";
    private static String RUTA_PERSONAS = "personas.txt";
    private static String RUTA_USUARIO = "usuario.txt";
    private static String RUTA_NEGOCIO = "negocio.txt";

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
        Usuario usuario = gestionarPersonas.getUsuario();
        //si el resultado es null significa que no hay usuario, 
        //o sea no se ha establecido contraseña nunca 
        
        if (null == usuario) {
            gestionarPersonas.addUsuario(pass);
            return true;
        } else if (pass.equals(usuario.getPass())) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para cambiar la constraseña del administrador
     */
    public int cambiarPass(String pass, String newPass) {
        if (son2PassIguales(pass)) {
            return gestionarPersonas.cambiarPass(newPass);
        }
        return -1;
    }

    public Usuario getUsuario() {
        usuario =  gestionarPersonas.getUsuario();
        return usuario;
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
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Fecha", nota.getFecha());
            JasperPrint print = JasperFillManager.fillReport("ireport/InformeNotasFecha.jasper", parametros, conexion.getConexion());
            JasperExportManager.exportReportToPdfFile(print, "informes/InformeNotasFecha_" + System.currentTimeMillis() + ".pdf");
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
            JasperExportManager.exportReportToPdfFile(print, "informes/InformeFactura_" + System.currentTimeMillis() + ".pdf");
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
     * Leer fichero Tablas
     */
    private boolean cargarTablasSQL(String rutaFichero) {
        //String sqlFichero = "crear_tablas.sql";
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

    public boolean cargarRegistrosIniciales() {
        return cargarTablasSQL("registros.sql");
    }

    public boolean cargarTablas() {
        return cargarTablasSQL("crear_tablas.sql");
    }

    public boolean crearCopiaSeguridad() {
        try {
            List<NotaLibroDiario> listaNotas = gestionarNotasLibro.getListaNotas();
            List<Producto> listaProductos = gestionarInventario.getListaProductos();
            List<Factura> listaFacturas = gestionarFacturas.getListaFacturas();
            List<Persona> listaPersonas = gestionarPersonas.getListaPersonas();
            Usuario u = gestionarPersonas.getUsuario();
            List<int[]> listaNegocio = gestionarCaja.getArrayNegocio();
            if (crearFichero(listaNotas, listaProductos, listaFacturas, listaPersonas, u, listaNegocio)) {
                return true;
            }

        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
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
    private boolean crearFichero(List<NotaLibroDiario> listaNotas,
            List<Producto> listaProductos,
            List<Factura> listaFacturas,
            List<Persona> listaPersonas,
            Usuario u,
            List<int[]> listaNegocio) {

        if (escribirListas(RUTA_PRODUCTOS, listaProductos)
                && escribirListas(RUTA_FACTURAS, listaFacturas)
                && escribirListas(RUTA_PERSONAS, listaPersonas)
                && escribirUsuario(RUTA_USUARIO)
                && escribirNegocio(RUTA_NEGOCIO, listaNegocio)) {
            return true;
        }

        return false;
    }

    private boolean escribirUsuario(String fichero) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fichero)));
            oos.writeObject(getUsuario());
            try {
                oos.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    private boolean escribirNegocio(String fichero, List<int[]> lista) {
        try {
            final FileWriter fw = new FileWriter(new File(fichero));
            lista.stream().forEach(n -> {
                for (int i : n) {
                    try {
                        fw.write(i);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            });
            try {
                fw.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }

    private boolean escribirListas(String fichero, List lista) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fichero)));
            lista.stream().forEach(o -> {
                try {
                    oos.writeObject(o);
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
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return false;
    }

    private Usuario getDatosUsuario(String fichero) {
        Usuario u = new Usuario("");
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fichero)));
            u = (Usuario) ois.readObject();
            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return u;
    }

    private List<int[]> getNegocio(String fichero) {
        List<int[]> lista = new ArrayList<>();

        try {
            final FileReader fr = new FileReader(new File(fichero));
            int[] registro = {fr.read(), fr.read(), fr.read(), fr.read()};
            while (registro != null) {
                lista.add(registro);
                registro = new int[]{fr.read(), fr.read(), fr.read(), fr.read()};
            }
            try {
                fr.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return lista;
    }

    public List getList(String fichero) {
        List lista = new LinkedList();
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fichero)));

            Object o = null;
            while ((o = ois.readObject()) != null) {
                lista.add(o);
            }

            try {
                ois.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return lista;
    }

    private List<Persona> getRegistrosPersonas() {
        List<Persona> lista = getList(RUTA_PERSONAS);
        return lista;
    }

    private List<Factura> getRegistrosFactura() {
        List<Factura> lista = getList(RUTA_FACTURAS);
        return lista;
    }

    private List<Producto> getRegistrosProducto() {
        List<Producto> lista = getList(RUTA_PRODUCTOS);
        return lista;
    }

    private Usuario getRegistroUsuario() {
        return getDatosUsuario(RUTA_USUARIO);
    }

    private List<int[]> getRegistrosNegocio() {
        return getNegocio(RUTA_NEGOCIO);
    }

    public boolean cargarRegistros() {
        crearCopiaSeguridad();
        getRegistroUsuario().toString();
        getRegistrosFactura().stream().forEach(f -> f.toString());
        getRegistrosNegocio().stream().forEach(neg -> Arrays.toString(neg));
        getRegistrosPersonas().stream().forEach(per -> per.toString());
        getRegistrosProducto().stream().forEach(p -> p.toString());
        return true;

    }

}
