/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver;

import Demo.LaboratoryRoom;
import Demo.Printer;
import lab_iceserver.camera.CameraI;
import lab_iceserver.devicemanager.LaboratoryRoomI;

public class Lab_IceServer {
    public static void
    main(String[] args)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            Ice.ObjectAdapter adapter =
                ic.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "tcp -h localhost -p 10000:udp -h localhost -p 10000");
            Ice.Object object = new PrinterI();
            
            Ice.Object cameraObject = new CameraI(ic);
            String[] devicesNames = {"Camera_One"};
            Ice.Object labRoom = new LaboratoryRoomI(devicesNames);
            adapter.add(object, ic.stringToIdentity("SimplePrinter"));
            adapter.add(cameraObject, ic.stringToIdentity("Camera_One"));
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

