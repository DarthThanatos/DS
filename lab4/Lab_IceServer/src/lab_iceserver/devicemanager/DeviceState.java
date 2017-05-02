/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.devicemanager;

/**
 *
 * @author Robert
 */
public class DeviceState {
    public float batteryLevel = 100;
    public long operationStartTime = 0;
    public long operationEndTime = 0;
    public String operationName = "";
    
    public String getState(){
        return "\nOperation " + operationName +  "\nstarted at: " + operationStartTime + "\nended at: " + operationEndTime + "\n"+
                "battery level: "  + batteryLevel + "\n";
    }
}
