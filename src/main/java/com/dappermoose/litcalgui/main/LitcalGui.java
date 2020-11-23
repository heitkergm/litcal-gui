package com.dappermoose.litcalgui.main;

/*
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Litcal Swing App.
 */
@Component
public class LitcalGui implements Runnable, InvocationHandler
{
    private static final long serialVersionUID = 1L;

    private static Locale locale = Locale.getDefault ();

    private JFrame frame;

    @Inject
    private MessageSource msgSource;

    /**
     * LitcalGui constructor.
     */
    public LitcalGui ()
    {
    }

    /**
     * get the current locale.
     *
     * @return the current locale
     */
    public static Locale getLocale ()
    {
        return locale;
    }

    /**
     * set the locale.
     *
     * @param newLocale - the new locale
     */
    public static void setLocale (final Locale newLocale)
    {
        locale = newLocale;
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
        Object retVal = null;
        if (method.getName ().equals ("windowClosing"))
        {
            shutdownApp ();
        }
        else if (method.getName ().equals ("actionPerformed"))
        {
            ActionEvent evt = (ActionEvent) args[0];
            if (evt.getActionCommand ().equals (
                          msgSource.getMessage ("exitLabel", null, locale)))
            {
                shutdownApp ();
            }
            else if (evt.getActionCommand ().equals (
                         msgSource.getMessage ("aboutLabel", null, locale)))
            {
                showAbout ();
            }
        }
        return retVal;
    }

    private void initGui ()
    {
        try
        {
            UIManager.setLookAndFeel (
                UIManager.getSystemLookAndFeelClassName ());
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace ();
        }
        catch (InstantiationException ie)
        {
            ie.printStackTrace ();
        }
        catch (IllegalAccessException iae)
        {
            iae.printStackTrace ();
        }
        catch (UnsupportedLookAndFeelException ulafe)
        {
            ulafe.printStackTrace ();
        }

        // create window handler
        Object proxy = Proxy.newProxyInstance (
                  this.getClass ().getClassLoader (),
                       new Class<?>[] { ActionListener.class,
                                        WindowListener.class },
                       this);

        //Create and set up the window.
        frame = new JFrame (msgSource.getMessage (
                                             "litcalLabel", null, locale));

        // make sure that we ASK before closing the main frame
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener ((WindowListener) proxy);

        // create Jpanel
        JPanel panel = new JPanel (new BorderLayout ());

        frame.getContentPane ().add (panel);

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

    void shutdownApp ()
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

        Runtime.getRuntime ().exit (0);
    }

    void showAbout ()
    {
        JOptionPane.showMessageDialog (frame,
            msgSource.getMessage ("litcalGui", null, locale),
            msgSource.getMessage ("aboutTitle", null, locale),
            JOptionPane.INFORMATION_MESSAGE);

    }
}
