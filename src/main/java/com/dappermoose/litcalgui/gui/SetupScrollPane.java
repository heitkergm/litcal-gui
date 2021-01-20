package com.dappermoose.litcalgui.gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.springframework.stereotype.Component;

/**
 * create the result window.
 * A text pane wrapped by a scroll pane.
 * 
 * @author matt
 */
@Component
public final class SetupScrollPane
{    
    JScrollPane setupScrollPane ()
    {
        // create JTextPane
        JTextPane textPane = new JTextPane ();
        textPane.setPreferredSize (new Dimension (640, 480));
        textPane.setEditable (false);
        textPane.setContentType ("text/html");
        textPane.setText ("<html>\n" + "<head>\n" + "<style type=\"text/css\">\n" +
            "body {font-family:sans-serif; font-size: large; background-color: black; color: white}\n" +
            "</style>\n" + "</head>\n" + "<body>\n" + "Hello\n" + "</body>\n" +
            "</html>\n");

        JScrollPane scrollPane = new JScrollPane (textPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        return scrollPane;
    }   
}
