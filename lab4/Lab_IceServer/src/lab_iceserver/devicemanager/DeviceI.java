/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.devicemanager;
import Demo.DeviceStatePrx;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class DeviceI extends Demo._DeviceDisp{

    protected int batteryLevel;
    private DeviceState state;
    private DeviceServiceProvider provider;
    private DeviceOperationsLister lister;

    public DeviceI(){
        state = new DeviceState();
        provider = new DeviceServiceProvider();
        lister = new DeviceOperationsLister();
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
