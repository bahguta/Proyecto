/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Esta clase hace la logica de la vista , cambiar colores y tamaño de texto
 *
 * @author Plam
 */
public class LogicaTemas {

    //static variables
    public static Map<String, List<JLabel>> MAP_JLABEL = new LinkedHashMap<>();
    public static Font BUTTON_FONT = new Font("Monospaced", Font.BOLD, 14);
    public static Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 14);
    public static Font TEXT_FONT_H1 = new Font("Monospaced", Font.BOLD, 18);
    public static List<JTable> LISTA_TABLES = new ArrayList<>();
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogicaTemas.class);
    
    
    /**
     * Metodo para añadir un jtable a la lista para poder modificar el tamaño del texto
     */
    public static void addJTable(JTable table){
        LISTA_TABLES.add(table);
    }
    
    
    /**
     * Metodo para agregar un JLabel en un Map 
     * @param nombre
     * @param lista 
     */
    public static void addListJLabel(String nombre, List<JLabel> lista) {
        MAP_JLABEL.put(nombre, lista);
    }

    /**
     * Metodo para obtener un JLabel 
     * @param nombre
     * @return
     * @throws NullPointerException 
     */
    public static List<JLabel> getListJLabel(String nombre) throws NullPointerException {
        return MAP_JLABEL.get(nombre);
    }

    /**
     * MEtodo para cambiar el tamaño del texto
     * @param sizeH1
     * @param sizeH2 
     */
    public static void setTextSize(float sizeH1, float sizeH2) {
        for (Map.Entry<String, List<JLabel>> entry : MAP_JLABEL.entrySet()) {
            String key = entry.getKey();
            List<JLabel> value = entry.getValue();
            if (key.contains("H1")) {
                for (JLabel jLabel : value) {
                    if (sizeH1 < 14) {
                        sizeH1 = 14f;
                    }
                    jLabel.setFont(TEXT_FONT.deriveFont(sizeH1));
                }
            } else if (key.contains("H2")) {
                for (JLabel jLabel : value) {
                    if (sizeH2 < 10) {
                        sizeH2 = 10f;
                    }
                    jLabel.setFont(TEXT_FONT.deriveFont(sizeH2));
                }
            }
        }
        TEXT_FONT.deriveFont(sizeH2 * 2);
        TEXT_FONT_H1.deriveFont(sizeH2);
        BUTTON_FONT.deriveFont(sizeH2);
        for (JTable jTable : LogicaTemas.LISTA_TABLES) {
            jTable.setFont(TEXT_FONT);
            DefaultTableCellRenderer d = new DefaultTableCellRenderer();
            d.setFont(TEXT_FONT);
            d.setBorder(GET_TITLE_BORDER("some nombre"));
            jTable.setDefaultRenderer(JTable.class, d);
            
        }
    }

    /**
     * Metodo para crear unos bordes con titulo
     * @param nombre
     * @return 
     */
    public static TitledBorder GET_TITLE_BORDER(String nombre) {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        Border line = BorderFactory.createLineBorder(Color.ORANGE, 2, true);
        Border border = BorderFactory.createTitledBorder(line, nombre, TitledBorder.CENTER, TitledBorder.TOP, font, Color.CYAN);
        TitledBorder titledBorder = new TitledBorder(border);
        return titledBorder;
    }

}
