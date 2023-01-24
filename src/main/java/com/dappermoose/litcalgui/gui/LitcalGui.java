package com.dappermoose.litcalgui.gui;

import java.util.Locale;

import jakarta.inject.Inject;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Litcal Swing App.
 */
@Component
@Slf4j
public class LitcalGui implements Runnable
{

    @Inject
    private JFrame frame;

    @Inject
    private FrameSetup frameSetup;

    @Inject
    private Locale myLocale;

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
        log.debug ("in initGui, locale is " + myLocale);

        frameSetup.setupFrame ();

        //Display the window.
        frame.pack ();
        frame.setVisible (true);
    }
}
