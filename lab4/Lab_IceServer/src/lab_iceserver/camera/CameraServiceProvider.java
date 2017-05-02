/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import Demo.RotationOutOfRangeException;
import lab_iceserver.devicemanager.DeviceServiceProvider;

/**
 *
 * @author Robert
 */
public class CameraServiceProvider extends DeviceServiceProvider{
    
    public void turnLeft(float angle, CameraState state) throws RotationOutOfRangeException{
        if (state.horizontalAngle - angle < -state.amplitude) 
            throw new RotationOutOfRangeException(
                    -state.amplitude,
                    state.amplitude,
                    (state.horizontalAngle - angle),
                    "Max amplitude: " + state.amplitude + "; you tried to turn it with the resulting angle: " + (state.horizontalAngle - angle));
        state.operationName = "<<Turn Left>>";
        waitRandom(state);
        state.batteryLevel -= 5;
        state.horizontalAngle -= angle;
    }
    
    public void turnRight(float angle, CameraState state) throws RotationOutOfRangeException{
        if (state.horizontalAngle + angle > state.amplitude) 
            throw new RotationOutOfRangeException(
                    -state.amplitude,
                    state.amplitude,
                    (state.horizontalAngle + angle),
                    "Max amplitude: " + state.amplitude + "; you tried to turn it with the resulting angle: " + (state.horizontalAngle + angle));
        state.operationName = "<<Turn Right>>";
        waitRandom(state);
        state.batteryLevel -= 5;
        state.horizontalAngle += angle;
        
    }
    
    public void turnDown(float angle, CameraState state) throws RotationOutOfRangeException{
        if (state.verticalAngle - angle < -state.amplitude) 
            throw new RotationOutOfRangeException(
                    -state.amplitude,
                    state.amplitude,
                    (state.verticalAngle - angle),
                    "Max amplitude: " + state.amplitude + "; you tried to turn it with the resulting angle: " + (state.verticalAngle - angle));
        state.operationName = "<<Turn Down>>";
        waitRandom(state);
        state.batteryLevel -= 5;
        state.verticalAngle -= angle;
    }
    
    public void turnUp(float angle, CameraState state) throws RotationOutOfRangeException{
        if (state.verticalAngle + angle > state.amplitude) 
            throw new RotationOutOfRangeException(
                    -state.amplitude,
                    state.amplitude,
                    (state.verticalAngle + angle),
                    "Max amplitude: " + state.amplitude + "; you tried to turn it with the resulting angle: " + (state.verticalAngle + angle));
        state.operationName = "<<Turn Up>>";
        waitRandom(state);
        state.batteryLevel -= 5;
        state.verticalAngle += angle;        
    }
}
