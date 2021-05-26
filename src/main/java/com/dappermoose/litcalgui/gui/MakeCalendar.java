package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

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

    void makeCalendar ()
    {
        LOG.debug ("entered makeCalendar()");
        
        String[] options = new String[]
        {
            msgSource.getMessage ("yesLabel", null, myLocale),
            msgSource.getMessage ("noLabel", null, myLocale)
        };
        
        JTextField yearInput = new JTextField (1);
        yearInput.addAncestorListener (new RequestFocusListener ());
        PlainDocument doc = (PlainDocument) yearInput.getDocument ();
        doc.setDocumentFilter (new MyIntFilter ());

        Object[] message = { "enter the year, you rascal", yearInput };

        // icon is font-awesome question mark
        int action = JOptionPane.showOptionDialog (frame,
                message, "initial data entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                MyIconImage.makeIcon ('\uf128'), options, null);

        if (action == JOptionPane.OK_OPTION)
        {
            LOG.debug ("entry made");
            String year = yearInput.getText ();
            if (year != null && !year.trim ().isEmpty ())
            {
                LOG.debug ("year is not null " + year);
            }
            else
            {
                LOG.debug ("year is null");
            }
        }
        else
        {
            LOG.debug ("canceled");
        }
    }
}
