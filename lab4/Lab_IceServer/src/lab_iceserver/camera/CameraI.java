/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;
import Demo.CameraState;
import Demo.CameraStatePrx;
import Demo.CameraStatePrxHelper;
import Demo.DeviceStatePrx;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class CameraI extends Demo._CameraDisp{

    Ice.Communicator ic;
    CameraState state;
    
    public CameraI(Ice.Communicator ic){
        this.ic = ic;
        state = new CameraStateI();
        
    }
    
    @Override
    public String getName(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getState(Current __current) {
        return "horizontal angle: " + state.horizontalAngle + " vertical angle: " + state.verticalAngle;        
    }

    @Override
    public void feedBattery(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnLeft(float angle, Current __current) {
        state.horizontalAngle -= angle;
    }

    @Override
    public CameraState turnRight(float angle, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CameraState turnUp(float angle, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CameraState turnDown(float angle, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] listActions(Current __current) {
        return new String[] {"turnUp (float angle)", "turnDown(float angle)", "turRight (float angle)", "turnLeft (float angle)"};
    }
    
}
