package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * home of the action listeners.
 * for the menubar and frame.
 *
 * @author matt
 */
@Component
@Log4j2
public final class ActionListeners
{
    @Inject
    private MessageSource msgSource;

    @Inject
    private JFrame frame;

    @Value ("${git.build.version}")
    private String gitVersion;

    @Value ("${git.commit.time}")
    private String gitCommitTime;

    @Value ("${git.commit.id.abbrev}")
    private String gitCommitId;

    @Inject
    private Locale myLocale;

    void shutdownApp ()
    {
        LOG.debug ("entered shutdownApp ()");
        String[] options = new String[]
        {
            msgSource.getMessage ("yesLabel", null, myLocale),
            msgSource.getMessage ("noLabel", null, myLocale)
        };

        // icon is font-awesome question mark
        int choice = JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("quitQuestion", null, myLocale),
                msgSource.getMessage ("quitTitle", null, myLocale),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                MyIconImage.makeIcon ('\uf128'), options, options[0]);

        if (choice != JOptionPane.OK_OPTION)
        {
            LOG.debug ("canceled");
            return;
        }

        LOG.debug ("bye-bye");

        frame.setVisible (false);
        frame.dispose ();
    }

    void showAbout ()
    {
        String[] options = new String[]
        {
            msgSource.getMessage ("okLabel", null, myLocale)
        };

        // icon is font-awesome exclamation        
        JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("litcalGui",
                        new Object[]
                        {
                            (Object) gitVersion, (Object) gitCommitTime,
                            (Object) gitCommitId
                        },
                        myLocale),
                msgSource.getMessage ("aboutTitle", null, myLocale),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                MyIconImage.makeIcon ('\uf12a'), options, options[0]);
    }

    void makeCalendar ()
    {
        LOG.debug ("entered makeCalendar()");

        String[] options = new String[]
        {
            msgSource.getMessage ("yesLabel", null, myLocale),
            msgSource.getMessage ("noLabel", null, myLocale)
        };

        JTextField yearInput = new JTextField (4);
        yearInput.addAncestorListener (new RequestFocusListener ());
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
            if (year == null)
            {
                LOG.debug ("year is null");
            }
            else
            {
                LOG.debug ("year is not null " + year);
            }
        }
        else
        {
            LOG.debug ("canceled");
        }
    }

    /*  "Borrowed" shamelessly from http://www.camick.com/java/source/RequestFocusListener.java */
    private static class RequestFocusListener implements AncestorListener
    {

        private boolean removeListener = false;

        /*
         *  Convenience constructor. The listener is only used once and then it is
         *  removed from the component.
         */
        RequestFocusListener ()
        {
            this (true);
        }

        /*
         *  Constructor that controls whether this listen can be used once or
         *  multiple times.
         *
         *  @param removeListener when true this listener is only invoked once
         *                        otherwise it can be invoked multiple times.
         */
        RequestFocusListener (final boolean remove)
        {
            removeListener = remove;
        }

        @Override
        public void ancestorAdded (final AncestorEvent e)
        {
            LOG.debug ("ancestor added, removeListener is " + Boolean.toString (removeListener));

            JComponent component = e.getComponent ();
            component.requestFocusInWindow ();

            if (removeListener)
            {
                component.removeAncestorListener (this);
            }
        }

        @Override
        public void ancestorRemoved (final AncestorEvent event)
        {
        }

        @Override
        public void ancestorMoved (final AncestorEvent event)
        {
        }
    }
}
