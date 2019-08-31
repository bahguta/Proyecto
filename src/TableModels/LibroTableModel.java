/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import Dto.NotaLibroDiario;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Plam
 */
public class LibroTableModel extends AbstractTableModel {
    
    private List <NotaLibroDiario> listanotas;
    private String [] listaColumnas = new String [] {"ID_Nota", "Fecha", "Detalle", "Debe", "Haber"};
    private NumberFormat formater = new DecimalFormat("#0.00");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public LibroTableModel(List<NotaLibroDiario> listanotas) {
        this.listanotas = listanotas;
    }

    @Override
    public int getRowCount() {
        return listanotas.size();
    }

    @Override
    public int getColumnCount() {
        return listaColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnaIndex) {
        
        switch (columnaIndex){
            case 0: 
                return listanotas.get(rowIndex).getCodNota();
            case 1:
                return sdf.format(listanotas.get(rowIndex).getFecha());
            case 2:
                return listanotas.get(rowIndex).getDetalle();
            case 3:
                return listanotas.get(rowIndex).getDebe();
            case 4:
                return listanotas.get(rowIndex).getHaber();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return listaColumnas[i];
    }
    
    public double getTotalDebe(){
        double total = 0d;
        for (NotaLibroDiario listanota : listanotas) {
            total += listanota.getDebe();
        }
        return total;
    }
    
    public double getTotalHaber(){
        double total = 0d;
        for (NotaLibroDiario listanota : listanotas) {
            total += listanota.getHaber();
        }
        return total;
    }
    
}
