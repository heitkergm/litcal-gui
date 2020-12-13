package com.dappermoose.litcalgui.days;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *  Test the LeapYear class.
 * @author matt
 */
public class LeapYearTest
{
    /**
     * Dummy constructor.
     */
    public LeapYearTest ()
    {
    }

    /**
     * setup before class instantiation.
     */
    @BeforeAll
    public static void setUpClass ()
    {
    }

    /**
     * class tear down when done with all tests.
     */
    @AfterAll
    public static void tearDownClass ()
    {
    }

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setUp ()
    {
    }

    /**
     * Tear down after each test.
     */
    @AfterEach
    public void tearDown ()
    {
    }

    /**
     * Test of isLeapYear method, with 1900.
     */
    @Test
    public void testIsLeapYear1900 ()
    {
        System.out.println ("isLeapYear - 1900");
        Assertions.assertFalse (LeapYear.isLeapYear (1900));
    }

    /**
     * Test of isLeapYear method, with 2000.
     */
    @Test
    public void testIsLeapYear2000 ()
    {
        System.out.println ("isLeapYear - 2000");
        Assertions.assertTrue (LeapYear.isLeapYear (2000));
    }

    /**
     * Test of isLeapYear method, with 2016.
     */
    @Test
    public void testIsLeapYear2016 ()
    {
        System.out.println ("isLeapYear - 2016");
        Assertions.assertTrue (LeapYear.isLeapYear (2016));
    }

    /**
     * Test of isLeapYear method, with 2013.
     */
    @Test
    public void testIsLeapYear2013 ()
    {
        System.out.println ("isLeapYear - 2013");
        Assertions.assertFalse (LeapYear.isLeapYear (2013));
    }

    /**
     * Test of isLeapYear method, with 2014.
     */
    @Test
    public void testIsLeapYear2014 ()
    {
        System.out.println ("isLeapYear - 2014");
        Assertions.assertFalse (LeapYear.isLeapYear (2014));
    }

    /**
     * Test of isLeapYear method, with 2015.
     */
    @Test
    public void testIsLeapYear2015 ()
    {
        System.out.println ("isLeapYear - 2015");
        Assertions.assertFalse (LeapYear.isLeapYear (2015));
    }
}
