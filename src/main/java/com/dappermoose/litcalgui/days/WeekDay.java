package com.dappermoose.litcalgui.days;

import lombok.extern.slf4j.Slf4j;

/**
 * week day utility.
 *
 * <p>This is often known as Zeller's Congruence.</p>
 *
 * @author matt
 */
@Slf4j
public final class WeekDay
{
    private WeekDay ()
    {
    }

    /**
     * determines the index (0-6) as the day of week for a given date.
     *
     * @param year the year to evaluate
     * @param month the month to evaluate
     * @param day the day of month to evaluate
     * @return 0-6, as the day of week.
     */
    public static int calcWeekDate (final int year, final int month,
            final int day)
    {
        int retVal;

        int m = month;
        int y = year;
        if (m <= 2)
        {
            m += 10;
            y--;
        }
        else
        {
            m -= 2;
        }
        log.debug ("WeekDay.m " + m);
        int c = y / 100;
        log.debug ("WeekDay.c " + c);
        int a = y % 100;
        log.debug ("WeekDay.a " + a);
        int b = (13 * m - 1) / 5 + a / 4 + c / 4;
        log.debug ("WeekDay.b" + b);

        retVal = (b + a + day - (2 * c)) % 7;
        if (retVal < 0)
        {
            retVal += 7;
        }
        log.debug ("WeekDay.retVal " + retVal);

        return retVal;
    }
}
