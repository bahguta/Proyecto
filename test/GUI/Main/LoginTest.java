/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import java.awt.Frame;
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
public class LoginTest {
    
    Login objectToTest;
    
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        objectToTest = new Login(new Frame(), true);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of esNumero method, of class Login.
     */
    @Test
    public void testEsNumero() {
        System.out.println("esNumero");
        String s = "";
        Login instance = objectToTest;
        boolean expResult = false;
        boolean result = instance.esNumero(s);
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of isRegistrar method, of class Login.
     */
    @Test
    public void testIsRegistrar() {
        System.out.println("isRegistrar");
        Login instance = objectToTest;
        boolean expResult = false;
        boolean result = instance.isRegistrar();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombreLogin method, of class Login.
     */
    @Test
    public void testGetNombreLogin() {
        System.out.println("getNombreLogin");
        Login instance = objectToTest;
        String expResult = null;
        String result = instance.getNombreLogin();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPassLogin method, of class Login.
     */
    @Test
    public void testGetPassLogin() {
        System.out.println("getPassLogin");
        Login instance = objectToTest;
        String expResult = "";
        String result = "";
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombre method, of class Login.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Login instance = objectToTest;
        String expResult = null;
        String result = instance.getNombre();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPass method, of class Login.
     */
    @Test
    public void testGetPass() {
        System.out.println("getPass");
        Login instance = objectToTest;
        String expResult = "";
        String result = "";
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getHost method, of class Login.
     */
    @Test
    public void testGetHost() {
        System.out.println("getHost");
        Login instance = objectToTest;
        String expResult = null;
        String result = instance.getHost();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombreBBDD method, of class Login.
     */
    @Test
    public void testGetNombreBBDD() {
        System.out.println("getNombreBBDD");
        Login instance = objectToTest;
        String expResult = null;
        String result = instance.getNombreBBDD();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPuerto method, of class Login.
     */
    @Test
    public void testGetPuerto() {
        System.out.println("getPuerto");
        Login instance = objectToTest;
        int expResult = 0;
        int result = instance.getPuerto();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
