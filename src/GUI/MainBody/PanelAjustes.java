/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainBody;

import GUI.Main.Main;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import org.openide.util.Exceptions;

/**
 *
 * @author Plam
 */
public class PanelAjustes extends javax.swing.JPanel {
    
    private List<LookAndFeel> listaLaf;
    private List<JLabel> listaLabelsH1;
    private List<JLabel> listaLabelsH2;
    private LogicaNegocio logica;

    /**
     * Creates new form PanelAjustes
     */
    public PanelAjustes(JFrame frame, LogicaNegocio logica) {
        initComponents();
        setBorder(LogicaTemas.GET_TITLE_BORDER("Ajustes"));
        
        this.logica = logica;
        
        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel10H1);
        listaLabelsH1.add(jLabel8);
        listaLabelsH1.add(jLabel4H1);
        listaLabelsH1.add(jLabel1);
        
        listaLabelsH2 = new ArrayList<>();
       // listaLabelsH2.add(jLabel11H2);
        //listaLabelsH2.add(jLabel12H2);
        listaLabelsH2.add(jLabel9);
        listaLabelsH2.add(jLabel3H2);
        listaLabelsH2.add(jLabel5H2);
        listaLabelsH2.add(jLabel6H2);
        listaLabelsH2.add(jLabel7H2);
        listaLabelsH2.add(jLabel8H2);
        listaLabelsH2.add(jLabel9H2);
        listaLabelsH2.add(jLabelH2);
        listaLabelsH2.add(jLabel10H2);
        
