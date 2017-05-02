/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import Demo.ZoomOutOfRangeException;

/**
 *
 * @author Robert
 */
public class ZoomingCameraServiceProvider extends CameraServiceProvider{
    public void zoom(int lvl, ZoomCameraState state) throws ZoomOutOfRangeException{
        waitRandom(state);
        if (lvl<0 || lvl > state.maxZoom) 
            throw new ZoomOutOfRangeException(
                    state.maxZoom, 
                    lvl, 
                    "Having max lvl of " + state.maxZoom + " and min lvl of 0, you tried to force the lvl of" + lvl);
        state.zoom = lvl;
    }
}
