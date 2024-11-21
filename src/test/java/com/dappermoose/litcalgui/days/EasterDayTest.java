package com.dappermoose.litcalgui.days;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  Test the EasterDay class.
 * @author matt
 */
public class EasterDayTest
{
    /**
     * Dummy constructor.
     */
    public EasterDayTest ()
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
     * Test of calcEaster method, with 2011.
     */
    @Test
    public void testCalcDateEaster2011 ()
    {
        System.out.println ("calcDateEaster - 2011");
        Assertions.assertEquals (55, EasterDay.calcEaster (2011));
    }

    /**
     * Test of calcEaster method, with 2012.
     */
    @Test
    public void testCalcDateEaster2012 ()
    {
        System.out.println ("calcDateEaster - 2012");
        Assertions.assertEquals (39, EasterDay.calcEaster (2012));
    }

    /**
     * Test of calcEaster method, with 2013.
     */
    @Test
    public void testCalcDateEaster2013 ()
    {
        System.out.println ("calcDateEaster - 2013");
        Assertions.assertEquals (31, EasterDay.calcEaster (2013));
    }

    /**
     * Test of calcEaster method, with 2014.
     */
    @Test
    public void testCalcDateEaster2014 ()
    {
        System.out.println ("calcDateEaster - 2014");
        Assertions.assertEquals (51, EasterDay.calcEaster (2014));
    }

    /**
     * Test of calcEaster method, with 2015.
     */
    @Test
    public void testCalcDateEaster2015 ()
    {
        System.out.println ("calcDateEaster - 2015");
        Assertions.assertEquals (36, EasterDay.calcEaster (2015));
    }

    /**
     * Test of calcEaster method, with 2016.
     */
    @Test
    public void testCalcDateEaster2016 ()
    {
        System.out.println ("calcDateEaster - 2016");
        Assertions.assertEquals (27, EasterDay.calcEaster (2016));
    }

    /**
     * Test of calcEaster method, with 2017.
     */
    @Test
    public void testCalcDateEaster2017 ()
    {
        System.out.println ("calcDateEaster - 2017");
        Assertions.assertEquals (47, EasterDay.calcEaster (2017));
    }

   /**
     * Test of calcEaster method, with 2018.
     */
    @Test
    public void testCalcDateEaster2018 ()
    {
        System.out.println ("calcDateEaster - 2018");
        Assertions.assertEquals (32, EasterDay.calcEaster (2018));
    }

    /**
     * Test of calcEaster method, with 2019.
     */
    @Test
    public void testCalcDateEaster2019 ()
    {
        System.out.println ("calcDateEaster - 2019");
        Assertions.assertEquals (52, EasterDay.calcEaster (2019));
    }

    /**
     * Test of calcEaster method, with 2020.
     */
    @Test
    public void testCalcDateEaster2020 ()
    {
        System.out.println ("calcDateEaster - 2019");
        Assertions.assertEquals (43, EasterDay.calcEaster (2020));
    }
}
