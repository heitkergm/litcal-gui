package com.dappermoose.litcalgui.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
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
    JFrame frame;
    
    @Inject
    MessageSource msgSource;
    
    @Inject
    ApplicationContext ctx;

    private Locale myLocale = null;
    
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
            shutdownApp (frame, myLocale);
        }
        else if (method.getName ().equals ("actionPerformed"))
        {
            ActionEvent evt = (ActionEvent) args[0];
            LOG.debug ("        action command = " + evt.getActionCommand ());
            if (evt.getActionCommand ().equals (
                          msgSource.getMessage ("exitLabel", null, myLocale)))
            {
                shutdownApp (frame, myLocale);
            }
            else if (evt.getActionCommand ().equals (
                         msgSource.getMessage ("aboutLabel", null, myLocale)))
            {
                showAbout (frame, myLocale);
            }
        }
        return retVal;
    }

    @SuppressWarnings ("CallToPrintStackTrace")
    private void initGui ()
    {        
        myLocale = (Locale) ctx.getBean ("locale");
        
        LOG.debug ("in initGui, locale is " + myLocale);
        
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
        
        FrameSetup.setupFrame (frame, myLocale, msgSource, proxy);
        
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
        String [] options = new String []
        {msgSource.getMessage ("okLabel", null, locale)};

        JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("litcalGui", null, locale),
                msgSource.getMessage ("aboutTitle", null, locale),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
}
