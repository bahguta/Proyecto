/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainBody;

import Dto.NotaLibroDiario;
import GUI.Dialog.DialogNotaLibro;
import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import TableModels.LibroTableModel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import org.openide.util.Exceptions;

/**
 * Panel Libro Diario
 * 
 * @author Plam
 */
public class PanelLibroDiario extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    private GregorianCalendar cal1, cal2;
    private LibroTableModel ltm;
    private List<JLabel> listaLabelsH1;
    private List<JLabel> listaLabelsH2;
    private JFrame frame;
    private LogicaNegocio logica;
    private SimpleDateFormat sdf;

    public PanelLibroDiario(JFrame frame, LogicaNegocio logica) {

        this.frame = frame;
        this.logica = logica;

        ltm = new LibroTableModel(logica.getNotaLibroDiarios());
        
        initComponents();
        setBorder(LogicaTemas.GET_TITLE_BORDER("Libro Diario"));
        
        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel1H1);

        listaLabelsH2 = new ArrayList<>();
        listaLabelsH2.add(jLabel2H2);
        listaLabelsH2.add(jLabel3H2);
        listaLabelsH2.add(jLabel4H2);
        listaLabelsH2.add(jLabel5H2);
        listaLabelsH2.add(lblDebeH2);
        listaLabelsH2.add(lblHaberH2);

        LogicaTemas.addListJLabel("JLabelH1LibroDiario", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2LibroDiario", listaLabelsH2);
        
        
        jTableLibroDiario.setModel(ltm);

        TableColumnModel columnModel = jTableLibroDiario.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);

        sdf = new SimpleDateFormat("yyyy/MM/dd");

        cal1 = new GregorianCalendar(1900, 01, 01);
        cal2 = new GregorianCalendar(2025, 12, 31);

        fechaInicio.setModel(new DefaultComboBoxModel<>(getFechaComboBoxModel()));
        fechaFin.setModel(new DefaultComboBoxModel<>(getFechaComboBoxModel()));
        
        fechaFin.setSelectedIndex(fechaFin.getModel().getSize() - 1);

        NumberFormat formater = new DecimalFormat("#0.00");

        lblDebeH2.setText(formater.format(ltm.getTotalDebe()));
        lblHaberH2.setText(formater.format(ltm.getTotalHaber()));

        
    }

    /**
     * Metodo para obtener el total de haber - deber , entre dos fechas
     * @param fechaIn
     * @param fechaFin
     * @return 
     */
    private double getTotatDebeEntreFechas(Date fechaIn, Date fechaFin) {
        double totalDebe = 0d;
        for (NotaLibroDiario notaLibroDiario : logica.getNotasEntreFechas(fechaIn, fechaFin)) {
            if (notaLibroDiario.getFecha().after(fechaIn)
                    && notaLibroDiario.getFecha().before(fechaFin)
                    || notaLibroDiario.getFecha().equals(fechaIn)
                    || notaLibroDiario.getFecha().equals(fechaFin)) {
                totalDebe += notaLibroDiario.getDebe();
            }
        }
        return totalDebe;
    }

    /**
     * Metodo para obtener total haber entre dos fechas
     * @param fechaIn
     * @param fechaFin
     * @return 
     */
    private double getTotatHaberEntreFechas(Date fechaIn, Date fechaFin) {
        double totalHaber = 0d;
        for (NotaLibroDiario notaLibroDiario : logica.getNotasEntreFechas(fechaIn, fechaFin)) {
            if (notaLibroDiario.getFecha().after(fechaIn)
                    && notaLibroDiario.getFecha().before(fechaFin)
                    || notaLibroDiario.getFecha().equals(fechaIn)
                    || notaLibroDiario.getFecha().equals(fechaFin)) {
                totalHaber += notaLibroDiario.getHaber();
            }
        }
        return totalHaber;
    }

    /**
     * Metodo para obtener un array con las fechas para utilizarlas en un jComboBox
     * @return 
     */
    public String[] getFechaComboBoxModel() {
        String[] fechasComboboxModel;
        Calendar c1 = Calendar.getInstance();
        if (null != logica.getNotasFechaIn()) {
            c1.setTime(logica.getNotasFechaIn());
        } else {
            c1.setTime(new Date(19800101));
        }
        
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(cal2.getTime());
        
        ArrayList<String> listaFechas = new ArrayList<String>();
        while (!c1.equals(c2)) {
            listaFechas.add(sdf.format(c1.getTime()));
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        fechasComboboxModel = new String[listaFechas.size()];
        for (int i = 0; i < listaFechas.size(); i++) {
            fechasComboboxModel[i] = listaFechas.get(i);
        }
        return fechasComboboxModel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jLabel1H1 = new javax.swing.JLabel();
        jLabel2H2 = new javax.swing.JLabel();
        fechaInicio = new javax.swing.JComboBox<>();
        fechaFin = new javax.swing.JComboBox<>();
        jLabel3H2 = new javax.swing.JLabel();
        jLabel4H2 = new javax.swing.JLabel();
        jButtonFiltrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        lblDebeH2 = new javax.swing.JLabel();
        lblHaberH2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLibroDiario = new javax.swing.JTable();
        jLabel5H2 = new javax.swing.JLabel();
        jButtonNuevaNota = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(901, 515));

        btnModificar.setFont(LogicaTemas.BUTTON_FONT);
        btnModificar.setText("Editar Nota");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setFont(LogicaTemas.BUTTON_FONT);
        btnBorrar.setText("Borrar Nota");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel1H1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel1H1.setText("Ver informacion entre fechas ");

        jLabel2H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2H2.setText("hasta");

        fechaInicio.setFont(LogicaTemas.TEXT_FONT);
        fechaInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date" }));

        fechaFin.setFont(LogicaTemas.TEXT_FONT);
        fechaFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date" }));

        jLabel3H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel3H2.setText("Total Debe: ");

        jLabel4H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel4H2.setText("Total Haber: ");

        jButtonFiltrar.setFont(LogicaTemas.BUTTON_FONT);
        jButtonFiltrar.setText("Filtrar");
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        btnImprimir.setFont(LogicaTemas.BUTTON_FONT);
        btnImprimir.setText("Imprimir Nota");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        lblDebeH2.setFont(LogicaTemas.TEXT_FONT);
        lblDebeH2.setText("0.0");

        lblHaberH2.setFont(LogicaTemas.TEXT_FONT);
        lblHaberH2.setText("0.0");

        jTableLibroDiario.setFont(LogicaTemas.TEXT_FONT);
        jTableLibroDiario.setModel(ltm);
        jTableLibroDiario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableLibroDiario.setColumnSelectionAllowed(true);
        jTableLibroDiario.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTableLibroDiario.setRowHeight(20);
        jTableLibroDiario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableLibroDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLibroDiarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLibroDiario);
        jTableLibroDiario.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel5H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel5H2.setText("desde");
        jLabel5H2.setAlignmentX(0.5F);

        jButtonNuevaNota.setFont(LogicaTemas.BUTTON_FONT);
        jButtonNuevaNota.setText("Añadir Nota");
        jButtonNuevaNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaNotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3H2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDebeH2)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel4H2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblHaberH2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5H2)
                                .addGap(20, 20, 20)
                                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2H2)
                                .addGap(20, 20, 20)
                                .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                                .addComponent(jButtonFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1H1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNuevaNota)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1H1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2H2)
                    .addComponent(jLabel5H2)
                    .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4H2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3H2)
                        .addComponent(lblDebeH2)
                        .addComponent(lblHaberH2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevaNota)
                    .addComponent(btnBorrar)
                    .addComponent(btnModificar)
                    .addComponent(btnImprimir))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para filtrar las notas entre dos fechas
     * @param evt 
     */
    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        try {
            String fechaIn = (String) fechaInicio.getSelectedItem();
            String fechaFi = (String) fechaFin.getSelectedItem();
            jTableLibroDiario.setModel(new LibroTableModel(logica.getNotasEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblDebeH2.setText(String.format("%.2f", getTotatDebeEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblHaberH2.setText(String.format("%.2f", getTotatHaberEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));

        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }

    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    /**
     * Metodo para modificar la informacion de una nota 
     * @param evt 
     */
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (jTableLibroDiario.getSelectedRow() == -1) {
            return;
        }
        int ID_nota = (int) jTableLibroDiario.getValueAt(jTableLibroDiario.getSelectedRow(), 0);
        if (ID_nota == -1) {
            return;
        }
        NotaLibroDiario nota = logica.getNotaPorID(ID_nota);
        if (nota != null) {
            DialogNotaLibro dialog = new DialogNotaLibro(frame, true, logica, nota);
            dialog.setVisible(true);
        }
        try {
            String fechaIn = (String) fechaInicio.getSelectedItem();
            String fechaFi = (String) fechaFin.getSelectedItem();
            jTableLibroDiario.setModel(new LibroTableModel(logica.getNotasEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblDebeH2.setText(String.format("%.2f", getTotatDebeEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblHaberH2.setText(String.format("%.2f", getTotatHaberEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * Metodo para crear una nueva nota del libro diario
     * @param evt 
     */
    private void jButtonNuevaNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaNotaActionPerformed
        DialogNotaLibro dialog = new DialogNotaLibro(frame, true, logica, null);
        dialog.setVisible(true);

        try {
            String fechaIn = (String) fechaInicio.getSelectedItem();
            String fechaFi = (String) fechaFin.getSelectedItem();
            jTableLibroDiario.setModel(new LibroTableModel(logica.getNotasEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblDebeH2.setText(String.format("%.2f", getTotatDebeEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblHaberH2.setText(String.format("%.2f", getTotatHaberEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }

    }//GEN-LAST:event_jButtonNuevaNotaActionPerformed

    /**
     * Metodo para borrar una nota del libro diario, se borra tambien de la base de datos
     * @param evt 
     */
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (jTableLibroDiario.getSelectedRow() == -1) {
            return;
        }
        int id_nota = (int) jTableLibroDiario.getValueAt(jTableLibroDiario.getSelectedRow(), 0);
        if (id_nota != -1) {
            int input = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres borrar la nota con ID " + id_nota, "Borrar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                logica.borrarNota(id_nota);
            }
        }

        try {

            String fechaIn = (String) fechaInicio.getSelectedItem();
            String fechaFi = (String) fechaFin.getSelectedItem();
            jTableLibroDiario.setModel(new LibroTableModel(logica.getNotasEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblDebeH2.setText(String.format("%.2f", getTotatDebeEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            lblHaberH2.setText(String.format("%.2f", getTotatHaberEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }

    }//GEN-LAST:event_btnBorrarActionPerformed

    /**
     * Metodo para manejar los clicks de la table del libro diario
     * @param evt 
     */
    private void jTableLibroDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLibroDiarioMouseClicked
        if (jTableLibroDiario.getSelectedRow() == -1) {
            return;
        }
        int ID_nota = (int) jTableLibroDiario.getValueAt(jTableLibroDiario.getSelectedRow(), 0);
        if (ID_nota != -1 && evt.getClickCount() >= 2) {
            DialogNotaLibro dialog = new DialogNotaLibro(frame, true, logica, logica.getNotaPorID(ID_nota));
            dialog.setVisible(true);
            try {
                String fechaIn = (String) fechaInicio.getSelectedItem();
                String fechaFi = (String) fechaFin.getSelectedItem();
                jTableLibroDiario.setModel(new LibroTableModel(logica.getNotasEntreFechas(sdf.parse(fechaIn), sdf.parse(fechaFi))));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }


    }//GEN-LAST:event_jTableLibroDiarioMouseClicked

    /**
     * Metodo para imprimir una nota del libro diario
     * @param evt 
     */
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (jTableLibroDiario.getSelectedRow() == -1) {
            return;
        }
        int id_nota = (int) jTableLibroDiario.getValueAt(jTableLibroDiario.getSelectedRow(), 0);
        if (id_nota != -1) {
            logica.imprimirInformeNotasFecha(logica.getNotaPorID(id_nota));
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> fechaFin;
    private javax.swing.JComboBox<String> fechaInicio;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonNuevaNota;
    private javax.swing.JLabel jLabel1H1;
    private javax.swing.JLabel jLabel2H2;
    private javax.swing.JLabel jLabel3H2;
    private javax.swing.JLabel jLabel4H2;
    private javax.swing.JLabel jLabel5H2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLibroDiario;
    private javax.swing.JLabel lblDebeH2;
    private javax.swing.JLabel lblHaberH2;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PanelLibroDiario.class);
}
