package com.dappermoose.litcalgui.gui;

import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import lombok.extern.log4j.Log4j2;

/**
 * RequestFocusListener puts the focus on the field to which it's attached.
 * "Borrowed" shamelessly from
 * http://www.camick.com/java/source/RequestFocusListener.java
 *
 * @author matt
 */
@Log4j2
class RequestFocusListener implements AncestorListener
{

    private boolean remove = false;

    /**
     * Convenience constructor. The listener is only used once and then it is
     * removed from the component.
     */
    RequestFocusListener ()
    {
        this (true);
    }

    /**
     * Constructor that controls whether this listen can be used once or
     * multiple times.
     *
     * @param removeListener when true this listener is only invoked once
     *                       otherwise it can be invoked multiple times.
     */
    RequestFocusListener (final boolean removeListener)
    {
        remove = removeListener;
    }

    @Override
    public void ancestorAdded (final AncestorEvent e)
    {
        LOG.debug ("ancestor added, remove is " + Boolean.toString (remove));

        JComponent component = e.getComponent ();
        component.requestFocusInWindow ();

        if (remove)
        {
            component.removeAncestorListener (this);
        }
    }

    @Override
    public void ancestorRemoved (final AncestorEvent event)
    {
    }

    @Override
    public void ancestorMoved (final AncestorEvent event)
    {
    }
}
