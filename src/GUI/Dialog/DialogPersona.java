/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialog;

import Dto.Persona;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *  Dialog para el manejo de una persona.
 * 
 * @author Plam
 */
public class DialogPersona extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private List<JLabel> listaLabelsH2;
    private List<JLabel> listaLabelsH1;
    private Persona persona;
    private LogicaNegocio logica;

    /**
     * Constructor
     */
    public DialogPersona(java.awt.Frame parent, boolean modal, LogicaNegocio logica, Persona persona) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.logica = logica;
        this.persona = persona;
        if (this.persona != null) {
            jLabel1H1DialogCliente.setText(this.persona.getNombre() + " " + this.persona.getApellido());
            jLabel1H2CodCliente.setText("" + this.persona.getCodPersona());
            jTextFieldNombreCliente.setText(this.persona.getNombre());
            jTextFieldApellidoNuevoCliente.setText(this.persona.getApellido());
            jTextFieldDireccionCliente.setText(this.persona.getDireccion());
            jTextFieldTelefonoCliente.setText(this.persona.getTelefono() + "");
            jTextFieldEmailCliente.setText(this.persona.getEmail());

        } else {
            jLabel1H2CodCliente.setText("----");
        }

        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel1H1DialogCliente);

        listaLabelsH2 = new ArrayList<>();
        listaLabelsH2.add(jLabel1H2);
        listaLabelsH2.add(jLabel2H2);
        listaLabelsH2.add(jLabel3H2);
        listaLabelsH2.add(jLabel4H2);
        listaLabelsH2.add(jLabel7H2);
        listaLabelsH2.add(jLabel1H2CodCliente);

        LogicaTemas.addListJLabel("JLabelH1NuevaNota", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2DialogCliente", listaLabelsH2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldDireccionCliente = new javax.swing.JTextField();
        jTextFieldTelefonoCliente = new javax.swing.JTextField();
        jTextFieldEmailCliente = new javax.swing.JTextField();
        jButtonOKCliente = new javax.swing.JButton();
        jButtonCancelarCliente = new javax.swing.JButton();
        jLabel1H2 = new javax.swing.JLabel();
        jLabel4H2 = new javax.swing.JLabel();
        jLabel2H2 = new javax.swing.JLabel();
        jLabel3H2 = new javax.swing.JLabel();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        jLabel7H2 = new javax.swing.JLabel();
        jLabel1H2CodCliente = new javax.swing.JLabel();
        jLabel1H1DialogCliente = new javax.swing.JLabel();
        jLabel6H2 = new javax.swing.JLabel();
        jTextFieldApellidoNuevoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextFieldDireccionCliente.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldDireccionCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jTextFieldDireccionCliente.text")); // NOI18N

        jTextFieldTelefonoCliente.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldTelefonoCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jTextFieldTelefonoCliente.text")); // NOI18N

        jTextFieldEmailCliente.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldEmailCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jTextFieldEmailCliente.text")); // NOI18N

        jButtonOKCliente.setFont(LogicaTemas.BUTTON_FONT);
        jButtonOKCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jButtonOKCliente.text")); // NOI18N
        jButtonOKCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKClienteActionPerformed(evt);
            }
        });

        jButtonCancelarCliente.setFont(LogicaTemas.BUTTON_FONT);
        jButtonCancelarCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jButtonCancelarCliente.text")); // NOI18N
        jButtonCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarClienteActionPerformed(evt);
            }
        });

        jLabel1H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel1H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel1H2.text")); // NOI18N

        jLabel4H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel4H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel4H2.text")); // NOI18N

        jLabel2H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel2H2.text")); // NOI18N

        jLabel3H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel3H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel3H2.text")); // NOI18N

        jTextFieldNombreCliente.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldNombreCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jTextFieldNombreCliente.text")); // NOI18N

        jLabel7H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel7H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel7H2.text")); // NOI18N

        jLabel1H2CodCliente.setFont(LogicaTemas.TEXT_FONT);
        jLabel1H2CodCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel1H2CodCliente.text")); // NOI18N

        jLabel1H1DialogCliente.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel1H1DialogCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel1H1DialogCliente.text")); // NOI18N

        jLabel6H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel6H2.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel6H2.text")); // NOI18N

        jTextFieldApellidoNuevoCliente.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldApellidoNuevoCliente.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jTextFieldApellidoNuevoCliente.text")); // NOI18N

        jLabel1.setFont(LogicaTemas.TEXT_FONT);
        jLabel1.setText(org.openide.util.NbBundle.getMessage(DialogPersona.class, "DialogPersona.jLabel1.text")); // NOI18N

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CLIENTE", "PROVEEDOR" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1H1DialogCliente)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7H2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jLabel1H2CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4H2)
                            .addComponent(jLabel2H2)
                            .addComponent(jLabel3H2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTelefonoCliente)
                            .addComponent(jTextFieldEmailCliente)
                            .addComponent(jTextFieldDireccionCliente, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCancelarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonOKCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombreCliente)
                            .addComponent(jTextFieldApellidoNuevoCliente)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1H1DialogCliente)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7H2)
                    .addComponent(jLabel1H2CodCliente))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1H2)
                    .addComponent(jTextFieldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6H2)
                    .addComponent(jTextFieldApellidoNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4H2)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2H2)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3H2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextFieldDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOKCliente)
                    .addComponent(jButtonCancelarCliente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para añadir una persona como cliente en la base de datos
     * @param evt 
     */
    private void jButtonOKClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKClienteActionPerformed
        if (!logica.isConexionExitosa()) {
            return;
        }
        String nombre = jTextFieldNombreCliente.getText();
        String apellido = jTextFieldApellidoNuevoCliente.getText();
        String email = jTextFieldEmailCliente.getText();
        String direccion = jTextFieldDireccionCliente.getText();
        String tlf = jTextFieldTelefonoCliente.getText();
        long telefono = 0;
        try {
            telefono = new Long(tlf);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || direccion.isEmpty() || tlf.isEmpty()) {
            return;
        }
        
        String tipo = (String) jComboBoxTipo.getSelectedItem();
        if (persona == null) {
            System.out.println(nombre + " " + apellido + " " + direccion + " " + email + " " + telefono + " " + tipo);
            logica.addPersona(nombre, apellido, direccion, telefono, email, tipo);
        } else {
            this.persona.setNombre(nombre);
            this.persona.setApellido(apellido);
            this.persona.setEmail(email);
            this.persona.setDireccion(direccion);
            this.persona.setTelefono(telefono);
            logica.modificarPersona(this.persona);
        }
        dispose();
    }//GEN-LAST:event_jButtonOKClienteActionPerformed

    /**
     * Metodo para cerrar el dialogo
     * 
     * @param evt 
     */
    private void jButtonCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarClienteActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarClienteActionPerformed

    public static DialogPersona newInstance(JFrame parent, boolean modal, LogicaNegocio logica, Persona persona) {
        return new DialogPersona(parent, modal, logica, persona);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarCliente;
    private javax.swing.JButton jButtonOKCliente;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1H1DialogCliente;
    private javax.swing.JLabel jLabel1H2;
    private javax.swing.JLabel jLabel1H2CodCliente;
    private javax.swing.JLabel jLabel2H2;
    private javax.swing.JLabel jLabel3H2;
    private javax.swing.JLabel jLabel4H2;
    private javax.swing.JLabel jLabel6H2;
    private javax.swing.JLabel jLabel7H2;
    private javax.swing.JTextField jTextFieldApellidoNuevoCliente;
    private javax.swing.JTextField jTextFieldDireccionCliente;
    private javax.swing.JTextField jTextFieldEmailCliente;
    private javax.swing.JTextField jTextFieldNombreCliente;
    private javax.swing.JTextField jTextFieldTelefonoCliente;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DialogPersona.class);
}
