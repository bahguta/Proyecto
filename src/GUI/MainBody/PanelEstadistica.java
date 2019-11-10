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
import Logica.StretchIcon;
import TableModels.LibroTableModel;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Panel Estadistica
 * @author Plam
 */
public class PanelEstadistica extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private LogicaNegocio logica;
    private List<JLabel> listaLabelsH1;
    private List<JLabel> listaLabelsH2;
    private String rutaLineChart, rutaBarChart3d, rutaPieChart3d;
    private File barChart3d, lineChart, pieChart3d;
    private JFrame parent;
    private File ruta;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructor
     */
    public PanelEstadistica(JFrame parent, LogicaNegocio logica) {
        this.logica = logica;
        initComponents();
        this.parent = parent;

        setBorder(LogicaTemas.GET_TITLE_BORDER("Estadistica"));
        
        jComboBoxFechaDesde.setSelectedItem(sdf.format(logica.getNotasFechaIn()));
        jComboBoxFechaHasta.setSelectedItem(sdf.format(logica.getNotasFechaFin()));

        ruta = new File("img_estadistica");
        if (ruta.mkdir()) {
            logger.info("Carpeta " + ruta.getPath() + " creada con Exito");
        } else if (ruta.exists()) {
            logger.info("La carpeta img_estadistica existe !!! No se crea !");
        } else {
            JOptionPane.showMessageDialog(this, "La carpeta " + ruta.getPath() + " NO se ha creado\nRevisa los permisos del programa.");
        }

        LogicaTemas.addJTable(jTableNotas);
    }


    /**
     * Metodo para generar la estadistica tipo PiePlot3d 
     * @param nota 
     */
    public void estadisticaPieChart3d(NotaLibroDiario nota) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Cobros", nota.getHaber());
        dataset.setValue("Debe", nota.getDebe());
        double diferencia = nota.getHaber() - nota.getDebe();
        dataset.setValue("Beneficio", diferencia);
        JFreeChart chart = ChartFactory.createPieChart3D("Estadistica diaria - " + nota.getFecha(), dataset, true, true, true);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        if (pieChart3d != null && pieChart3d.exists()) {
            pieChart3d.delete();
        }
        rutaPieChart3d = ruta.getPath() + "/pie_Chart3D" + "_" + System.currentTimeMillis() + ".jpeg";
        pieChart3d = new File(rutaPieChart3d);
        try {
            ChartUtilities.saveChartAsJPEG(pieChart3d, chart, 600, 400);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Metodo para generar la estadistica tipo BarChart3D
     * @param fechaInicio
     * @param fechaFin 
     */
    public void estadisticaBarChart3d(Date fechaInicio, Date fechaFin) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (NotaLibroDiario nota : logica.getNotasEntreFechas(fechaInicio, fechaFin)) {
            dataset.addValue(nota.getHaber(), "Cobros", nota.getFecha());
            dataset.addValue(nota.getDebe(), "Debe", nota.getFecha());
            double diferencia = nota.getHaber() - nota.getDebe();
            dataset.addValue(diferencia, "Beneficio", nota.getFecha());
        }
        JFreeChart barChart = ChartFactory.createBarChart3D("Estadistica de Cobros", "Fecha", "Dinero", dataset, PlotOrientation.VERTICAL, true, true, true);

        if (barChart3d != null && barChart3d.exists()) {
            barChart3d.delete();
        }
        rutaBarChart3d = ruta.getPath() + "/barChart2d" + "_" + System.currentTimeMillis() + ".jpeg";
        barChart3d = new File(rutaBarChart3d);

        try {
            ChartUtilities.saveChartAsJPEG(barChart3d, barChart, 600, 400);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
    
    /**
     * Metodo para generar la estadistica tipo LineChart
     * @param fechaInicio
     * @param fechaFin 
     */
    public void estadisticaLineChart(Date fechaInicio, Date fechaFin) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (NotaLibroDiario nota : logica.getNotasEntreFechas(fechaInicio, fechaFin)) {
            dataset.addValue(nota.getHaber(), "Cobros", nota.getFecha());
            dataset.addValue(nota.getDebe(), "Debe", nota.getFecha());
            double diferencia = nota.getHaber() - nota.getDebe();
            dataset.addValue(diferencia, "Beneficio", nota.getFecha());
        }
        JFreeChart barChart = ChartFactory.createLineChart("Estadistica de Cobros", "Fecha", "Dinero", dataset, PlotOrientation.VERTICAL, true, true, true);
        if (lineChart != null && lineChart.exists()) {
            lineChart.delete();
        }
        rutaLineChart = ruta.getPath() + "/lineChart" + "_" + System.currentTimeMillis() + ".jpeg";
        lineChart = new File(rutaLineChart);
        try {
            ChartUtilities.saveChartAsJPEG(lineChart, barChart, 600, 400);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxFechaDesde = new javax.swing.JComboBox<>();
        jComboBoxFechaHasta = new javax.swing.JComboBox<>();
        jButtonFiltrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        jLabelBarChart = new javax.swing.JLabel();
        jLabelLineChart = new javax.swing.JLabel();
        jLabelPieChart = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(901, 500));

        jLabel1.setFont(LogicaTemas.TEXT_FONT);
        jLabel1.setText("desde");

        jLabel2.setFont(LogicaTemas.TEXT_FONT);
        jLabel2.setText("hasta");

        jComboBoxFechaDesde.setFont(LogicaTemas.BUTTON_FONT);
        jComboBoxFechaDesde.setModel(logica.getFechasComboboxModel());

        jComboBoxFechaHasta.setFont(LogicaTemas.BUTTON_FONT);
        jComboBoxFechaHasta.setModel(logica.getFechasComboboxModel());

        jButtonFiltrar.setFont(LogicaTemas.BUTTON_FONT);
        jButtonFiltrar.setText("Filtrar");
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jTableNotas.setFont(LogicaTemas.TEXT_FONT);
        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableNotasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableNotas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFiltrar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFiltrar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para filtrar las notas del libro diario entre dos fechas
     * @param evt 
     */
    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        jLabelPieChart.setIcon(null);
        
        try {
            Date fechaInicio = sdf.parse((String) jComboBoxFechaDesde.getSelectedItem());
            Date fechaFin = sdf.parse((String) jComboBoxFechaHasta.getSelectedItem());
            jTableNotas.setModel(new LibroTableModel(logica.getNotasEntreFechas(fechaInicio, fechaFin)));

            estadisticaBarChart3d(fechaInicio, fechaFin);
            estadisticaLineChart(fechaInicio, fechaFin);

            StretchIcon img = new StretchIcon(rutaBarChart3d);
            jLabelBarChart.setIcon(img);

            StretchIcon img3 = new StretchIcon(rutaLineChart);
            jLabelLineChart.setIcon(img3);

        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    
    /**
     * Metodo para obtener 
     */
    
    
    /**
     * Metodo para manejar los clicks a la table Notas. Genera Estadistica
     * @param evt 
     */
    private void jTableNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNotasMouseClicked
        if (jTableNotas.getSelectedRow() == -1 ) {
            return;
        }
        if (jTableNotas.getValueAt(jTableNotas.getSelectedRow(), 0) == null) {
            return;
        }
        int ID_nota = (int) jTableNotas.getValueAt(jTableNotas.getSelectedRow(), 0);
        if (ID_nota != -1) {
            if (evt.getClickCount() == 2) {
                DialogNotaLibro dialog = new DialogNotaLibro(parent, true, logica, logica.getNotaPorID(ID_nota));
                dialog.setVisible(true);
            } else {
                estadisticaPieChart3d(logica.getNotaPorID(ID_nota));
                StretchIcon img2 = new StretchIcon(rutaPieChart3d);
                jLabelPieChart.setIcon(img2);
            }
        }
    }//GEN-LAST:event_jTableNotasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JComboBox<String> jComboBoxFechaDesde;
    private javax.swing.JComboBox<String> jComboBoxFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBarChart;
    private javax.swing.JLabel jLabelLineChart;
    private javax.swing.JLabel jLabelPieChart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableNotas;
    // End of variables declaration//GEN-END:variables
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PanelEstadistica.class);
}
