package com.dappermoose.litcalgui.main;

import java.util.Locale;

import javax.swing.SwingUtilities;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import com.dappermoose.litcalgui.gui.LitcalGui;

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
        SimpleCommandLinePropertySource ps;
        ps = new SimpleCommandLinePropertySource (args);
        String [] pnames = ps.getPropertyNames ();
        for (String pname : pnames)
        {
            LOG.debug ("property name: " + pname + ": " + ps.getProperty (pname));
        }
        
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext (SpringConfig.class);
        context.getEnvironment ().getPropertySources ().addFirst (ps);
               
        // get the locale and save it off as a bean
        LOG.debug ("context is " + context);
        String localeName = context.getEnvironment ().getProperty ("locale");
        LOG.debug ("locale is " + localeName);
        if (localeName == null)
        {
            localeName = Locale.getDefault ().getDisplayName ();
        }
        context.registerBean ("locale", Locale.class, localeName);
        Locale myLocale = context.getBean (Locale.class);
        LOG.debug ("locale bean is " + myLocale);


        // make sure our context shuts down when JVM wants to
        context.registerShutdownHook ();

        // our main display class is a bean.
        LitcalGui gui = context.getBean (LitcalGui.class);

        // mark the gui class for swing startup
        SwingUtilities.invokeLater (gui);
    }
}
