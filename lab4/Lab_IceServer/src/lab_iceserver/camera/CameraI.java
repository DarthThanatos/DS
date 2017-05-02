/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import Demo.RotationOutOfRangeException;
import Ice.Current;
import java.util.Date;
/**
 *
 * @author Robert
 */
public class CameraI extends Demo._CameraDisp{

    private CameraState state;
    private CameraServiceProvider provider;
    private CameraOperationsLister lister;

    public CameraI(){
        state = new CameraState();
        provider = new CameraServiceProvider();
        lister = new CameraOperationsLister();
    }
    
    
    @Override
    public String getState(Current __current) { 
        return state.getState();
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
    public String[] listActions(Current __current) {
        return lister.getListOfOperations();
    }

    @Override
    public void feedBattery(Current __current) {
        provider.feedBattery(state);
    }
    
}
