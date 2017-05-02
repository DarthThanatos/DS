/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;

import Demo.BrokenDiodeException;
import lab_iceserver.devicemanager.DeviceServiceProvider;

/**
 *
 * @author Robert
 */
public class SensorServiceProvider extends DeviceServiceProvider{
    
    public void measureMotion(float speed, SensorState state) throws BrokenDiodeException{
        state.operationName = "<<Measure Motion>>";
        state.registeredSpeed = speed;
        if (speed > 0 && speed < 60){
            state.diodeColor = "Green";
        }
        else if(speed >= 60 && speed < 90){
            state.diodeColor = "Blue";
            
        }
        else if (speed >= 90 && speed <state.maximumSpeed){
            state.diodeColor = "Red";            
        }
        else{
            throw new BrokenDiodeException(
                state.maximumSpeed, 
                speed,
                "With maximum speed of " +  state.maximumSpeed + " measured " + speed + " and now we must change the diode! Thank you! "
            );
        }
    }
}
