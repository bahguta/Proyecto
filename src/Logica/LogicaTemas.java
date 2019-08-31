/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Plam
 */
public class LogicaTemas {

    //static variables
    public static Map<String, List<JLabel>> MAP_JLABEL = new LinkedHashMap<>();
    public static Font BUTTON_FONT = new Font("Monospaced", Font.BOLD, 14);
    public static Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 14);
    public static Font TEXT_FONT_H1 = new Font("Monospaced", Font.BOLD, 18);

    public static void addListJLabel(String nombre, List<JLabel> lista) {
        MAP_JLABEL.put(nombre, lista);
    }

    public static List<JLabel> getListJLabel(String nombre) throws NullPointerException {
        return MAP_JLABEL.get(nombre);
    }

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
    }

    public static TitledBorder GET_TITLE_BORDER(String nombre) {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        Border line = BorderFactory.createLineBorder(Color.ORANGE, 2, true);
        Border border = BorderFactory.createTitledBorder(line, nombre, TitledBorder.CENTER, TitledBorder.TOP, font, Color.CYAN);
        TitledBorder titledBorder = new TitledBorder(border);
        return titledBorder;
    }
}
