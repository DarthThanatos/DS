/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

/**
 *
 * @author Robert
 */
public class AsciiPrinterState extends PrinterState{
    public String prettyResult = "";
    
    @Override
    public String getState(){
        return super.getState() + "pretty result:\n" + prettyResult + "\n";
    }
}
