/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import Dto.Usuario;
import GUI.MainBody.PanelAjustes;
import GUI.MainBody.PanelAyuda;
import GUI.MainBody.PanelCaja;
import GUI.MainBody.PanelCompra;
import GUI.MainBody.PanelEstadistica;
import GUI.MainBody.PanelFacturas;
import GUI.MainBody.PanelInventario;
import GUI.MainBody.PanelLibroDiario;
import GUI.MainBody.PanelPersonas;
import GUI.MainBody.PanelVenta;
import Gestiones.GestionarCaja;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openide.util.Exceptions;

/**
 *
 *
 * @author Plam
 */
public class Main extends javax.swing.JFrame implements MenuListener {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);

    public final static int MIN_LARGO = 1400;
    public final static int MIN_ALTO = 900;
    private static double VERSION = 1.0;
    private static final long serialVersionUID = 1L;

    private LogicaNegocio logica = null;
    private GestionarCaja gestionarCaja = null;

    private CardLayout cardLayout;
    private static JPanel panelAjustes, panelAjuste,
            panelLibroDiario,
            panelCaja,
            panelInventario,
            panelCompra,
            panelVenta,
            panelPersonas,
            panelEstadistica,
            panelFacturas,
            panelAyuda;
    private Usuario usuario;
    // private JScrollPane scrollPane;

    /**
     * Constructor:
     */
    public Main() {
        BasicConfigurator.configure();
        imprimirLogo();
        LogicaTemas logicaTemas = new LogicaTemas();
        logica = new LogicaNegocio();
        ConexionBBDD login = new ConexionBBDD(this, true, logica);
        login.setVisible(true);
        
        
//        /**
//         * Metodo para cerrar la aplicacion por si no se establece conexion con
//         * la base de datos
//         */
//        login.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent we) {
//                super.windowClosed(we);
//                if (!LogicaNegocio.isIsConexion()) {
//                    System.exit(0);
//                }
//            }
//
//        });
//        if (login != null) {
//            usuario = new Usuario(login.getNombreLogin(), login.getPassLogin());
//        }

        //Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/icono.png"));
        
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        Image img = new ImageIcon(getClass().getResource("/img/icono.png")).getImage();
        setIconImage(img);
        //this.setMinimumSize(new Dimension(MIN_LARGO, MIN_ALTO));

        

        //conexion(login.getNombre(), login.getPass(), login.getHost(), login.getPuerto(), login.getNombreBBDD());
        //conexion();

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        //boxLayout.getTarget().setMinimumSize(new Dimension(MIN_LARGO, MIN_ALTO));

        setLayout(boxLayout);

        cardLayout = new CardLayout();

        jPanelBody.setLayout(cardLayout);

        panelAjustes = new PanelAjustes(this, logica);
        panelLibroDiario = new PanelLibroDiario(this, logica);
        panelCaja = new PanelCaja(this, logica);
        panelInventario = new PanelInventario(this, logica);
        panelCompra = new PanelCompra(this, logica);
        panelVenta = new PanelVenta(this, logica);
        panelPersonas = new PanelPersonas(this, logica);
        panelEstadistica = new PanelEstadistica(this, logica);
        panelFacturas = new PanelFacturas(this, logica);
        panelAyuda = new PanelAyuda(this);

        jPanelBody.add(panelLibroDiario, "panelLibroDiario");
        jPanelBody.add(panelAjustes, "panelAjustes");
        jPanelBody.add(panelCaja, "panelCaja");
        jPanelBody.add(panelInventario, "panelInventario");
        jPanelBody.add(panelCompra, "panelCompras");
        jPanelBody.add(panelVenta, "panelVentas");
        jPanelBody.add(panelPersonas, "panelPersonas");
        jPanelBody.add(panelEstadistica, "panelEstadistica");
        jPanelBody.add(panelFacturas, "panelFacturas");
        jPanelBody.add(panelAyuda, "panelAyuda");

        menuAjustes.addMenuListener(this);
        menuLibro.addMenuListener(this);
        menuCaja.addMenuListener(this);
        menuInventario.addMenuListener(this);
        menuCompras.addMenuListener(this);
        menuVentas.addMenuListener(this);
        menuPersonas.addMenuListener(this);
        menuEstadistica.addMenuListener(this);
        menuFacturas.addMenuListener(this);
        menuAyuda.addMenuListener(this);

        jLabelVersion.setText(Main.GET_VERSION() + "");
    }

    public static double GET_VERSION() {
        return Main.VERSION;
    }
