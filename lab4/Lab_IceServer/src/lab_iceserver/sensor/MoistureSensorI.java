/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;
import Demo.BrokenDiodeException;
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

    private MoistureSensorState state;
    private MoistureSensorOperationsLister lister;
    private MoistureSensorServiceProvider provider;
    
    public MoistureSensorI(){
        state = new MoistureSensorState();
        provider = new MoistureSensorServiceProvider();
        lister = new MoistureSensorOperationsLister();
    }
    
    @Override
    public void measureMoisture(float moisture, Current __current) throws BrokenDiodeException {
        provider.measureMoisture(moisture, state);
    }

    @Override
    public void measureMotion(float speed, Current __current) throws BrokenDiodeException {
        provider.measureMotion(speed, state);
    }

    @Override
    public String getState(Current __current) {
        return state.getState();
    }

    @Override
    public void feedBattery(Current __current) {
        provider.feedBattery(state);
    }

    @Override
    public String[] listActions(Current __current) {
        return lister.getListOfOperations();
    }

    
}
