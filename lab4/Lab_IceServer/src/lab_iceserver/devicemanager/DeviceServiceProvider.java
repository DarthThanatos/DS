/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.devicemanager;

import java.util.Date;

/**
 *
 * @author Robert
 */
public class DeviceServiceProvider {
    
    public void waitRandom(DeviceState state){
        state.operationStartTime = new Date().getTime();
        try{
            Thread.sleep(((long)Math.random())%2 * 10);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        state.operationEndTime = new Date().getTime();
    }
    
    public void feedBattery(DeviceState state){
        state.operationName = "<<Feed Battery>>";
        state.batteryLevel = 100;
    }
}
