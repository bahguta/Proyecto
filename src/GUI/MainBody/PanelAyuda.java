/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainBody;

import Logica.LogicaTemas;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Panel Ayuda
 * @author Plam
 */
public class PanelAyuda extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private final String root = "Componentes";
    private final String nLibro = "Libro Diario";
    private final String nFiltrarNotas = "Filtrar Notas";
    private final String nTableNotas = "Table Notas";
    private final String nCaja = "Caja";
    private final String nInventario = "Inventario";
    private final String nNuevoProducto = "Nuevo Producto";
    private final String nTableProductos = "Table Productos";
    private final String nCompras = "Compras";
    private final String nVentas = "Ventas";
    private final String nPersonas = "Personas";
    private final String nClientes = "Clientes";
    private final String nProveedores = "Proveedores";
    private final String nEstadistica = "Estadistica";
    private final String nFacturas = "Facturas";
    private final String nFiltrarFacturas = "Filtrar Facturas";
    private final String nTableFacturas = "Table Facturas";
    private final String nAjustes = "Ajustes";
    private final String nGeneral = "General";
    private final String nBBDD = "Base de Datos";
    private final String nCopiaSeguridad = "Copia de Seguridad";
    private final String nAyuda = "Ayuda";
    private final String nInfo = "Info";
    private final String nTree = "Arbol";
    
    
    
    
    /**
     * Creates new form PanelAyuda
     */
    public PanelAyuda(JFrame frame) {
        initComponents();

        setBorder(LogicaTemas.GET_TITLE_BORDER("Ayuda"));
        
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
                
                switch(node.getUserObject().toString()){
                    case root:
                        jTextAreaAyuda.setText("Informacion sobre los menus del programa");
                        break;
                    case nFiltrarNotas:
                        jTextAreaAyuda.setText("Se pueden filtrar las notas del libro diario entre las fechas elegidas");
                        break;
                    case nTableNotas:
                        jTextAreaAyuda.setText("Informacion sobre las notas del libro diario existentes");
                        break;
                    case nNuevoProducto:
                        jTextAreaAyuda.setText("Se puede crear nuevo producto. Abre ventana secundaria !");
                        break;
                    case nTableProductos:
                        jTextAreaAyuda.setText("Informacion sobre todos los productos del inventario existentes");
                        break;
                    case nFiltrarFacturas:
                        jTextAreaAyuda.setText("Se pueden filtrar las facturas por clientes y proveedores y tambien entre fechas elegidas.");
                        break;
                    case nTableFacturas:
                        jTextAreaAyuda.setText("Muestra los clientes y/o proveedores existentes\n\nLa segunda tabla muestra todas las facturas que tiene el cliente/proveedor");
                        break;
                    case nGeneral:
                        jTextAreaAyuda.setText("Se puede camiar la vista del programa elegida de la lista desplegable.\n\nDesde el slider se puede cambiar el tamaño del texto para todos los componentes del programa");
                        break;
                    case nBBDD:
                        jTextAreaAyuda.setText("Se puede cambiar la base de datos para los registros\n Host  --  La direccion donde se encuentra la base de datos\n Puerto  -- el puerto de la direccion\n BD name -- el nombre de la base de datos que se va a usar\n Contraseña  --  la contraseña para conectar a la base de datos\n\nSe ejecutara un escript SQL para crear todas las tablas necesarias en caso de cambiar la base de datos. Se pueden copiar todos los registros");
                        break;
                    case nCopiaSeguridad:
                        jTextAreaAyuda.setText("Se puede crear copia de seguridad. Creara un script SQL con todas las tablas y registros existentes en el programa\n\nSe puede cargar copia de seguridad. Recibe un script SQL para insertar registros en el programa\n\nSe pueden borrar todos los registros en el programa.\n-- PELIGRO . DEJARA EL PROGRAMA SIN NINGUN REGISTRO ! --");
                        break;
                    case nInfo:
                        jTextAreaAyuda.setText("Informacion sobre el programa y el desarrollador de la misma");
                        break;
                    case nTree:
                        jTextAreaAyuda.setText("Informacion sobre el arbol de menus del programa");
                        break;
                    default:
                        jTextAreaAyuda.setText("");
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAyuda = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(901, 500));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Software para gestionar el inventario, clientes, proveedores, notas del libro diario y facturas para PYMES.El programa ha sido desarrollado para realizar el proyecto del modulo \"Desarrollo de Aplicaciones Multiplataforma\" instituto - IES Juan Jose Calvo Miguel - Sotrondio.\n\nTutor: Julia Paz Triana Toribio");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Componentes");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Libro Diario");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Filtrar Notas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Table Notas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Caja");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Inventario");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Nuevo Producto");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Table Productos");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Compras");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ventas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Personas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Clientes");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Proveedores");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Estadistica");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("help2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Facturas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Filtrar Facturas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Table Facturas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ajustes");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("General");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Base de Datos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Copia de Seguridad");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ayuda");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Info");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Arbol");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(jTree1);

        jTextAreaAyuda.setEditable(false);
        jTextAreaAyuda.setColumns(20);
        jTextAreaAyuda.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jTextAreaAyuda.setLineWrap(true);
        jTextAreaAyuda.setRows(5);
        jTextAreaAyuda.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaAyuda);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Monospaced", 2, 14)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("Desarrollador\n\tNombre: Plamen\n\tApellidos: Georgiev Petkov\n\tEdad: 37\n\tTelefono: 611442359\n\tEmail: bahguta.pp@gmail.com\n\tDireccion: Piedras Blancas / Castrillon");
        jTextArea3.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextAreaAyuda;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(PanelAyuda.class.getName());
}
