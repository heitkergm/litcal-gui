package com.dappermoose.litcalgui.gui;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


/**
 * Litcal Swing App.
 */
@Component
@Log4j2
public class LitcalGui implements Runnable
{

    @Inject
    private JFrame frame;
    
    @Inject
    private MessageSource msgSource;
    
    @Inject
    private ApplicationContext ctx;
    
    @Inject
    private FrameSetup frameSetup;
    
    @Inject
    private ActionListeners al;
        
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

    private void initGui ()
    {
        myLocale = (Locale) ctx.getBean ("locale");
        
        LOG.debug ("in initGui, locale is " + myLocale);
        
        al.setupMyLocale (myLocale);
        
        try
        {
            MyIconImage.setupIconFont (myLocale, msgSource);
        }
        catch (IOException | FontFormatException ex)
        {
            LOG.fatal ("Font error", ex);
            frame.dispose ();
        }
        
        frameSetup.setupFrame ();
        
        //Display the window.
        frame.pack ();
        frame.setVisible (true);
    }
}
