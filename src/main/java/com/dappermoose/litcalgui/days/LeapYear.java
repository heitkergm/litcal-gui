package com.dappermoose.litcalgui.days;

/**
 * leap year utility.
 *
 * @author heitkergm@acm.org
 */
public final class LeapYear
{
    private LeapYear ()
    {
    }

    /**
     * determines whether the give year is a leap year.
     *
     * @param year the year to evaluate
     * @return true/false, depending on whether a leap year or not.
     */
    public static boolean isLeapYear (final int year)
    {
        boolean retVal = false;

        if (year % 400 == 0)
        {
            retVal = true;
        }
        else
        {
            if ((year % 100 != 0) && (year % 4 == 0))
            {
                retVal = true;
            }
        }

        return retVal;
    }
}
