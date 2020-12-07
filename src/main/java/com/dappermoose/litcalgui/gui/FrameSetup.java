package com.dappermoose.litcalgui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
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

import lombok.extern.log4j.Log4j2;

/**
 * initial frame setup.
 * 
 * @author matt
 */
@Log4j2
public final class FrameSetup
{
    private FrameSetup ()
            
    {
    }
    
    @SuppressWarnings ("CallToPrintStackTrace")
    protected static void setupFrame (final JFrame frame, final Locale myLocale,
                                  final MessageSource msgSource, final Object proxy)
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

        //Create and set up the window.
        frame.setTitle (msgSource.getMessage ("litcalLabel", null, myLocale));
        
        // make sure that we ASK before closing the main frame
        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener ((WindowListener) proxy);

        // create JTextPane
        JTextPane textPane = new JTextPane ();
        textPane.setPreferredSize (new Dimension (640, 480));
        textPane.setEditable (false);
        textPane.setContentType ("text/html");
        textPane.setText ("<html><head><<style type=\"text/css\">" +
                "body {font-family:sans-serif; font-size: large; background-color: black; color: white}" +
                "</style></head><body><p>Paragraph 1</p></body></html>");

        JScrollPane scrollPane = new JScrollPane (textPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        frame.getContentPane ().add (scrollPane);

        URL imgURL =  FrameSetup.class.getResource ("/favicon.png");
        LOG.debug ("imgURL is " + (imgURL == null ? "not " : "") + "available");
        if (imgURL != null)
        {
            ImageIcon icon = new ImageIcon (imgURL);
            frame.setIconImage (icon.getImage ());
        }

        JMenuBar menuBar = new JMenuBar ();
        JMenu fileMenu = new JMenu (
                          msgSource.getMessage ("fileLabel", null, myLocale));
        fileMenu.setMnemonic (KeyEvent.VK_F);
        JMenuItem exitItem = new JMenuItem (
                          msgSource.getMessage ("exitLabel", null, myLocale));
        exitItem.setMnemonic (KeyEvent.VK_E);
        exitItem.addActionListener ((ActionListener) proxy);
        fileMenu.add (exitItem);
        menuBar.add (fileMenu);

        menuBar.add (Box.createHorizontalGlue ());

        JMenu helpMenu = new JMenu (
                          msgSource.getMessage ("helpLabel", null, myLocale));
        helpMenu.setMnemonic (KeyEvent.VK_H);
        JMenuItem aboutItem = new JMenuItem (
                         msgSource.getMessage ("aboutLabel", null, myLocale));
        aboutItem.setMnemonic (KeyEvent.VK_A);
        aboutItem.addActionListener ((ActionListener) proxy);
        helpMenu.add (aboutItem);
        menuBar.add (helpMenu);
        
        JOptionPane.setDefaultLocale (myLocale);

        frame.setJMenuBar (menuBar);
    }
}
