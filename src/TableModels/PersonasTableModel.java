/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import Dto.Persona;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Plam
 */
public class PersonasTableModel extends AbstractTableModel {

    private List<Persona> listaPersonas;
    private String[] listaColumnas = new String[]{"Cod", "Nombre", "Apellido", "Direccion", "Telefono", "E-mail", "Tipo"};
    //private NumberFormat formater = new DecimalFormat("#0.00");

    public PersonasTableModel(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public int getRowCount() {
        return listaPersonas.size();
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
                return listaPersonas.get(rowIndex).getCodPersona();
            case 1:
                return listaPersonas.get(rowIndex).getNombre();
            case 2:
                return listaPersonas.get(rowIndex).getApellido();
            case 3:
                return listaPersonas.get(rowIndex).getDireccion();
            case 4:
                return listaPersonas.get(rowIndex).getTelefono();
            case 5:
                return listaPersonas.get(rowIndex).getEmail();
            case 6:
                return listaPersonas.get(rowIndex).getTipo();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return listaColumnas[i];
    }
}
