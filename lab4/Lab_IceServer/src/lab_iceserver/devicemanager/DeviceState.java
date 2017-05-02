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
    public long operationStartTime;
    public long operationEndTime;
    
    public String getState(){
        return "Operation started at: " + operationStartTime + ", ended at: " + operationEndTime;
    }
}
