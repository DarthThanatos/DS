/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.sensor;

import java.util.ArrayList;
import java.util.Arrays;
import lab_iceserver.devicemanager.DeviceOperationsLister;

/**
 *
 * @author Robert
 */
public class SensorOperationsLister extends DeviceOperationsLister{
    @Override
    public String[] getListOfOperations(){
        String[] baseOperations = super.getListOfOperations();
        String[] SensorOperations = new String[]{"measureMotion (float speed)"};
        ArrayList<String> ops = new ArrayList<>();
        ops.addAll(Arrays.asList(baseOperations));
        ops.addAll(Arrays.asList(SensorOperations));
        Object[] objArray = ops.toArray();
        return Arrays.copyOf(objArray, objArray.length, String[].class);
    }
}
