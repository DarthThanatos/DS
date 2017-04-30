/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;
import Demo.DeviceStatePrx;
import Demo.MoistureSensorStatePrx;
import Demo.SensorStatePrx;
import Demo.moisture;
import Demo.speed;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class MoistureSensorI extends Demo._MoistureSensorDisp{

    @Override
    public MoistureSensorStatePrx measureMoisture(moisture moistureObj, Current __current) {
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
