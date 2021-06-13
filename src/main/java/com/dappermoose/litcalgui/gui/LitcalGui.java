package com.dappermoose.litcalgui.gui;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import org.webjars.WebJarAssetLocator;

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
    private FrameSetup frameSetup;
    
    @Inject
    private Locale myLocale;
    
    @Inject
    private WebJarAssetLocator locator;

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
        LOG.debug ("in initGui, locale is " + myLocale);
               
        try
        {
            MyIconImage.setupIconFont (myLocale, msgSource, locator);
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
