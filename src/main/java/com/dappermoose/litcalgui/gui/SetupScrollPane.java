package com.dappermoose.litcalgui.gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.springframework.stereotype.Component;

/**
 * create the result window.
 * A text pane wrapped by a scroll pane.
 * 
 * @author heitkergm@acm.org
 */
@Component
public final class SetupScrollPane
{
    /**
     * default constructor.
     */
    public SetupScrollPane ()
    {
    }

    /**
     * set up the scroll pane which shows the results.
     * 
     * @return the scroll pane with original contents
     */
    JScrollPane setupScrollPane ()
    {
        // create JTextPane
        JTextPane textPane = new JTextPane ();
        textPane.setPreferredSize (new Dimension (1024, 768));
        textPane.setEditable (false);
        textPane.setContentType ("text/html");
        textPane.setText ("<html>\n" + "<head>\n" + "<style type=\"text/css\">\n" +
            "body {font-family:sans-serif; font-size: x-large; background-color: black; color: white}\n" +
            "</style>\n" + "</head>\n" + "<body>\n" + "Hello\n" + "</body>\n" +
            "</html>\n");

        return new JScrollPane (textPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }   
}
