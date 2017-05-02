/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;
import Demo.RotationOutOfRangeException;
import Demo.ZoomOutOfRangeException;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class ZoomingCameraI extends Demo._ZoomingCameraDisp{

    private ZoomCameraState state;
    private ZoomingCameraOperationsLister lister;
    private ZoomingCameraServiceProvider provider;
    
    public ZoomingCameraI(){
        state = new ZoomCameraState();
        provider = new ZoomingCameraServiceProvider();                
        lister = new ZoomingCameraOperationsLister();
    }

    @Override
    public void zoom(int lvl, Current __current) throws ZoomOutOfRangeException {
        provider.zoom(lvl, state);
    }

    @Override
    public void turnLeft(float angle, Current __current) throws RotationOutOfRangeException {
        provider.turnLeft(angle, state);
    }

    @Override
    public void turnRight(float angle, Current __current) throws RotationOutOfRangeException {
        provider.turnRight(angle, state);
    }

    @Override
    public void turnUp(float angle, Current __current) throws RotationOutOfRangeException {
        provider.turnUp(angle, state);
    }

    @Override
    public void turnDown(float angle, Current __current) throws RotationOutOfRangeException {
        provider.turnDown(angle, state);
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
