/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;

import lab_iceserver.devicemanager.DeviceState;

/**
 *
 * @author Robert
 */
public class SensorState extends DeviceState{
    public String diodeColor = "Green";
    public float registeredSpeed = 0;
    public float maximumSpeed = 120;
    
    @Override 
    public String getState(){
        return super.getState() + "; diode color: " + diodeColor + "; reacted on speed: " + registeredSpeed;
    }
}
