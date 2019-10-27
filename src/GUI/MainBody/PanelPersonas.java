/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainBody;

import GUI.Dialog.DialogFactura;
import GUI.Dialog.DialogPersona;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import TableModels.FacturaTableModel;
import TableModels.PersonasTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Panel Personas
 *
 * @author Plam
 */
public class PanelPersonas extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private List<JLabel> listaLabelsH1;
    private List<JLabel> listaLabelsH2;
    private JFrame parent;
    private LogicaNegocio logica;
    private FacturaTableModel facturaTableModel;
    private PersonasTableModel personaTableModel;

    /**
     * Constructor
     */
    public PanelPersonas(JFrame parent, LogicaNegocio logica) {
        this.logica = logica;
        initComponents();
        this.parent = parent;

        setBorder(LogicaTemas.GET_TITLE_BORDER("Personas"));

        personaTableModel = new PersonasTableModel(logica.getListaPersonas());
        jTablePersonas.setModel(personaTableModel);
        facturaTableModel = new FacturaTableModel(null);

        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel1H1);

        listaLabelsH2 = new ArrayList<>();
        listaLabelsH2.add(jLabel1H2);
        listaLabelsH2.add(jLabel2H2);
        
        LogicaTemas.addJTable(jTableFacturas);
        LogicaTemas.addJTable(jTablePersonas);

        LogicaTemas.addListJLabel("JLabelH1Personas", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2Personas", listaLabelsH2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePersonas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jButtonNuevaPersona = new javax.swing.JButton();
        jButtonVerFactura = new javax.swing.JButton();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel1H1 = new javax.swing.JLabel();
        jButtonImprimirFactura = new javax.swing.JButton();
        jLabel2H2 = new javax.swing.JLabel();
        jLabel1H2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButtoneditarPersona = new javax.swing.JButton();
        jButtonborrarPersona = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(901, 500));

        jTablePersonas.setFont(LogicaTemas.TEXT_FONT);
        jTablePersonas.setModel(new TableModels.PersonasTableModel(logica.getListaPersonas()));
        jTablePersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePersonasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTablePersonas);

        jTableFacturas.setFont(LogicaTemas.TEXT_FONT);
        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFacturasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableFacturas);

        jButtonNuevaPersona.setFont(LogicaTemas.BUTTON_FONT);
        jButtonNuevaPersona.setText("Nueva Persona");
        jButtonNuevaPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaPersonaActionPerformed(evt);
            }
        });

        jButtonVerFactura.setFont(LogicaTemas.BUTTON_FONT);
        jButtonVerFactura.setText("Ver Factura");
        jButtonVerFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFacturaActionPerformed(evt);
            }
        });

        jComboBox.setFont(LogicaTemas.TEXT_FONT);
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Clientes", "Proveedores" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel1H1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel1H1.setText("Filtrar por Cliente / Proveedor");

        jButtonImprimirFactura.setFont(LogicaTemas.BUTTON_FONT);
        jButtonImprimirFactura.setText("Imprimir Factura");
        jButtonImprimirFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirFacturaActionPerformed(evt);
            }
        });

        jLabel2H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2H2.setText("Facturas");

        jLabel1H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel1H2.setText("Cliente / Proveedor");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtoneditarPersona.setFont(LogicaTemas.BUTTON_FONT);
        jButtoneditarPersona.setText("Editar");
        jButtoneditarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoneditarPersonaActionPerformed(evt);
            }
        });

        jButtonborrarPersona.setFont(LogicaTemas.BUTTON_FONT);
        jButtonborrarPersona.setText("Borrar");
        jButtonborrarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonborrarPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1H1)
                .addGap(27, 27, 27)
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1H2)
                        .addGap(357, 357, 357))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonborrarPersona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtoneditarPersona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonNuevaPersona))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonVerFactura)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonImprimirFactura)))
                    .addComponent(jLabel2H2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1H1)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1H2))
                            .addComponent(jLabel2H2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonImprimirFactura)
                                    .addComponent(jButtonVerFactura)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonNuevaPersona, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtoneditarPersona, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonborrarPersona, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para manejar los click de la table de las personas
     * @param evt 
     */
    private void jTablePersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonasMouseClicked
        int ID_Persona = (int) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
        if (ID_Persona == -1) {
            return;
        } else if (ID_Persona != -1 && evt.getClickCount() >= 2) {
            DialogPersona dialog = new DialogPersona(parent, true, logica, logica.getPersonaPorID(ID_Persona));
            dialog.setVisible(true);

        }
        jTableFacturas.setModel(new FacturaTableModel(logica.getListaFacturas(ID_Persona)));
        jTablePersonas.setModel(new PersonasTableModel(logica.getListaPersonas()));
    }//GEN-LAST:event_jTablePersonasMouseClicked

    /**
     * Metodo para crear una nueva persona. Se abre ventana nueva
     * @param evt 
     */
    private void jButtonNuevaPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaPersonaActionPerformed
        DialogPersona dialog = new DialogPersona(parent, true, logica, null);
        dialog.setVisible(true);
        jTablePersonas.setModel(new PersonasTableModel(logica.getListaPersonas()));
    }//GEN-LAST:event_jButtonNuevaPersonaActionPerformed

    /**
     * Metodo para ver la informacion de una factura
     * @param evt 
     */
    private void jButtonVerFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFacturaActionPerformed
        if (jTableFacturas.getSelectedRow() == -1) {
            return;
        }
        int idFactura = (int) jTableFacturas.getValueAt(jTableFacturas.getSelectedRow(), 0);
        if (idFactura == -1) {
            return;
        }
        DialogFactura dialog = new DialogFactura(parent, true, logica, logica.getFacturaPorID(idFactura));
        dialog.setVisible(true);
    }//GEN-LAST:event_jButtonVerFacturaActionPerformed

    /**
     * Metodo par aobtener las personas segun el valor de jComboBox<br>
     * Clientes, Proveedores o todos.
     * @param evt 
     */
    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        if (jComboBox.getSelectedItem().toString().equalsIgnoreCase("Clientes")) {
            jTablePersonas.setModel(new PersonasTableModel(logica.getListaClientes()));
        } else if (jComboBox.getSelectedItem().toString().equalsIgnoreCase("Proveedores")) {
            jTablePersonas.setModel(new PersonasTableModel(logica.getListaProveedores()));
        } else {
            jTablePersonas.setModel(new PersonasTableModel(logica.getListaPersonas()));
        }
    }//GEN-LAST:event_jComboBoxActionPerformed

    /**
     * Metodo para imprimir en pantalla una factura, genera un pdf
     * @param evt 
     */
    private void jButtonImprimirFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirFacturaActionPerformed
        if (jTableFacturas.getSelectedRow() == -1) {
            return;
        }
        int idFactura = (int) jTableFacturas.getValueAt(jTableFacturas.getSelectedRow(), 0);
        if (idFactura != -1) {
            logica.imprimirInformeFactura(logica.getFacturaPorID(idFactura));
        }
    }//GEN-LAST:event_jButtonImprimirFacturaActionPerformed

    /**
     * Metodo para editar una persona 
     * @param evt 
     */
    private void jButtoneditarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoneditarPersonaActionPerformed
        if (jTablePersonas.getSelectedRow() == -1) {
            return;
        }
        int ID_persona = (int) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
        if (logica.getPersonaPorID(ID_persona) != null) {
            DialogPersona dialog = new DialogPersona(parent, true, logica, logica.getPersonaPorID(ID_persona));
            dialog.setVisible(true);
        }
        jTablePersonas.setModel(new PersonasTableModel(logica.getListaPersonas()));
    }//GEN-LAST:event_jButtoneditarPersonaActionPerformed

    /**
     * Metodo para borrar una persona. Se borra tambien de la base de datos
     * @param evt 
     */
    private void jButtonborrarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonborrarPersonaActionPerformed
        if (jTablePersonas.getSelectedRow() == -1) {
            return;
        }
        int ID_persona = (int) jTablePersonas.getValueAt(jTablePersonas.getSelectedRow(), 0);
        if (ID_persona != -1) {
            int input = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres borrar persona con ID " + ID_persona, "Borrar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                logica.borrarPersona(ID_persona);
            }
        }
        jTablePersonas.setModel(new PersonasTableModel(logica.getListaPersonas()));
    }//GEN-LAST:event_jButtonborrarPersonaActionPerformed

    /**
     * Metodo para manejar los clicks de la tabla de las facturas
     * @param evt 
     */
    private void jTableFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFacturasMouseClicked
        int idFactura = (int) jTableFacturas.getValueAt(jTableFacturas.getSelectedRow(), 0);
        if (idFactura != -1 && evt.getClickCount() >= 2) {
            DialogFactura dialog = new DialogFactura(parent, true, logica, logica.getFacturaPorID(idFactura));
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_jTableFacturasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimirFactura;
    private javax.swing.JButton jButtonNuevaPersona;
    private javax.swing.JButton jButtonVerFactura;
    private javax.swing.JButton jButtonborrarPersona;
    private javax.swing.JButton jButtoneditarPersona;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1H1;
    private javax.swing.JLabel jLabel1H2;
    private javax.swing.JLabel jLabel2H2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableFacturas;
    private javax.swing.JTable jTablePersonas;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PanelPersonas.class);
}
