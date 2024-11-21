package com.dappermoose.litcalgui.days;

import lombok.extern.slf4j.Slf4j;

/**
 * easter day utility.
 *
 * <p>This is Gauss's algorithm for Easter.</p>
 *
 * @author heitkergm@acm.org
 */
@Slf4j
public final class EasterDay
{
    private EasterDay ()
    {
    }

    /**
     * determines the date of Easter given the input year.
     *
     * @param year the year to evaluate
     * @return an integer. If less than or equal to 31, then in March on that date;
     *                     if greater than 31, then in April on date - 31.
     */
    public static int calcEaster (final int year)
    {
        int retVal;

        int bigC = year / 100 + 1;
        int g = 3 * bigC / 4 - 12;
        int bigG = year % 19 + 1;
        int c = (8 * bigC + 5) / 25 - 5 - g;
        int e = 5 * year / 4 - g - 10;
        int bigE = (11 * bigG + 20  + c) % 30;

        if (bigE != 25 && bigG > 11)
        {
            bigE++;
        }

        if (bigE == 24)
        {
            bigE++;
        }

        int d = 44 - bigE;

        if (d < 21)
        {
            d += 30;
        }

        retVal = d + 7 - ((d + e) % 7);
        // deepcode ignore LogLevelCheck:
        log.debug ("EasterDay.retVal " + retVal);

        return retVal;
    }
}
