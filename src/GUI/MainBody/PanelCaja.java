/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainBody;

import Logica.LogicaNegocio;
import Logica.LogicaTemas;
import TableModels.CajaTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Panel Caja
 * 
 * @author Plam
 */
public class PanelCaja extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private List<JLabel> listaLabelsH1; 
    private List<JLabel> listaLabelsH2;
    private LogicaNegocio logica;
    private CajaTableModel ctm;
    
    /**
     * Constructor
     */
    public PanelCaja(JFrame frame, LogicaNegocio logica) {
        initComponents();

        setBorder(LogicaTemas.GET_TITLE_BORDER("CAJA"));
        
        
        this.logica = logica;

        listaLabelsH1 = new ArrayList<>();
        listaLabelsH1.add(jLabel4H1);
        listaLabelsH1.add(jLabelTotalH1);

        listaLabelsH2 = new ArrayList<>();
        listaLabelsH2.add(jLabel2H2);
        listaLabelsH2.add(jLabel3H2);
        listaLabelsH2.add(jLabelTotalDebeH2);
        listaLabelsH2.add(jLabelTotalHaberH2);
        
        LogicaTemas.addJTable(jTable1);
        
        LogicaTemas.addListJLabel("JLabelH1Caja", listaLabelsH1);
        LogicaTemas.addListJLabel("JLabelH2Caja", listaLabelsH2);
        
        actualizarPanelCaja();
        
    }
    
    /**
     * Metodo para actualizar la caja.<br>
     * Recoge las factura y las notas diarias, hace los calculos y asigna el valor a la caja.
     */
    public void actualizarPanelCaja(){
        ctm = new CajaTableModel(logica.getNotaLibroDiarios());
        jTable1.setModel(ctm);
        jLabelTotalDebeH2.setText(String.format("%.2f", ctm.getTotalDebe()));
        jLabelTotalHaberH2.setText(String.format("%.2f", ctm.getTotalHaber()));
        jLabelTotalH1.setText(String.format("%.2f", ctm.getTotal()));
        logica.setCaja(ctm.getTotal());
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
        jTable1 = new javax.swing.JTable();
        jLabel2H2 = new javax.swing.JLabel();
        jLabel3H2 = new javax.swing.JLabel();
        jLabel4H1 = new javax.swing.JLabel();
        jLabelTotalHaberH2 = new javax.swing.JLabel();
        jLabelTotalDebeH2 = new javax.swing.JLabel();
        jLabelTotalH1 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(901, 515));

        jTable1.setFont(LogicaTemas.TEXT_FONT);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel2H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2H2.setText("Total Haber: ");

        jLabel3H2.setFont(LogicaTemas.TEXT_FONT);
        jLabel3H2.setText("Total Debe:");

        jLabel4H1.setFont(LogicaTemas.TEXT_FONT_H1);
        jLabel4H1.setText("Total:");

        jLabelTotalHaberH2.setFont(LogicaTemas.TEXT_FONT);

        jLabelTotalDebeH2.setFont(LogicaTemas.TEXT_FONT);

        jLabelTotalH1.setFont(LogicaTemas.TEXT_FONT_H1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2H2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3H2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4H1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTotalHaberH2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jLabelTotalDebeH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTotalH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2H2)
                    .addComponent(jLabelTotalHaberH2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3H2)
                    .addComponent(jLabelTotalDebeH2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4H1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTotalH1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2H2;
    private javax.swing.JLabel jLabel3H2;
    private javax.swing.JLabel jLabel4H1;
    private javax.swing.JLabel jLabelTotalDebeH2;
    private javax.swing.JLabel jLabelTotalH1;
    private javax.swing.JLabel jLabelTotalHaberH2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PanelCaja.class);
}
