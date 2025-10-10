package com.dappermoose.litcalgui.main;

import javax.swing.SwingUtilities;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import org.jspecify.annotations.NonNull;

import com.dappermoose.litcalgui.gui.LitcalGui;

import lombok.extern.slf4j.Slf4j;

/**
 * This is the actual main class.
 *
 * @author heitkergm@acm.org
 */
@Slf4j
@ComponentScan
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

        ApplicationContext context = new SpringApplicationBuilder (Main.class)
                .headless (false).web (WebApplicationType.NONE).registerShutdownHook (true)
                .run (args);

        // our main display class is a bean.
        LitcalGui gui = context.getBean (LitcalGui.class);

        // mark the gui class for swing startup
        SwingUtilities.invokeLater (gui);
    }
}
