package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.dappermoose.litcalgui.days.EasterDay;
import com.dappermoose.litcalgui.days.LeapYear;

import lombok.extern.log4j.Log4j2;

/**
 * the guts of the calendar-making process.
 * 
 * @author matt
 */
@Component
@Log4j2
public class MakeCalendar
{
    @Inject
    private MessageSource msgSource;

    @Inject
    private Locale myLocale;          

    @Inject
    private JFrame frame;

    /**
     * action method for "make calendar".
     */
    void makeCalendar ()
    {
        LOG.debug ("entered makeCalendar()");
        
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
                MyIconImage.makeIcon ('\uf128'), options, null);

        Integer year;
        if (action == JOptionPane.OK_OPTION)
        {
            LOG.debug ("entry made");
            year = (Integer) yearInput.getValue ();
            if (year != null)
            {
                LOG.debug ("year is not null " + year);
            }
            else
            {
                LOG.debug ("year is null");
                return;
            }
        }
        else
        {
            LOG.debug ("canceled");
            return;
        }

        LOG.debug ("Liturgical year is " + year);
        int easterDay = EasterDay.calcEaster (year);
        String month = "March";
        if (easterDay > 31)
        {
            easterDay -= 31;
            month = "April";
        }
        LOG.debug ("Easter is " + month + " " + easterDay);
        LOG.debug (year + " is " + (!LeapYear.isLeapYear (year) ? "not " : "") + "a leap year");
    }
}
