/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 

public class FacturaTest {
    
    public FacturaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTrabajos method, of class Factura.
     */
    @Test
    public void testGetTrabajos() {
        System.out.println("getTrabajos");
        Factura instance = null;
        String expResult = "";
        String result = instance.getTrabajos();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setTrabajos method, of class Factura.
     */
    @Test
    public void testSetTrabajos() {
        System.out.println("setTrabajos");
        String trabajos = "";
        Factura instance = null;
        instance.setTrabajos(trabajos);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class Factura.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Factura instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setFecha method, of class Factura.
     */
    @Test
    public void testSetFecha() {
        System.out.println("setFecha");
        Date fecha = null;
        Factura instance = null;
        instance.setFecha(fecha);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getListaProductos method, of class Factura.
     */
    @Test
    public void testGetListaProductos() {
        System.out.println("getListaProductos");
        Factura instance = null;
        List<Producto> expResult = null;
        List<Producto> result = instance.getListaProductos();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setListaProductos method, of class Factura.
     */
    @Test
    public void testSetListaProductos() {
        System.out.println("setListaProductos");
        List<Producto> listaProductos = null;
        Factura instance = null;
        instance.setListaProductos(listaProductos);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecio method, of class Factura.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Factura instance = null;
        double expResult = 0.0;
        double result = instance.getPrecio();
        Assert.assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setPrecio method, of class Factura.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        double precio = 0.0;
        Factura instance = null;
        instance.setPrecio(precio);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getCodFactura method, of class Factura.
     */
    @Test
    public void testGetCodFactura() {
        System.out.println("getCodFactura");
        Factura instance = null;
        int expResult = 0;
        int result = instance.getCodFactura();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }
    
}
