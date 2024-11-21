package com.dappermoose.litcalgui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Locale;

import jakarta.inject.Inject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * initial frame setup.
 *
 * @author heitkergm@acm.org
 */
@Slf4j
@Component
public final class FrameSetup
{
    @Inject
    private JFrame frame;

    @Inject
    private MessageSource messageSource;

    @Inject
    private ActionListeners al;

    @Inject
    private SetupScrollPane ssp;

    @Inject
    private MakeMenuBar mmb;

    @Inject
    @NonNull
    private Locale myLocale;

    private FrameSetup ()

    {
    }

    /**
     * routine which sets up the swing Frame object.
     */
    protected void setupFrame ()
    {
        try
        {
            UIManager.setLookAndFeel (
                    UIManager.getSystemLookAndFeelClassName ());
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            log.error ("UIManager look and feel error", ex);
            frame.dispose ();
        }

        UIManager.put ("OptionPane.background", Color.BLACK);
        UIManager.put ("OptionPane.foreground", Color.WHITE);
        UIManager.put ("OptionPane.messageForeground", Color.WHITE);
        UIManager.put ("Button.background", Color.BLACK);
        UIManager.put ("Button.foreground", Color.WHITE);
        UIManager.put ("Panel.background", Color.BLACK);
        UIManager.put ("Panel.foreground", Color.WHITE);
        UIManager.put ("MenuItem.background", Color.BLACK);
        UIManager.put ("MenuItem.foreground", Color.WHITE);
        UIManager.put ("MenuBar.background", Color.BLACK);
        UIManager.put ("MenuBar.foreground", Color.WHITE);
        UIManager.put ("Menu.foreground", Color.WHITE);

        Font f = new Font ("sans-serif", Font.PLAIN, 18);
        UIManager.put ("Menu.font", f);
        UIManager.put ("MenuItem.font", f);
        UIManager.put ("OptionPane.messageFont", f);
        UIManager.put ("OptionPane.buttonFont", f);
        UIManager.put ("Spinner.font", f);

        //Create and set up the window.
        frame.setTitle (messageSource.getMessage ("litcalLabel", null, myLocale));

        // make sure that we ASK before closing the main frame
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing (final WindowEvent e)
            {
                log.debug ("Clicked on exit");
                al.shutdownApp ();
            }
        });

        JScrollPane scrollPane = ssp.setupScrollPane ();
        frame.getContentPane ().add (scrollPane);

        URL imgURL = FrameSetup.class.getResource ("/favicon.png");
        // deepcode ignore LogLevelCheck:
        log.debug ("imgURL is " + (imgURL == null ? "not " : "") + "available");
        if (imgURL != null)
        {
            ImageIcon icon = new ImageIcon (imgURL);
            frame.setIconImage (icon.getImage ());
        }

        JOptionPane.setDefaultLocale (myLocale);

        JMenuBar menuBar = mmb.makeMenuBar ();
        frame.setJMenuBar (menuBar);
    }
}
