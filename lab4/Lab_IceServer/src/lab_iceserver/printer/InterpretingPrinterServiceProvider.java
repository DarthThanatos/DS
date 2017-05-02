/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import Demo.OutOfInkException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Robert
 */
public class InterpretingPrinterServiceProvider extends PrinterServiceProvider{
    public String interpretAndPrint(String s, InterpretingPrinterState state) throws OutOfInkException{
        state.operationName = "<<Interpret and Print>>";
        if ( 2 * s.length() > state.inkLevel)
            throw new OutOfInkException(state.inkLevel, s.length(), "Having " + state.inkLevel + " units of ink, printer could not print string of length "
                    + s.length() + " as it demands " + 2 * s.length() + " units; please call fillInk() operation first");
        waitRandom(state);
        state.inkLevel -= 2 * s.length();
        String log = "Before: " + s + "\n";
        Pattern pattern = Pattern.compile("(>>.*?<<)");
        Matcher m = pattern.matcher(s);
        String res = "";
        while (m.find()) {
            res += m.group(1) + " at " + m.start(1) + " up to " + m.end(1) + "\n";
            String line = m.group(1).replace(">>","").replace("<<","").toUpperCase();
            s = s.replace(m.group(1), line);
        }
        log += "After: " + s + "\n";
        state.interpretedParts = res.split("\n");
        state.interpretingResult = log;
        return log;
    }
}
