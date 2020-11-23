package com.dappermoose.litcalgui.main;

import javax.swing.SwingUtilities;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j2;

/**
 * This is the actual main class.
 *
 */
@Log4j2
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
        LOG.debug ("starting main litcal gui program");
        ConfigurableApplicationContext context =
            new AnnotationConfigApplicationContext (SpringConfig.class);

        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();

        // our main display class is a bean.
        LitcalGui gui = context.getBean (LitcalGui.class);

        // mark the gui class for swing startup
        SwingUtilities.invokeLater (gui);
    }
}
