/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver;

import Demo.CameraPrx;
import Demo.CameraPrxHelper;
import Demo.DevicePrx;
import Demo.LaboratoryRoom;
import Demo.Printer;
import java.util.HashMap;
import lab_iceserver.camera.CameraI;
import lab_iceserver.devicemanager.LaboratoryRoomI;

public class Lab_IceServer {
    public static void
    main(String[] args)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            HashMap <String, DevicePrx> proxies = new HashMap<>();
            HashMap <String, String[]> operationsPerServant = new HashMap<>();
            ic = Ice.Util.initialize(args);
            
            Ice.ObjectAdapter adapter =
                 ic.createObjectAdapter("Adapter1");
                //ic.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "tcp -h localhost -p 10000:udp -h localhost -p 10000");
            
            CameraI cameraObject = new CameraI(ic);
            String[] devicesNames = {"Camera_One"};         
            operationsPerServant.put("Camera_One", cameraObject.listActions());            
            
            CameraPrx cameraPrx = CameraPrxHelper.checkedCast(adapter.add(cameraObject, ic.stringToIdentity("Camera_One")));
            proxies.put("Camera_One", cameraPrx);
            
            Ice.Object laboratoryRoom = new LaboratoryRoomI(devicesNames, proxies, operationsPerServant);
            adapter.add(laboratoryRoom, ic.stringToIdentity("LaboratoryRoom"));
            
            adapter.activate();
            System.out.println("Waiting for shutdown... entering main loop");
            ic.waitForShutdown();
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}