//
//    private void conexion() {
//        logica = new LogicaNegocio();
//    }
//
//    private void conexion(String nombre, String pass, String host, int puerto, String nombreBBDD) {
//        logica = new LogicaNegocio(this, nombre, pass, host, puerto, nombreBBDD);
//        if (!logica.conectBBDD(nombre, pass, host, puerto, nombreBBDD)) {
//            System.exit(0);
//            logger.error("Conexion NO establecida. Exit !");
//        }
//
//    }

    public static void actualizarPanelCaja() {
        ((PanelCaja) panelCaja).actualizarCaja();
    }

    public static void setLookAndFeel(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(panelAjustes);
            SwingUtilities.updateComponentTreeUI(panelAyuda);
            SwingUtilities.updateComponentTreeUI(panelCaja);
            SwingUtilities.updateComponentTreeUI(panelCompra);
            SwingUtilities.updateComponentTreeUI(panelEstadistica);
            SwingUtilities.updateComponentTreeUI(panelFacturas);
            SwingUtilities.updateComponentTreeUI(panelInventario);
            SwingUtilities.updateComponentTreeUI(panelLibroDiario);
            SwingUtilities.updateComponentTreeUI(panelPersonas);
            SwingUtilities.updateComponentTreeUI(panelVenta);
        } catch (UnsupportedLookAndFeelException ex) {
            Exceptions.printStackTrace(ex);
        }

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
        jPanelBody = new javax.swing.JPanel();
        jPanelBottom = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelVersion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuLibro = new javax.swing.JMenu();
        menuCaja = new javax.swing.JMenu();
        menuInventario = new javax.swing.JMenu();
        menuCompras = new javax.swing.JMenu();
        menuVentas = new javax.swing.JMenu();
        menuPersonas = new javax.swing.JMenu();
        menuEstadistica = new javax.swing.JMenu();
        menuFacturas = new javax.swing.JMenu();
        menuAjustes = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("bahguta");
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(1024, 800));

        jPanelBody.setMinimumSize(new java.awt.Dimension(0, 550));
        jPanelBody.setPreferredSize(new java.awt.Dimension(901, 515));

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanelBody);

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel1.setText("Creado por Plamen Petkov 2019 ®");

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
        jLabel2.setText("Version: ");

        jLabelVersion.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N

        jLabel3.setFont(LogicaTemas.TEXT_FONT);
        jLabel3.setText("bahguta API");

        jLabel4.setFont(LogicaTemas.TEXT_FONT);
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("http://bahguta.ddns.net");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Skudo.png"))); // NOI18N
        jLabel5.setToolTipText("");

        javax.swing.GroupLayout jPanelBottomLayout = new javax.swing.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        menuBar.setInheritsPopupMenu(true);
        menuBar.setRequestFocusEnabled(false);
        menuBar.setVerifyInputWhenFocusTarget(false);

        menuLibro.setBorder(null);
        menuLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/libro.png"))); // NOI18N
        menuLibro.setText("Libro Diario");
        menuLibro.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLibroMouseClicked(evt);
            }
        });
        menuBar.add(menuLibro);

        menuCaja.setBorder(null);
        menuCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja.png"))); // NOI18N
        menuCaja.setText("Caja");
        menuCaja.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuCaja.setHideActionText(true);
        menuCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCajaMouseClicked(evt);
            }
        });
        menuBar.add(menuCaja);

        menuInventario.setBorder(null);
        menuInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inv.png"))); // NOI18N
        menuInventario.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuInventario.setLabel("Inventario");
        menuInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInventarioMouseClicked(evt);
            }
        });
        menuBar.add(menuInventario);

        menuCompras.setBorder(null);
        menuCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/compras.png"))); // NOI18N
        menuCompras.setText("Compras");
        menuCompras.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuComprasMouseClicked(evt);
            }
        });
        menuBar.add(menuCompras);

        menuVentas.setBorder(null);
        menuVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas.png"))); // NOI18N
        menuVentas.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuVentas.setLabel("Ventas");
        menuVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuVentasMouseClicked(evt);
            }
        });
        menuBar.add(menuVentas);

        menuPersonas.setBorder(null);
        menuPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
        menuPersonas.setText("Personas");
        menuPersonas.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuPersonas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuPersonas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuPersonas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPersonasMouseClicked(evt);
            }
        });
        menuBar.add(menuPersonas);

        menuEstadistica.setBorder(null);
        menuEstadistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/estadistica.png"))); // NOI18N
        menuEstadistica.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuEstadistica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuEstadistica.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuEstadistica.setLabel("Estadistica");
        menuEstadistica.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuEstadistica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEstadisticaMouseClicked(evt);
            }
        });
        menuBar.add(menuEstadistica);

        menuFacturas.setBorder(null);
        menuFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/facturas.png"))); // NOI18N
        menuFacturas.setText("Facturas");
        menuFacturas.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuFacturas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuFacturas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuFacturas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFacturasMouseClicked(evt);
            }
        });
        menuBar.add(menuFacturas);

        menuAjustes.setBorder(null);
        menuAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ajustes.png"))); // NOI18N
        menuAjustes.setText("Ajustes");
        menuAjustes.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuAjustes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuAjustes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuAjustes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuAjustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAjustesMouseClicked(evt);
            }
        });
        menuBar.add(menuAjustes);

        menuAyuda.setBorder(null);
        menuAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/info.png"))); // NOI18N
        menuAyuda.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        menuAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuAyuda.setLabel("Ayuda");
        menuAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAyudaMouseClicked(evt);
            }
        });
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1)
                .addGap(11, 11, 11))
            .addComponent(jPanelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void menuLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLibroMouseClicked
        cardLayout.show(jPanelBody, "panelLibroDiario");
    }//GEN-LAST:event_menuLibroMouseClicked

    private void menuCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCajaMouseClicked
        cardLayout.show(jPanelBody, "panelCaja");
    }//GEN-LAST:event_menuCajaMouseClicked

    private void menuInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInventarioMouseClicked
        cardLayout.show(jPanelBody, "panelInventario");
    }//GEN-LAST:event_menuInventarioMouseClicked

    private void menuVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuVentasMouseClicked
        cardLayout.show(jPanelBody, "panelVentas");
    }//GEN-LAST:event_menuVentasMouseClicked

    private void menuComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuComprasMouseClicked
        cardLayout.show(jPanelBody, "panelCompras");
    }//GEN-LAST:event_menuComprasMouseClicked

    private void menuAjustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAjustesMouseClicked
        cardLayout.show(jPanelBody, "panelAjustes");
    }//GEN-LAST:event_menuAjustesMouseClicked

    private void menuPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPersonasMouseClicked
        cardLayout.show(jPanelBody, "panelPersonas");
    }//GEN-LAST:event_menuPersonasMouseClicked

    private void menuEstadisticaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEstadisticaMouseClicked
        cardLayout.show(jPanelBody, "panelEstadistica");
    }//GEN-LAST:event_menuEstadisticaMouseClicked

    private void menuFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFacturasMouseClicked
        cardLayout.show(jPanelBody, "panelFacturas");
    }//GEN-LAST:event_menuFacturasMouseClicked

    private void menuAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAyudaMouseClicked
        cardLayout.show(jPanelBody, "panelAyuda");
    }//GEN-LAST:event_menuAyudaMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        try {
            URI url = new URI("https://bahguta.ddns.net");
            Desktop.getDesktop().browse(url);
        } catch (URISyntaxException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ParseException, UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        javax.swing.UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel()); //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            PropertyConfigurator.configure("lib/log4j.properties");
            new Main().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelVersion;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAjustes;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCaja;
    private javax.swing.JMenu menuCompras;
    private javax.swing.JMenu menuEstadistica;
    private javax.swing.JMenu menuFacturas;
    private javax.swing.JMenu menuInventario;
    private javax.swing.JMenu menuLibro;
    private javax.swing.JMenu menuPersonas;
    private javax.swing.JMenu menuVentas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void menuSelected(MenuEvent me) {
    }

    @Override
    public void menuDeselected(MenuEvent me) {
    }

    @Override
    public void menuCanceled(MenuEvent me) {
    }

    /**
     * Metodo para imprimir el logo por consola
     */
    public void imprimirLogo() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                    File banner = new File("lib/banner.txt");
                    BufferedReader br = new BufferedReader(new FileReader(banner));
                    String st;
                    while ((st = br.readLine()) != null) {
                        logger.info(st);
                    }
                } catch (IOException e) {

                    logger.error(e.getMessage());
                }
            }
        });

    }

}
