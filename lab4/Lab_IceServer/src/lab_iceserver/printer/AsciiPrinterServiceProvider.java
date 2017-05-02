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
public class AsciiPrinterServiceProvider extends PrinterServiceProvider{
    
    public String prettyPrint(String s, AsciiPrinterState state) throws OutOfInkException{
        if ( 3 * s.length() > state.inkLevel)
            throw new OutOfInkException(state.inkLevel, s.length(), "Having " + state.inkLevel + " units of ink, printer could not print string of length "
                    + s.length() + " as it demands " + 3 * s.length() + " units; please call fillInk() operation first");
        waitRandom(state);
        state.inkLevel -= 3 * s.length();
        state.prettyResult = s;
        return "printed pretty version of string " + s + " on a console";
    }
}
