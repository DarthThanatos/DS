/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;
import Demo.BodyTemperatureStatePrx;
import Demo.DeviceStatePrx;
import Demo.SensorStatePrx;
import Demo.speed;
import Demo.temperature;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class BodyTemperatureSensor extends Demo._BodyTemperatureSensorDisp{

    @Override
    public BodyTemperatureStatePrx measureBodyTemperature(temperature temperatureObj, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SensorStatePrx measureMotion(speed speedObj, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeviceStatePrx getState(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void feedBattery(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
