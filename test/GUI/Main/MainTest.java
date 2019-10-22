/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Main;

import javax.swing.LookAndFeel;
import javax.swing.event.MenuEvent;
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
public class MainTest {
    
    public MainTest() {
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
     * Test of GET_VERSION method, of class Main.
     */
    @Test
    public void testGET_VERSION() {
        System.out.println("GET_VERSION");
        double expResult = 0.0;
        double result = Main.GET_VERSION();
        Assert.assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of setLookAndFeel method, of class Main.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        LookAndFeel laf = null;
        Main.setLookAndFeel(laf);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of menuSelected method, of class Main.
     */
    @Test
    public void testMenuSelected() {
        System.out.println("menuSelected");
        MenuEvent me = null;
        Main instance = new Main();
        instance.menuSelected(me);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of menuDeselected method, of class Main.
     */
    @Test
    public void testMenuDeselected() {
        System.out.println("menuDeselected");
        MenuEvent me = null;
        Main instance = new Main();
        instance.menuDeselected(me);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of menuCanceled method, of class Main.
     */
    @Test
    public void testMenuCanceled() {
        System.out.println("menuCanceled");
        MenuEvent me = null;
        Main instance = new Main();
        instance.menuCanceled(me);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }
    
}
