/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import Dto.Producto;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Plam
 */
public class ProductoTableModel extends AbstractTableModel{
    
    private List <Producto> listaProductos;
//    private boolean[][] editable_cells; // 2d array representan lineas y columnas
    private String [] listaColumnas = new String [] {"Cod Producto", "Nombre", "Peso", "Precio", "Cantidad"};
    private NumberFormat formaterPrecio = new DecimalFormat("#0.00");
    private NumberFormat formaterPeso = new DecimalFormat("#0.000");

            

    public ProductoTableModel(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public int getRowCount() {
        return listaProductos.size();
    }

    @Override
    public int getColumnCount() {
        return listaColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnaIndex) {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        switch (columnaIndex){
            case 0: 
                return listaProductos.get(rowIndex).getCodProducto();
            case 1:
                return listaProductos.get(rowIndex).getNombre();
            case 2:
                return formaterPeso.format(listaProductos.get(rowIndex).getPeso());
            case 3:
                return formaterPrecio.format(listaProductos.get(rowIndex).getPrecio());
            case 4:
                return listaProductos.get(rowIndex).getCantidad();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return listaColumnas[i];
    }
    
//    @Override
//    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
//        return this.editable_cells[row][column];
//    }
//
//    public void setCellEditable(int row, int col, boolean value) {
//        this.editable_cells[row][col] = value; // set cell true/false
//        
//        for (int i = 0; i < this.editable_cells.length-1; i++) {
//            this.editable_cells[i][this.editable_cells.length-1] = true;
//        }
//        
//        
//        this.fireTableCellUpdated(row, col);
//    }
//    
//    public double getTotalDebe(){
//        double total = 0d;
//        for (Producto listanota : listaProductos) {
//            total += listanota.getDebe();
//        }
//        return total;
//    }
//    
//    public double getTotalHaber(){
//        double total = 0d;
//        for (NotaLibroDiario listanota : listanotas) {
//            total += listanota.getHaber();
//        }
//        return total;
//    }
    
}
