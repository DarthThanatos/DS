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
public class InterpretingPrinterState extends PrinterState{
    public String[] interpretedParts;
    public String interpretingResult = "";
    
    public InterpretingPrinterState(){
        interpretedParts = new String[]{""};
    }
    
    @Override
    public String getState(){
        String interpreted = "";
        for (String interpretedPart : interpretedParts){
            interpreted += "\t-> " + interpretedPart + "\n";
        }
        return super.getState() + "interpreted result:\n"+interpreted + "\nInterpreting result: " + interpretingResult + "\n";
    }
}
