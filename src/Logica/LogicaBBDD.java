/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dto.Factura;
import Dto.Persona;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.openide.util.Exceptions;

/**
 *
 * @author Plam
 */
public class LogicaBBDD implements Serializable {
    
    

    private static final File fichero = new File("bin/DatosBBDD.txt");
    private static Map<String, Object> lista = new LinkedHashMap<String, Object>();
    private static LogicaNegocio logica;
    
    public LogicaBBDD(LogicaNegocio logica){
        this.logica = logica;
    }
    
    
    public static void guardarBBDD() {
        ObjectOutputStream ous = null;
        try {
            if (fichero == null) {
                fichero.createNewFile();
            }
            ous = new ObjectOutputStream(new FileOutputStream(fichero));
            for (Map.Entry<String, Object> entry : lista.entrySet()) {
                ous.writeObject(entry);
            }

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            if (ous != null) {
                try {
                    ous.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    public static void recuperarBBDD() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
            lista.clear();
            Object aux = ois.readObject();
            while (aux != null) {
                
                if (aux instanceof Persona) {
                    logica.addPersona(((Persona) aux).getNombre(), 
                            ((Persona) aux).getApellido(), 
                            ((Persona) aux).getDireccion(), 
                            ((Persona) aux).getTelefono(), 
                            ((Persona) aux).getEmail(), 
                            ((Persona) aux).getTipo());
                } else if ( aux instanceof Factura) {
                    //logica.addFactura(((Factura) aux).getPrecio(),
                           // 1,
                          //  ((Factura) aux).getListaProductos(), 
                          //  ((Factura) aux).getTrabajos());
                } //else if
                //aux = ois.readObject();
            }
            ois.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

    }
}
