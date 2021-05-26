package com.dappermoose.litcalgui.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import lombok.extern.log4j.Log4j2;

/**
 * Document Filter to force JTextField input to be only numbers. Shamelessly
 * "borrowed" from
 * https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 *
 * @author matt
 */
@Log4j2
class MyIntFilter extends DocumentFilter
{

    @Override
    public void insertString (final FilterBypass fb, final int offset,
            final String string, final AttributeSet attr)
            throws BadLocationException
    {

        Document doc = fb.getDocument ();
        StringBuilder sb = new StringBuilder ();
        sb.append (doc.getText (0, doc.getLength ()));
        sb.insert (offset, string);

        if (test (sb.toString ()))
        {
            super.insertString (fb, offset, string, attr);
        }
        else
        {
            LOG.debug ("about to beep in insert string");
        }
    }

    private boolean test (final String text)
    {
        if (text.trim ().isEmpty ())
        {
            return true;
        }
        try
        {
            Integer.parseInt (text);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    @Override
    public void replace (final FilterBypass fb, final int offset,
            final int length, final String text,
            final AttributeSet attrs) throws BadLocationException
    {

        Document doc = fb.getDocument ();
        StringBuilder sb = new StringBuilder ();
        sb.append (doc.getText (0, doc.getLength ()));
        sb.replace (offset, offset + length, text);

        if (test (sb.toString ()))
        {
            super.replace (fb, offset, length, text, attrs);
        }
        else
        {
            LOG.debug ("about to beep in replace");
        }
    }

    @Override
    public void remove (final FilterBypass fb, final int offset,
            final int length) throws BadLocationException
    {
        Document doc = fb.getDocument ();
        StringBuilder sb = new StringBuilder ();
        sb.append (doc.getText (0, doc.getLength ()));
        sb.delete (offset, offset + length);

        if (test (sb.toString ()))
        {
            super.remove (fb, offset, length);
        }
        else
        {
            LOG.debug ("about to beep in remove");
        }
    }
}
