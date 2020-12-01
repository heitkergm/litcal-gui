package com.dappermoose.litcalgui.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * Litcal Swing App.
 */
@Component
@Log4j2
public class LitcalGui implements Runnable, InvocationHandler
{

    private Font iconFont;

    @Inject
    private JFrame frame;
    
    @Inject
    private MessageSource msgSource;

    @Inject
    private Locale locale;
    
    /**
     * LitcalGui constructor.
     */
    public LitcalGui ()
    {
    }

    /**
     * method for Runnable interface.
     */
    @Override
    public void run ()
    {
        initGui ();
    }

    /**
     * method for InvocationHandler.
     *
     * @param proxy - the proxy object
     * @param method - the Method object
     * @param args - the arguments
     *
     * @return - an object;
     *
     * @throws Throwable - what this might throw
     */
    @Override
    public Object invoke (final Object proxy, final Method method,
                          final Object[] args)
        throws Throwable
    {
        LOG.debug ("method name = " + method.getName ());

        Object retVal = null;
        if (method.getName ().equals ("windowClosing"))
        {
            shutdownApp (frame, locale);
        }
        else if (method.getName ().equals ("actionPerformed"))
        {
            ActionEvent evt = (ActionEvent) args[0];
            if (evt.getActionCommand ().equals (
                          msgSource.getMessage ("exitLabel", null, locale)))
            {
                shutdownApp (frame, locale);
            }
            else if (evt.getActionCommand ().equals (
                         msgSource.getMessage ("aboutLabel", null, locale)))
            {
                showAbout (frame, locale);
            }
        }
        return retVal;
    }

    @SuppressWarnings ("CallToPrintStackTrace")
    private void initGui ()
    {
        try
        {
            UIManager.setLookAndFeel (
                UIManager.getSystemLookAndFeelClassName ());
        }
        catch (ClassNotFoundException | InstantiationException |
               IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            ex.printStackTrace ();
            frame.dispose ();
        }
        // create window handler
        Object proxy = Proxy.newProxyInstance (
                  this.getClass ().getClassLoader (),
                       new Class<?>[] { ActionListener.class,
                                        WindowListener.class },
                       this);

        
        try (InputStream is = this.getClass ().getResourceAsStream ("/fa-solid-900.ttf"))
        {
            iconFont = Font.createFont (Font.TRUETYPE_FONT, is);
            iconFont = iconFont.deriveFont (Font.PLAIN, 24f);
        }
        catch (IOException | FontFormatException ex)
        {
            ex.printStackTrace ();
            frame.dispose ();
        }

        //Create and set up the window.
        frame.setTitle (msgSource.getMessage ("litcalLabel", null, locale));
        
        // make sure that we ASK before closing the main frame
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener ((WindowListener) proxy);

        // create JTextPane
        JTextPane textPane = new JTextPane ();
        textPane.setPreferredSize (new Dimension (640, 480));
        textPane.setEditable (false);

        JScrollPane scrollPane = new JScrollPane (textPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        frame.getContentPane ().add (scrollPane);

        JMenuBar menuBar = new JMenuBar ();
        JMenu fileMenu = new JMenu (
                          msgSource.getMessage ("fileLabel", null, locale));
        fileMenu.setMnemonic (KeyEvent.VK_F);
        JMenuItem exitItem = new JMenuItem (
                          msgSource.getMessage ("exitLabel", null, locale));
        exitItem.setMnemonic (KeyEvent.VK_E);
        exitItem.addActionListener ((ActionListener) proxy);
        fileMenu.add (exitItem);
        menuBar.add (fileMenu);

        menuBar.add (Box.createHorizontalGlue ());

        JMenu helpMenu = new JMenu (
                          msgSource.getMessage ("helpLabel", null, locale));
        helpMenu.setMnemonic (KeyEvent.VK_H);
        JMenuItem aboutItem = new JMenuItem (
                         msgSource.getMessage ("aboutLabel", null, locale));
        aboutItem.setMnemonic (KeyEvent.VK_A);
        aboutItem.addActionListener ((ActionListener) proxy);
        helpMenu.add (aboutItem);
        menuBar.add (helpMenu);

        frame.setJMenuBar (menuBar);

        //Display the window.
        frame.pack ();
        frame.setVisible (true);

    }

    void shutdownApp (final JFrame frame, final Locale locale)
    {
        String [] options = new String []
        {msgSource.getMessage ("yesLabel", null, locale),
         msgSource.getMessage ("noLabel", null, locale)};

        int choice = JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("quitQuestion", null, locale),
                msgSource.getMessage ("quitTitle", null, locale),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice != JOptionPane.OK_OPTION)
        {
            return;
        }

        frame.setVisible (false);
        frame.dispose ();
    }

    void showAbout (final JFrame frame, final Locale locale)
    {
        JOptionPane.showMessageDialog (frame,
            msgSource.getMessage ("litcalGui", null, locale),
            msgSource.getMessage ("aboutTitle", null, locale),
            JOptionPane.INFORMATION_MESSAGE);
    }
}
