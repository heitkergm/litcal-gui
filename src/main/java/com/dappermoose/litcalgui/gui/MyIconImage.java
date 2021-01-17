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

public final class MyIconImage
{
    private MyIconImage ()
    {
    }
    
    private static Font iconFont;
   
    static void setupIconFont (final Locale myLocale,
                                      final MessageSource msgSource)
           throws FontFormatException, IOException
    {
        InputStream is = MyIconImage.class.
                getResourceAsStream (msgSource.getMessage ("fa", null, myLocale));
        iconFont = Font.createFont (Font.TRUETYPE_FONT, is);
        iconFont = iconFont.deriveFont (Font.PLAIN, 24f);
    }
    
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
