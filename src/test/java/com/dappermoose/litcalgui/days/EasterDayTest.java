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
     * Test of calcEaster method, with 2016.
     */
    @Test
    public void testCalcDateEaster2016 ()
    {
        System.out.println ("calcDateEaster - 2016");
        Assertions.assertEquals (27, EasterDay.calcEaster (2016));
    }
}
