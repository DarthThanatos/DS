/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.devicemanager;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class LaboratoryRoomI extends Demo._LaboratoryRoomDisp{

    private String[] devicesNames;
    
    public LaboratoryRoomI(String[] devicesNames){
        this.devicesNames = devicesNames;
    }
    
    @Override
    public String[] getDevicesNamesList(Current __current) {
        return devicesNames;
    }
    
}
