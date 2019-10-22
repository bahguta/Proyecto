/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bahguta
 */
public class NotaLibroDiarioTest {
    
    public NotaLibroDiarioTest() {
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
     * Test of getCodNota method, of class NotaLibroDiario.
     */
    @Test
    public void testGetCodNota() {
        System.out.println("getCodNota");
        NotaLibroDiario instance = new NotaLibroDiario();
        int expResult = 0;
        int result = instance.getCodNota();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class NotaLibroDiario.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        NotaLibroDiario instance = new NotaLibroDiario();
        Date expResult = null;
        Date result = instance.getFecha();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setFecha method, of class NotaLibroDiario.
     */
    @Test
    public void testSetFecha() {
        System.out.println("setFecha");
        Date fecha = null;
        NotaLibroDiario instance = new NotaLibroDiario();
        instance.setFecha(fecha);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getDetalle method, of class NotaLibroDiario.
     */
    @Test
    public void testGetDetalle() {
        System.out.println("getDetalle");
        NotaLibroDiario instance = new NotaLibroDiario();
        String expResult = "";
        String result = instance.getDetalle();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setDetalle method, of class NotaLibroDiario.
     */
    @Test
    public void testSetDetalle() {
        System.out.println("setDetalle");
        String detalle = "";
        NotaLibroDiario instance = new NotaLibroDiario();
        instance.setDetalle(detalle);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getDebe method, of class NotaLibroDiario.
     */
    @Test
    public void testGetDebe() {
        System.out.println("getDebe");
        NotaLibroDiario instance = new NotaLibroDiario();
        double expResult = 0.0;
        double result = instance.getDebe();
        Assert.assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setDebe method, of class NotaLibroDiario.
     */
    @Test
    public void testSetDebe() {
        System.out.println("setDebe");
        double debe = 0.0;
        NotaLibroDiario instance = new NotaLibroDiario();
        instance.setDebe(debe);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getHaber method, of class NotaLibroDiario.
     */
    @Test
    public void testGetHaber() {
        System.out.println("getHaber");
        NotaLibroDiario instance = new NotaLibroDiario();
        double expResult = 0.0;
        double result = instance.getHaber();
        Assert.assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setHaber method, of class NotaLibroDiario.
     */
    @Test
    public void testSetHaber() {
        System.out.println("setHaber");
        double haber = 0.0;
        NotaLibroDiario instance = new NotaLibroDiario();
        instance.setHaber(haber);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }
    
}
