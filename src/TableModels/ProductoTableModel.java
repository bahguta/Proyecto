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

    /**
     * 
     * Metodo para buscar la posicion en la tabla de un producto
     * 
     * @param id_producto  el ID del producto que se busca
     * @return retorna el producto encontrado
     * @throws NullPointerException 
     */
    public int getRowProducto(int id_producto){
        
        for (int i = 0; i < listaProductos.size()-1; i++) {
            if ((int) getValueAt(i, 0) == id_producto) {
                return i;
            }
        }
        return -1;
    }
    
    public Producto getProducto(int id_producto) throws NullPointerException{
        for (Producto producto : listaProductos) {
            if (producto.getCodProducto() == id_producto) {
                return producto;
            }
        }
        return null;
    }
    
}
