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
    float batteryLevel = 100;
    float horizontalAngle = 45;
    float verticalAngle = 0;
    float operationTime = 10;
    String lastUserName = "";
    
    public CameraI(Ice.Communicator ic){
        this.ic = ic;        
    }
    
    @Override
    public String getName(Current __current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getState(Current __current) {
        return "horizontal angle: " + horizontalAngle + " vertical angle: " + verticalAngle;        
    }
    
    @Override
    public void turnLeft(float angle, Current __current) {
        horizontalAngle -= angle;
    }

    @Override
    public void turnRight(float angle, Current __current) {
        horizontalAngle += angle;
    }

    @Override
    public void turnUp(float angle, Current __current) {
        verticalAngle += angle;
    }

    @Override
    public void turnDown(float angle, Current __current) {
        verticalAngle -= angle;
    }

    @Override
    public String[] listActions(Current __current) {
        return new String[] {"turnUp (float angle)", "turnDown(float angle)", "turRight (float angle)", "turnLeft (float angle)"};
    }

    @Override
    public void feedBattery(Current __current) {
        batteryLevel = 100;
    }
    
}
