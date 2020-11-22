package com.dappermoose.litcalgui.main;

import javax.swing.SwingUtilities;

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
        // our class is a singleton.
        LitcalGui gui = new LitcalGui ();
        SwingUtilities.invokeLater (gui);
    }
}
