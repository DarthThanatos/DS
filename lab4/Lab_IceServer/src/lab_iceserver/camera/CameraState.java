/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import lab_iceserver.devicemanager.DeviceState;

/**
 *
 * @author Robert
 */
public class CameraState extends DeviceState{
    public float verticalAngle = 0;
    public float horizontalAngle = 0;
    public float amplitude = 90;
    
    @Override 
    public String getState(){
        return super.getState() + "vertical angle of camera: " + verticalAngle + "\nhorizontal angle of camera: " + horizontalAngle + "\n";
    }
}
