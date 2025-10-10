
package com.dappermoose.litcalgui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import jakarta.inject.Inject;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import org.jspecify.annotations.NonNull;

import lombok.extern.slf4j.Slf4j;

/**
 * make the menu bar.
 * Create the menu bar and setup its listeners.
 * @author heitkergm@acm.org
 */
@Component
@Slf4j
public final class MakeMenuBar
{
    @Inject
    private ActionListeners al;

    @Inject
    private MakeCalendar mc;

    @Inject
    private MessageSource messageSource;

    @Inject
    @NonNull
    private Locale myLocale;

    /**
     * default constructor.
     */
    public MakeMenuBar ()
    {
    }

    /**
     * creates the menubar object used in the program.
     *
     * @return the menu bar object
     */
    JMenuBar makeMenuBar ()
    {
        // make menu bar
        JMenuBar menuBar = new JMenuBar ();
        JMenu fileMenu = new JMenu (
                        messageSource.getMessage ("fileLabel", null, myLocale));
        fileMenu.setMnemonic (KeyEvent.VK_F);
        JMenuItem exitItem = new JMenuItem (
                        messageSource.getMessage ("exitLabel", null, myLocale));
        exitItem.setMnemonic (KeyEvent.VK_E);
        exitItem.addActionListener (new ActionListener ()
        {
            @Override
            public void actionPerformed (final ActionEvent e)
            {
                log.debug ("Chose File->Exit");
                al.shutdownApp ();
            }
        });
        fileMenu.add (exitItem);
        menuBar.add (fileMenu);

        // request year
        JMenu calMenu = new JMenu (
                         messageSource.getMessage ("calLabel", null, myLocale));
        calMenu.setMnemonic (KeyEvent.VK_C);

        JMenuItem makeItem = new JMenuItem (
                        messageSource.getMessage ("makeLabel", null, myLocale));
        makeItem.setMnemonic (KeyEvent.VK_M);
        makeItem.addActionListener (new ActionListener ()
        {
            @Override
            public void actionPerformed (final ActionEvent e)
            {
                log.debug ("Chose Calendar->Make");
                mc.makeCalendar ();
            }
        });
        calMenu.add (makeItem);
        menuBar.add (calMenu);

        menuBar.add (Box.createHorizontalGlue ());

        // help and about
        JMenu helpMenu = new JMenu (
                        messageSource.getMessage ("helpLabel", null, myLocale));
        helpMenu.setMnemonic (KeyEvent.VK_H);
        JMenuItem aboutItem = new JMenuItem (
                       messageSource.getMessage ("aboutLabel", null, myLocale));
        aboutItem.setMnemonic (KeyEvent.VK_A);
        aboutItem.addActionListener (new ActionListener ()
        {
            @Override
            public void actionPerformed (final ActionEvent e)
            {
                log.debug ("Chose Help->About");
                al.showAbout ();
            }
        });
        helpMenu.add (aboutItem);
        menuBar.add (helpMenu);

        return menuBar;
    }
}