        listaLaf = new ArrayList<>();
        try {
            listaLaf.add(new SyntheticaBlackEyeLookAndFeel());
            listaLaf.add(new SyntheticaBlackMoonLookAndFeel());
            listaLaf.add(new SyntheticaBlueIceLookAndFeel());
            listaLaf.add(new SyntheticaBlueMoonLookAndFeel());
            listaLaf.add(new SyntheticaBlueSteelLookAndFeel());
            listaLaf.add(new SyntheticaMauveMetallicLookAndFeel());
            listaLaf.add(new SyntheticaSilverMoonLookAndFeel());
            listaLaf.add(new SyntheticaSkyMetallicLookAndFeel());
            listaLaf.add(new SyntheticaStandardLookAndFeel());
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        LogicaTemas.addListJLabel("JLabelH1Ajustes", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2Ajustes", listaLabelsH2);
        
        DefaultComboBoxModel <String> dcm = new DefaultComboBoxModel<>();
        for (LookAndFeel lfi : listaLaf) {
            dcm.addElement(lfi.getName());
        }
        comboboxLookAndFeel.setModel(dcm);
        //setMinimumSize(new Dimension(Main.MIN_LARGO, Main.MIN_ALTO));
        
        jTextFieldHost1.setText(logica.getHost());
        jTextFieldPass1.setText(logica.getPass());
        jTextFieldBBDDName1.setText(logica.getBBDDName());
        jTextFieldPuerto1.setText(logica.getPuerto() + "");
        jTextFieldUsuario1.setText(logica.getUser());
        
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
        jTextPane1 = new javax.swing.JTextPane();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4H1 = new javax.swing.JLabel();
        jLabel5H2 = new javax.swing.JLabel();
        jLabel6H2 = new javax.swing.JLabel();
        jLabel7H2 = new javax.swing.JLabel();
        jLabel8H2 = new javax.swing.JLabel();
        jCheckBox1H2 = new javax.swing.JCheckBox();
        jLabel9H2 = new javax.swing.JLabel();
        jButton1btn1 = new javax.swing.JButton();
        jTextFieldHost1 = new javax.swing.JTextField();
        jTextFieldPuerto1 = new javax.swing.JTextField();
        jTextFieldBBDDName1 = new javax.swing.JTextField();
        jLabel10H1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10H2 = new javax.swing.JLabel();
        jTextFieldUsuario1 = new javax.swing.JTextField();
        jLabelH2 = new javax.swing.JLabel();
        comboboxLookAndFeel = new javax.swing.JComboBox<>();
        jLabel3H2 = new javax.swing.JLabel();
        sliderTexto1 = new javax.swing.JSlider();
        jTextFieldPass1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldPassAdmin = new javax.swing.JPasswordField();
        jButtonIdentificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        jScrollPane1.setViewportView(jTextPane1);

        jLabel4H1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel4H1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel4H1.setText("Base de datos");

        jLabel5H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel5H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5H2.setText("Host:");

        jLabel6H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel6H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6H2.setText("Puerto:");

        jLabel7H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel7H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7H2.setText("BD name:");

        jLabel8H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel8H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8H2.setText("Contraseña:");

        jCheckBox1H2.setFont(LogicaTemas.TEXT_FONT);
        jCheckBox1H2.setForeground(new java.awt.Color(0, 102, 153));
        jCheckBox1H2.setText("Copiar todos los registros ?");
        jCheckBox1H2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel9H2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9H2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9H2.setText("( Se ejecutara script para crear todas las tablas y registros )");
        jLabel9H2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jButton1btn1.setFont(LogicaTemas.BUTTON_FONT);
        jButton1btn1.setText("Conectar");
        jButton1btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1btn1ActionPerformed(evt);
            }
        });

        jTextFieldHost1.setFont(LogicaTemas.TEXT_FONT);

        jTextFieldPuerto1.setFont(LogicaTemas.TEXT_FONT);

        jTextFieldBBDDName1.setFont(LogicaTemas.TEXT_FONT);

        jLabel10H1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel10H1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel10H1.setText("Copia de seguridad");

        jButton5.setFont(LogicaTemas.BUTTON_FONT);
        jButton5.setText("Crear copia de seguridad");

        jButton6.setFont(LogicaTemas.BUTTON_FONT);
        jButton6.setText("Importar copia de seguridad");

        jButton7.setFont(LogicaTemas.BUTTON_FONT);
        jButton7.setText("Borrar Todos los Registros");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel10H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10H2.setText("Usuario:");

        jTextFieldUsuario1.setFont(LogicaTemas.TEXT_FONT);

        jLabelH2.setFont(LogicaTemas.TEXT_FONT);
        jLabelH2.setForeground(new java.awt.Color(0, 102, 153));
        jLabelH2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelH2.setText("Cambiar Vista");

        comboboxLookAndFeel.setFont(LogicaTemas.TEXT_FONT);
        comboboxLookAndFeel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxLookAndFeel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxLookAndFeelActionPerformed(evt);
            }
        });

        jLabel3H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel3H2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3H2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3H2.setText("Tamaño del Texto");

        sliderTexto1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderTexto1StateChanged(evt);
            }
        });

        jTextFieldPass1.setFont(LogicaTemas.TEXT_FONT);

        jLabel3.setFont(LogicaTemas.TEXT_FONT);
        jLabel3.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Identifica te como Administrador");

        jPasswordFieldPassAdmin.setFont(LogicaTemas.TEXT_FONT);
        jPasswordFieldPassAdmin.setToolTipText("Constraseña");

        jButtonIdentificar.setFont(LogicaTemas.TEXT_FONT);
        jButtonIdentificar.setText("Identifica me");
        jButtonIdentificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIdentificarActionPerformed(evt);
            }
        });

        jLabel4.setFont(LogicaTemas.TEXT_FONT);
        jLabel4.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Cambia la contraseña del Administrador");

        jLabel5.setFont(LogicaTemas.TEXT_FONT);
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Contraseña actual");

        jLabel6.setFont(LogicaTemas.TEXT_FONT);
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Nueva Contraseña");

        jLabel7.setFont(LogicaTemas.TEXT_FONT);
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Confirma la Constraseña");

        jPasswordField1.setFont(LogicaTemas.TEXT_FONT);

        jPasswordField2.setFont(LogicaTemas.TEXT_FONT);

        jPasswordField3.setFont(LogicaTemas.TEXT_FONT);

        jButton1.setFont(LogicaTemas.BUTTON_FONT);
        jButton1.setText("Cambiar Contraseña");

        jLabel1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("Administrar Usuarios y Contraseñas");

        jLabel8.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel8.setForeground(new java.awt.Color(204, 51, 0));
        jLabel8.setText("Estilos del Programa");

        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Contraseña");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sliderTexto1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                    .addComponent(comboboxLookAndFeel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel10H1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4H1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9H2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10H2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldUsuario1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8H2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldPass1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldHost1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6H2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldPuerto1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7H2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldBBDDName1))))
                            .addComponent(jCheckBox1H2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField1)
                                    .addComponent(jPasswordField3)))
                            .addComponent(jButtonIdentificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldPassAdmin)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPasswordFieldPassAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonIdentificar)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10H1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelH2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboboxLookAndFeel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3H2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(sliderTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton6)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton7))))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4H1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5H2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHost1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6H2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPuerto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7H2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldBBDDName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10H2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8H2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1H2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9H2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1btn1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIdentificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIdentificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIdentificarActionPerformed

    private void sliderTexto1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderTexto1StateChanged
        int value = sliderTexto1.getValue();
        LogicaTemas.setTextSize(value / 3, value / 4);
    }//GEN-LAST:event_sliderTexto1StateChanged

    private void comboboxLookAndFeelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxLookAndFeelActionPerformed
        Main.setLookAndFeel(listaLaf.get(comboboxLookAndFeel.getSelectedIndex()));
    }//GEN-LAST:event_comboboxLookAndFeelActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1btn1ActionPerformed
        try {
            String nombre = jTextFieldUsuario1.getText();
            String host = jTextFieldHost1.getText();
            String nombreBBDD = jTextFieldBBDDName1.getText();
            String pass = String.valueOf(jTextFieldPass1.getPassword());
            int puerto = Integer.valueOf(jTextFieldPuerto1.getText());

            if (logica.cambiarConexion(nombre, pass, host, puerto, nombreBBDD)) {
                JOptionPane.showMessageDialog(this,"Conexion cambiada con exito !");
            } else {
                JOptionPane.showMessageDialog(this,"Conexion NO cambiada, revisa los datos !");
            }
        } catch (NumberFormatException e) {
            e.getMessage();
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, "Error al intentar conectar a la base de datos.\\nRevisa los datos y intente de nuevo");
        }
    }//GEN-LAST:event_jButton1btn1ActionPerformed
    
    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
    
    Graphics2D g;
    
    @Override
    public void print(Graphics grphcs) {
        super.print(grphcs); //To change body of generated methods, choose Tools | Templates.
        g = (Graphics2D) grphcs;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboboxLookAndFeel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton1btn1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonIdentificar;
    private javax.swing.JCheckBox jCheckBox1H2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10H1;
    private javax.swing.JLabel jLabel10H2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3H2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4H1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5H2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel6H2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel7H2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel8H2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel9H2;
    private javax.swing.JLabel jLabelH2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordFieldPassAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldBBDDName1;
    private javax.swing.JTextField jTextFieldHost1;
    private javax.swing.JPasswordField jTextFieldPass1;
    private javax.swing.JTextField jTextFieldPuerto1;
    private javax.swing.JTextField jTextFieldUsuario1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JSlider sliderTexto1;
    // End of variables declaration//GEN-END:variables
}
