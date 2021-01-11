package com.dappermoose.litcalgui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
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

    private Font iconFont;

    @Inject
    private JFrame frame;
    
    @Inject
    private MessageSource msgSource;
    
    @Inject
    private ApplicationContext ctx;
    
    @Inject
    private FrameSetup frameSetup;
        
    @Value ("${git.build.version}")
    private String gitVersion;

    @Value ("${git.commit.time}")
    private String gitCommitTime;

    @Value ("${git.commit.id.abbrev}")
    private String gitCommitId;


        
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
        
        try (InputStream is = this.getClass ().
             getResourceAsStream (msgSource.getMessage ("fa", null, myLocale)))
        {
            iconFont = Font.createFont (Font.TRUETYPE_FONT, is);
            iconFont = iconFont.deriveFont (Font.PLAIN, 24f);
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

    void shutdownApp ()
    {
        String [] options = new String []
        {msgSource.getMessage ("yesLabel", null, myLocale),
         msgSource.getMessage ("noLabel", null, myLocale)};

        // icon is font-awesome question mark
        int choice = JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("quitQuestion", null, myLocale),
                msgSource.getMessage ("quitTitle", null, myLocale),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                makeIcon ('\uf128'), options, options[0]);

        if (choice != JOptionPane.OK_OPTION)
        {
            return;
        }

        frame.setVisible (false);
        frame.dispose ();
    }

    void showAbout ()
    {
        String [] options = new String []
        {msgSource.getMessage ("okLabel", null, myLocale)};

        // icon is font-awesome exclamation        
        JOptionPane.showOptionDialog (frame,
                msgSource.getMessage ("litcalGui",
                    new Object []
                        {(Object) gitVersion, (Object) gitCommitTime,
                         (Object) gitCommitId},
                    myLocale),
                msgSource.getMessage ("aboutTitle", null, myLocale),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                makeIcon ('\uf12a'), options, options[0]);
    }
    
    private ImageIcon makeIcon (final char text)
    {
        JLabel label = new JLabel (Character.toString (text));
        label.setForeground (Color.WHITE);
        label.setFont (iconFont);
        Dimension dim = label.getPreferredSize ();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize (width, height);
        BufferedImage bufImage =
                new BufferedImage (width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics ();
        g2d.setRenderingHint (
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint (
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        label.print (g2d);
        g2d.dispose ();
        return new ImageIcon (bufImage);
    }
}
