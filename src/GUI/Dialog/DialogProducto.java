/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialog;

import Dto.Producto;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Dialog para el manejo de un producto
 * 
 * @author Plam
 */
public class DialogProducto extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private List<JLabel> listaLabelsH2;
    private List<JLabel> listaLabelsH1;
    private LogicaNegocio logica;
    private Producto producto;

    /**
     * Constructor
     */
    public DialogProducto(java.awt.Frame parent, boolean modal, LogicaNegocio logica, Producto producto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.logica = logica;
        this.producto = producto;
        if (this.producto != null) {
            jLabel1H1DialogProducto.setText("Editar Producto");
            jLabel1H2CodProducto.setText(this.producto.getCodProducto() + "");
            jTextFieldNombre.setText(this.producto.getNombre());
            jTextFieldPeso.setText(this.producto.getPeso() + "");
            jTextFieldPrecio.setText(this.producto.getPrecio() + "");
            jTextFieldCantidad.setText(this.producto.getCantidad() + "");
        } else {
            jLabel1H1DialogProducto.setText("Nuevo Producto");
            jLabel1H2CodProducto.setText("----");
        }

        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel1H2);

        listaLabelsH2 = new ArrayList<>();
        listaLabelsH2.add(jLabel1H2);
        listaLabelsH2.add(jLabel2H2);
        listaLabelsH2.add(jLabel3H2);
        listaLabelsH2.add(jLabel4H2);
        listaLabelsH2.add(jLabel11H2);
        listaLabelsH2.add(jLabel1H2CodProducto);

        LogicaTemas.addListJLabel("JLabelH1NuevaNota", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2DialogProducto", listaLabelsH2);
    }

    /**
     * Metodo para obtener una nueva instancia de DialogProducto.class
     * 
     * @param parent
     * @param modal
     * @param logica
     * @param producto
     * @return 
     */
    public static DialogProducto newInstance(JFrame parent, boolean modal, LogicaNegocio logica, Producto producto) {
        return new DialogProducto(parent, modal, logica, producto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1H2 = new javax.swing.JLabel();
        jLabel2H2 = new javax.swing.JLabel();
        jLabel3H2 = new javax.swing.JLabel();
        jLabel4H2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldPeso = new javax.swing.JTextField();
        jTextFieldPrecio = new javax.swing.JTextField();
        btnOKProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        jLabel1H2CodProducto = new javax.swing.JLabel();
        jLabel1H1DialogProducto = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jLabel11H2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel1H2.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel1H2.text")); // NOI18N

        jLabel2H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2H2.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel2H2.text")); // NOI18N

        jLabel3H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel3H2.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel3H2.text")); // NOI18N

        jLabel4H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel4H2.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel4H2.text")); // NOI18N

        jTextFieldNombre.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldNombre.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jTextFieldNombre.text")); // NOI18N

        jTextFieldPeso.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldPeso.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jTextFieldPeso.text")); // NOI18N

        jTextFieldPrecio.setFont(LogicaTemas.TEXT_FONT);
        jTextFieldPrecio.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jTextFieldPrecio.text")); // NOI18N

        btnOKProducto.setFont(LogicaTemas.BUTTON_FONT);
        btnOKProducto.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.btnOKProducto.text")); // NOI18N
        btnOKProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKProductoActionPerformed(evt);
            }
        });

        btnCancelarProducto.setFont(LogicaTemas.BUTTON_FONT);
        btnCancelarProducto.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.btnCancelarProducto.text")); // NOI18N
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        jLabel1H2CodProducto.setFont(LogicaTemas.TEXT_FONT);
        jLabel1H2CodProducto.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel1H2CodProducto.text")); // NOI18N

        jLabel1H1DialogProducto.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel1H1DialogProducto.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel1H1DialogProducto.text")); // NOI18N

        jTextFieldCantidad.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jTextFieldCantidad.text")); // NOI18N

        jLabel11H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel11H2.setText(org.openide.util.NbBundle.getMessage(DialogProducto.class, "DialogProducto.jLabel11H2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 219, Short.MAX_VALUE)
                        .addComponent(btnCancelarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOKProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2H2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jTextFieldPeso)
                            .addComponent(jLabel1H2CodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPrecio)
                            .addComponent(jTextFieldCantidad))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1H1DialogProducto)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1H1DialogProducto)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1H2)
                    .addComponent(jLabel1H2CodProducto))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2H2)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3H2)
                    .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4H2)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11H2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOKProducto)
                    .addComponent(btnCancelarProducto))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para cerrar el dialogo
     * @param evt 
     */
    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    /**
     * Metodo para añadir un nuevo producto a la base de datos
     * @param evt 
     */
    private void btnOKProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKProductoActionPerformed
        if (!logica.isConexionExitosa()) {
            return;
        }
        try {
            String nombre = jTextFieldNombre.getText();
            String peso = jTextFieldPeso.getText();
            String precio = jTextFieldPrecio.getText();
            String cantidad = jTextFieldCantidad.getText();

            
            if (nombre.isEmpty() || peso.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
                return;
            }

            if (this.producto != null) {
                this.producto.setNombre(nombre);
                this.producto.setPeso(Double.valueOf(peso));
                this.producto.setPrecio(Double.valueOf(precio));
                this.producto.setCantidad(Integer.valueOf(cantidad));
                logica.modificarProducto(this.producto);
            } else {
                logica.addProducto(nombre, Double.valueOf(peso), Double.valueOf(precio), Integer.valueOf(cantidad));
            }
            dispose();
        } catch (NumberFormatException e) {
            e.getMessage();
        }

    }//GEN-LAST:event_btnOKProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnOKProducto;
    private javax.swing.JLabel jLabel11H2;
    private javax.swing.JLabel jLabel1H1DialogProducto;
    private javax.swing.JLabel jLabel1H2;
    private javax.swing.JLabel jLabel1H2CodProducto;
    private javax.swing.JLabel jLabel2H2;
    private javax.swing.JLabel jLabel3H2;
    private javax.swing.JLabel jLabel4H2;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPeso;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DialogProducto.class);
}
