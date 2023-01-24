package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import jakarta.inject.Inject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.dappermoose.litcalgui.days.EasterDay;
import com.dappermoose.litcalgui.days.LeapYear;
import com.dappermoose.litcalgui.days.WeekDay;

import lombok.extern.slf4j.Slf4j;

/**
 * the guts of the calendar-making process.
 *
 * @author matt
 */
@Component
@Slf4j
public class MakeCalendar
{
    @Inject
    private MessageSource msgSource;

    @Inject
    private Locale myLocale;

    @Inject
    private JFrame frame;

    @Inject
    private String [] dateNames;

    /**
     * default constructor.
     */
    public MakeCalendar ()
    {
    }

    /**
     * action method for "make calendar".
     */
    void makeCalendar ()
    {
        log.debug ("entered makeCalendar()");

        String[] options = new String[]
        {
            msgSource.getMessage ("yesLabel", null, myLocale),
            msgSource.getMessage ("noLabel", null, myLocale)
        };

        JSpinner yearInput = new JSpinner (new SpinnerNumberModel (2020, 2000, 2050, 1));
        yearInput.setEditor (new NumberEditor (yearInput, "####"));

        yearInput.addAncestorListener (new RequestFocusListener ());
        Object[] message = { "enter the year, you rascal", yearInput };

        // icon is font-awesome question mark
        int action = JOptionPane.showOptionDialog (frame,
                message, "initial data entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, null);

        Integer year;
        if (action == JOptionPane.OK_OPTION)
        {
            log.debug ("entry made");
            year = (Integer) yearInput.getValue ();
            if (year != null)
            {
                log.debug ("year is not null " + year);
            }
            else
            {
                log.debug ("year is null");
                return;
            }
        }
        else
        {
            log.debug ("canceled");
            return;
        }

        log.debug ("Liturgical year is " + year);
        int easterDay = EasterDay.calcEaster (year);
        String month = "March";
        if (easterDay > 31)
        {
            easterDay -= 31;
            month = "April";
        }
        log.debug ("Easter is " + month + " " + easterDay);
        log.debug (year + " is " + (!LeapYear.isLeapYear (year) ? "not " : "") +
                "a leap year");

        int jan1date = WeekDay.calcWeekDate (year, 1, 1);
        log.debug ("January 1st is a " + dateNames[jan1date]);
    }
}
