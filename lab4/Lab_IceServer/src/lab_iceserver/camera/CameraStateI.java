/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import Demo.CameraState;
import Ice.Current;

/**
 *
 * @author Robert
 */
public class CameraStateI extends CameraState{

    public CameraStateI(){
    }
    
    public CameraStateI(float batteryLevel, long operationTime,String lastUserName, float horizontalAngle, float verticalAngle){
        this.batteryLevel = batteryLevel;
        this.horizontalAngle = horizontalAngle;
        this.lastUserName = lastUserName;
        this.verticalAngle = verticalAngle;
        this.operationTime = operationTime;
    }
    
    @Override
    public String formatToString(Current __current) {
        throw new UnsupportedOperationException("Not supported by server side."); 
    }
    
}
