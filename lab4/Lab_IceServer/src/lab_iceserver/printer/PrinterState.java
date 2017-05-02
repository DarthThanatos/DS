/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import lab_iceserver.devicemanager.DeviceState;

/**
 *
 * @author Robert
 */
public class PrinterState extends DeviceState{
    public int inkLevel;
    public String result;
    
    @Override
    public String getState(){
        return super.getState() + " ink level: " + inkLevel + "; last result: " + result;
    }
}
