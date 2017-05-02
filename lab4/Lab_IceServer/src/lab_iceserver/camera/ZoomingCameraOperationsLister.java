/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.camera;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Robert
 */
public class ZoomingCameraOperationsLister extends CameraOperationsLister{
    @Override
    public String[] getListOfOperations(){
        String[] baseOperations = super.getListOfOperations();
        String[] ZoomCameraOperations = new String[]{"zoom (int zoomLevel)"};
        ArrayList<String> ops = new ArrayList<>();
        ops.addAll(Arrays.asList(baseOperations));
        ops.addAll(Arrays.asList(ZoomCameraOperations));
        Object[] objArray = ops.toArray();
        return Arrays.copyOf(objArray, objArray.length, String[].class);
    }
}
