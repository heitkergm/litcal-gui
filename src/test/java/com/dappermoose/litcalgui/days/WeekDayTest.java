package com.dappermoose.litcalgui.days;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *  Test the WeekDay class.
 * @author matt
 */
public class WeekDayTest
{
    /**
     * Dummy constructor.
     */
    public WeekDayTest ()
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
     * Test of calcDate method, with 2016/08/12.
     */
    @Test
    public void testCalcDateWeekDayFri20160812 ()
    {
        System.out.println ("calcDate - 2016/08/12");
        Assertions.assertEquals (5, WeekDay.calcWeekDate (2016, 8, 12));
    }

    /**
     * Test of calDate method, with 2000/01/01.
     */
    @Test
    public void testCalcDateWeekDayFri20160101 ()
    {
        System.out.println ("calcDate - 2016/01/01");
        Assertions.assertEquals (5, WeekDay.calcWeekDate (2016, 1, 1));
    }
}
