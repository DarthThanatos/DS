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
public class Device extends Demo._DeviceDisp{

    protected int batteryLevel;
    
    @Override
    public String getName(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getState(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void feedBattery(Current __current) {
        
    }

    @Override
    public String[] listActions(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
