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

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.springframework.context.MessageSource;

import org.webjars.WebJarAssetLocator;

import lombok.extern.log4j.Log4j2;

/**
 * class to provide the Font-Awesome icons as graphic images.
 * 
 * @author matt
 */
@Log4j2
public final class MyIconImage
{
    private MyIconImage ()
    {
    }
    
    private static Font iconFont;
   
    /**
     * routine to instantiate the font awesome as an icon font.
     * 
     * @param myLocale the current locale
     * @param msgSource the message bundles
     * @param locator the webjars version-agnostic jar finder
     * 
     * @throws FontFormatException if error in font file
     * @throws IOException if fount file not found
     */
    static void setupIconFont (final Locale myLocale,
                               final MessageSource msgSource,
                               final WebJarAssetLocator locator)
           throws FontFormatException, IOException
    {
        String path = locator.getFullPath (msgSource.getMessage ("fa", null, myLocale));
        LOG.debug ("path is " + path);
        InputStream is = MyIconImage.class.
                getResourceAsStream ("/" + path);
        iconFont = Font.createFont (Font.TRUETYPE_FONT, is);
        iconFont = iconFont.deriveFont (Font.PLAIN, 24f);
    }
    
    /**
     * This routine takes the font character and converts it to an icon.
     * 
     * @param text the font character to convert into an icon
     * 
     * @return the actual icon
     */
    static ImageIcon makeIcon (final char text)
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
