/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import Demo.OutOfInkException;
import lab_iceserver.devicemanager.DeviceServiceProvider;

/**
 *
 * @author Robert
 */
public class PrinterServiceProvider extends DeviceServiceProvider{
    
    public String printString(String s, PrinterState state) throws OutOfInkException{
        if (s.length() > state.inkLevel)
            throw new OutOfInkException(state.inkLevel, s.length(), "Having " + state.inkLevel + " units of ink, printer could not print string of length "
                    + s.length() + "; please call fillInk() operation first");
        waitRandom(state);
        state.operationName = "<<Print String>>";
        state.inkLevel -= s.length();
        state.result = s;
        return "printed string " + s + "on a console";
    }
    
    public void fillInk(PrinterState state){
        state.inkLevel = 100;
        state.operationName = "<<Fill Ink>>";
    }
}
