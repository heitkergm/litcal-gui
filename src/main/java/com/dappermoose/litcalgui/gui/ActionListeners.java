package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import jakarta.inject.Inject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * home of the action listeners.
 * for the menubar and frame.
 *
 * @author heitkergm@acm.org
 */
@Component
@Slf4j
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
    @NonNull
    private Locale myLocale;

    /**
     * default constructor.
     */
    public ActionListeners ()
    {
    }

    /**
     * action method for "shutdown".
     */
    void shutdownApp ()
    {
        log.debug ("entered shutdownApp ()");
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
                null, options, options[0]);

        if (choice != JOptionPane.OK_OPTION)
        {
            log.debug ("canceled");
            return;
        }

        log.debug ("bye-bye");

        frame.setVisible (false);
        frame.dispose ();
    }

    /**
     * action method for "about".
     */
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
                null, options, options[0]);
    }
}
