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
public class BodyTemperatureSensorState extends SensorState{
    public float temperatureInCelsius = 0;
    public float temperatureInFahrenheits = 0;
    public float maximumBodyTemperature = 120;
    public String temperatureDiodeColor = "Green";
    
    @Override
    public String getState(){
        return super.getState() + "; measured temperature in Celsius: " + temperatureInCelsius + "; temperature in Fahrenheit: " + temperatureInFahrenheits + "; temperature diode color: " + temperatureDiodeColor;
    }
}
