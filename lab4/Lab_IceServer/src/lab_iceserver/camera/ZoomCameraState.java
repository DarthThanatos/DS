/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

/**
 *
 * @author Robert
 */
public class ZoomCameraState extends CameraState{
    public int zoom = 0;
    public int maxZoom =10;
    
    @Override
    public String getState(){
        return super.getState() + "zoom level: " + zoom + "\n";
    }
}
