/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import Demo.OutOfInkException;

/**
 *
 * @author Robert
 */
public class InterpretingPrinterServiceProvider extends PrinterServiceProvider{
    public String interpretAndPrint(String s, InterpretingPrinterState state) throws OutOfInkException{
        if ( 2 * s.length() > state.inkLevel)
            throw new OutOfInkException(state.inkLevel, s.length(), "Having " + state.inkLevel + " units of ink, printer could not print string of length "
                    + s.length() + " as it demands " + 2 * s.length() + " units; please call fillInk() operation first");
        waitRandom(state);
        state.inkLevel -= 2 * s.length();
        state.interpretedParts = new String[]{""};
        return "printed interpreted version of string " + s + " on a console";
    }
}
