/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import Dto.Factura;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Plam
 */
public class FacturaTableModel  extends AbstractTableModel{
    private List <Factura> listaFacturas;
    private String [] listaColumnas = new String [] {"Codigo Factura","Fecha", "Trabajos Realizados", "Precio"};
    private NumberFormat formaterPrecio = new DecimalFormat("#0.00");
    private NumberFormat formaterPeso = new DecimalFormat("#0.000");

            

    public FacturaTableModel(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    @Override
    public int getRowCount() {
        if (listaFacturas.isEmpty()) {
            return 0;
        }
        return listaFacturas.size();
    }

    @Override
    public int getColumnCount() {
        return listaColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnaIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        switch (columnaIndex){
            case 0: 
                return listaFacturas.get(rowIndex).getCodFactura();
            case 1:
                return sdf.format(listaFacturas.get(rowIndex).getFecha());
            case 2:
                return listaFacturas.get(rowIndex).getTrabajos();
            case 3:
                return listaFacturas.get(rowIndex).getPrecio();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return listaColumnas[i];
    }
}
