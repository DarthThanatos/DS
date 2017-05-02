/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;

import Demo.BrokenDiodeException;


/**
 *
 * @author Robert
 */
public class MoistureSensorServiceProvider extends SensorServiceProvider{
    public void measureMoisture(float moisture, MoistureSensorState state) throws BrokenDiodeException{
        state.operationName = "<<Measure Moisture>>";
        state.registeredMoisture = moisture;
        if (moisture > 0 && moisture < 60){
            state.moistureDiodeColor = "Green";
        }
        else if(moisture >= 60 && moisture < 90){
            state.moistureDiodeColor = "Blue";
            
        }
        else if (moisture >= 90 && moisture <state.maximumMoisture){
            state.moistureDiodeColor = "Red";            
        }
        else{
            throw new BrokenDiodeException(
                state.maximumMoisture, 
                moisture,
                "With maximum speed of " +  state.maximumMoisture + " measured " + moisture + " and now we must change the diode! Thank you!"
            );
        }
    }
}
