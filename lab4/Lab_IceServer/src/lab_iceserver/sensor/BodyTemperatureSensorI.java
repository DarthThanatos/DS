/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;
import Demo.BrokenDiodeException;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class BodyTemperatureSensorI extends Demo._BodyTemperatureSensorDisp{

    private BodyTemperatureSensorOperationsLister lister;
    private BodyTemperatureSensorServiceProvider provider;
    private BodyTemperatureSensorState state;
    
    public BodyTemperatureSensorI(){
        state = new BodyTemperatureSensorState();
        lister = new BodyTemperatureSensorOperationsLister();
        provider = new BodyTemperatureSensorServiceProvider();
    }
    
    @Override
    public void measureBodyTemperature(float temperature, Current __current) throws BrokenDiodeException {
        provider.measureBodyTemperature(temperature, state);
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
