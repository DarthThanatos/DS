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
        state = new CameraState();
        state.batteryLevel = 100;
        state.horizontalAngle = 45;
        state.verticalAngle = 0;
        state.operationTime = 10;
        state.lastUserName = "Rob";
        
    }
    
    @Override
    public CameraStatePrx turnLeft(float angle, Current __current) {
        CameraStatePrx statePrx = CameraStatePrxHelper.checkedCast(ic.propertyToProxy("Camera_One_State.Proxy"));
        return statePrx;
    }

    @Override
    public CameraStatePrx turnRight(float angle, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CameraStatePrx turnUp(float angle, Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CameraStatePrx turnDown(float angle, Current __current) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeviceStatePrx getState(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void feedBattery(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
