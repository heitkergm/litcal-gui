package com.dappermoose.litcalgui.main;

import javax.swing.SwingUtilities;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This is the actual main class.
 *
 */
public final class Main
{
    private Main ()
    {
    }

    /**
     * actual main procedure to start our app.
     * instantiates the gui and displays it.
     *
     * @param args the command line arguments
     */
    public static void main (final String[] args)
    {
        ConfigurableApplicationContext context =
            new AnnotationConfigApplicationContext (SpringConfig.class);
        context.registerShutdownHook ();

        // our class is a singleton.
        LitcalGui gui = context.getBean (LitcalGui.class);
        SwingUtilities.invokeLater (gui);
    }
}
