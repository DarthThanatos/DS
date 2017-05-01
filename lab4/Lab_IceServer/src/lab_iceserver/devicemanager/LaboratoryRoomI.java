/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.devicemanager;
import Demo.AlreadyControlledException;
import Demo.DeviceNotControlledException;
import Demo.DevicePrx;
import Demo.NotKnownDeviceException;
import Ice.Current;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author Robert
 */
public class LaboratoryRoomI extends Demo._LaboratoryRoomDisp{

    private String[] devicesNames;
    private HashMap<String,String> associationMap; //mapping deviceId -> userId
    private Lock lock;
    private HashMap<String, DevicePrx> proxies;
    private HashMap<String, String[]> operationsPerServant;
    
    public LaboratoryRoomI(String[] devicesNames, HashMap<String, DevicePrx> proxies, HashMap<String, String[]> operationsPerServant){
        this.devicesNames = devicesNames;
        associationMap = new HashMap<String,String>();
        for (String deviceName: devicesNames) associationMap.put(deviceName,""); //for now nobody controlls the device
        lock = new ReentrantLock();
        this.proxies = proxies;
        this.operationsPerServant = operationsPerServant;
    }
    
    @Override
    public String[] getDevicesNamesList(Current __current) {
        return devicesNames;
    }

    @Override
    public DevicePrx takeControlOverDevice(String deviceId, String userId, Current __current) throws AlreadyControlledException, NotKnownDeviceException {
         lock.lock();
           if (!associationMap.containsKey(deviceId)) {
               lock.unlock();
               throw new NotKnownDeviceException("Device " + deviceId + " is not recognized");
           }
           if(!associationMap.get(deviceId).equals("")) {
               lock.unlock();
               throw new AlreadyControlledException("Device " + deviceId + " is already controlled by " + associationMap.get(deviceId));
           }
           associationMap.put(deviceId, userId);
           lock.unlock();
           return proxies.get(deviceId);
    }

    @Override
    public void releaseDevice(String deviceId, String userId, Current __current) throws DeviceNotControlledException, NotKnownDeviceException {
        if (!associationMap.containsKey(deviceId)) throw new NotKnownDeviceException("Device " + deviceId + " is not known");
        if (!associationMap.get(deviceId).equals(userId)) throw new DeviceNotControlledException("User " + userId + " cannot release device " + deviceId + "because they do not control it");
        associationMap.put(deviceId,"");
    }

    @Override
    public String[] getDeviceOperationsList(String deviceId, Current __current) throws NotKnownDeviceException{
        if(!operationsPerServant.containsKey(deviceId)) throw new NotKnownDeviceException("Device " + deviceId + " is not known");
        return operationsPerServant.get(deviceId);
    }
}
