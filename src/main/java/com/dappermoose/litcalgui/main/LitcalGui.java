package com.dappermoose.litcalgui.main;

/*
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
*/

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Litcal Swing App.
 */
public class LitcalGui implements Runnable
{
    private static final long serialVersionUID = 1L;

    private static Locale locale = Locale.getDefault ();

    private JFrame frame;

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

        //Create and set up the window.
        /*
        frame = new JFrame (msgSource.getMessage (
                                             "financeLabel", null, locale));
        */
        frame = new JFrame ("Litcal");
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        //frame.addWindowListener ((WindowListener) proxy);

        //Display the window.
        frame.pack ();
        frame.setVisible (true);
    }
}
