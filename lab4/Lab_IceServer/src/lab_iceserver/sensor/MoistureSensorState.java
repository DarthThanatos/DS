/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;

/**
 *
 * @author Robert
 */
public class MoistureSensorState extends SensorState{
    public float registeredMoisture = 0;
    public String moistureDiodeColor = "Green";
    public float maximumMoisture = 120;
    
    @Override
    public String getState(){
        return super.getState() + "; registered moisture:  " + registeredMoisture + "; moisture diode color: " + moistureDiodeColor;
    }
    
}
