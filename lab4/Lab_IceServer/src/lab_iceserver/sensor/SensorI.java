/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;
import Demo.BrokenDiodeException;
import Demo.DeviceStatePrx;
import Demo.SensorStatePrx;
import Demo.speed;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class SensorI extends Demo._SensorDisp{

    private SensorState state;
    private SensorOperationsLister lister;
    private SensorServiceProvider provider;
    
    public SensorI(){
        state = new SensorState();
        lister = new SensorOperationsLister();
        provider = new SensorServiceProvider();
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