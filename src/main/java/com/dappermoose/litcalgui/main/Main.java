package com.dappermoose.litcalgui.main;

import javax.swing.SwingUtilities;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.lang.NonNull;

import com.dappermoose.litcalgui.gui.LitcalGui;

import lombok.extern.slf4j.Slf4j;

/**
 * This is the actual main class.
 *
 * @author heitkergm@acm.org
 */
@Slf4j
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
    public static void main (final @NonNull String[] args)
    {
        log.debug ("starting main litcal gui program");
        SimpleCommandLinePropertySource ps;
        ps = new SimpleCommandLinePropertySource (args);
        String [] pnames = ps.getPropertyNames ();
        for (String pname : pnames)
        {
            if (pname != null)
            {
                // deepcode ignore LogLevelCheck:
                log.debug ("property name: " + pname + ": " + ps.getProperty (pname));
            }
        }

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext ();
        context.getEnvironment ().getPropertySources ().addFirst (ps);

        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();

        // now run SpringConfig and inject the locale bean
        context.register (SpringConfig.class);
        context.refresh ();

        // our main display class is a bean.
        LitcalGui gui = context.getBean (LitcalGui.class);

        // mark the gui class for swing startup
        SwingUtilities.invokeLater (gui);
        context.close ();
    }
}
