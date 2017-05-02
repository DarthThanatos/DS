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
public class BodyTemperatureSensorServiceProvider extends SensorServiceProvider{
    
    public void measureBodyTemperature(float temperature, BodyTemperatureSensorState state) throws BrokenDiodeException{
        state.temperatureInCelsius = temperature;
        state.temperatureInFahrenheits = 0;
        if (temperature > 0 && temperature < 60){
            state.temperatureDiodeColor = "Green";
        }
        else if(temperature >= 60 && temperature < 90){
            state.temperatureDiodeColor = "Blue";
            
        }
        else if (temperature >= 90 && temperature <state.maximumBodyTemperature){
            state.temperatureDiodeColor = "Red";            
        }
        else{
            throw new BrokenDiodeException(
                state.maximumBodyTemperature, 
                temperature,
                "With maximum temperature of " +  state.maximumBodyTemperature + " measured " + temperature + " and now we must change the diode! Thank you!"
            );
        }
    }
}
