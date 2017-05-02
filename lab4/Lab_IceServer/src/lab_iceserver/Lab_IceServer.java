/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver;

import Demo.*;
import java.util.HashMap;
import lab_iceserver.camera.*;
import lab_iceserver.sensor.*;
import lab_iceserver.printer.*;
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
            
            String[] devicesNamesPrefixes = {"Camera", "ZoomingCamera", 
                "AsciiPrinter","InterpretingPrinter","Printer",
                "Sensor", "MoistureSensor", "BodyTemperatureSensor"
            };         
            String[] devicesNames = new String[devicesNamesPrefixes.length * 2];
            for (int i = 0; i < devicesNamesPrefixes.length; i++){
                devicesNames[2*i] = devicesNamesPrefixes[i] + "_One";
                devicesNames[2*i + 1] = devicesNamesPrefixes[i] + "_Two";
            }
            String[] devicesNamesSuffixes = {"One", "Two"};
            
            for (int i = 0; i < 2; i++){
                CameraI cameraObject = new CameraI();
                operationsPerServant.put("Camera_" + devicesNamesSuffixes[i], cameraObject.listActions());            
                CameraPrx cameraPrx = CameraPrxHelper.checkedCast(adapter.add(cameraObject, ic.stringToIdentity("Camera_" + devicesNamesSuffixes[i])));
                proxies.put("Camera_" + devicesNamesSuffixes[i], cameraPrx);

                ZoomingCameraI zoomingCameraObject = new ZoomingCameraI();
                operationsPerServant.put("ZoomingCamera_" + devicesNamesSuffixes[i], zoomingCameraObject.listActions());            
                ZoomingCameraPrx zoomingCameraPrx = ZoomingCameraPrxHelper.checkedCast(adapter.add(zoomingCameraObject, ic.stringToIdentity("ZoomingCamera_" + devicesNamesSuffixes[i])));
                proxies.put("ZoomingCamera_" + devicesNamesSuffixes[i], zoomingCameraPrx);   
                
                AsciiPrinterI asciiPrinterObject = new AsciiPrinterI();
                operationsPerServant.put("AsciiPrinter_" + devicesNamesSuffixes[i], asciiPrinterObject.listActions());            
                AsciiPrinterPrx asciiPrinterPrx = AsciiPrinterPrxHelper.checkedCast(adapter.add(asciiPrinterObject, ic.stringToIdentity("AsciiPrinter_" + devicesNamesSuffixes[i])));
                proxies.put("AsciiPrinter_" + devicesNamesSuffixes[i], asciiPrinterPrx); 

                InterpretingPrinterI interpretingPrinterObject = new InterpretingPrinterI();
                operationsPerServant.put("InterpretingPrinter_" + devicesNamesSuffixes[i], interpretingPrinterObject.listActions());            
                InterpretingPrinterPrx interpretingPrinterPrx = InterpretingPrinterPrxHelper.checkedCast(adapter.add(interpretingPrinterObject, ic.stringToIdentity("InterpretingPrinter_" + devicesNamesSuffixes[i])));
                proxies.put("InterpretingPrinter_" + devicesNamesSuffixes[i], interpretingPrinterPrx);     
                
                PrinterI printerObject = new PrinterI();
                operationsPerServant.put("Printer_" + devicesNamesSuffixes[i], printerObject.listActions());            
                PrinterPrx printerPrx = PrinterPrxHelper.checkedCast(adapter.add(printerObject, ic.stringToIdentity("Printer_" + devicesNamesSuffixes[i])));
                proxies.put("Printer_" + devicesNamesSuffixes[i], printerPrx);
  
                SensorI sensorObject = new SensorI();
                operationsPerServant.put("Sensor_" + devicesNamesSuffixes[i], sensorObject.listActions());            
                SensorPrx sensorPrx = SensorPrxHelper.checkedCast(adapter.add(sensorObject, ic.stringToIdentity("Sensor_" + devicesNamesSuffixes[i])));
                proxies.put("Sensor_" + devicesNamesSuffixes[i], sensorPrx);
                
                MoistureSensorI moistureSensorObject = new MoistureSensorI();
                operationsPerServant.put("MoistureSensor_" + devicesNamesSuffixes[i], moistureSensorObject.listActions());            
                MoistureSensorPrx moistureSensorPrx = MoistureSensorPrxHelper.checkedCast(adapter.add(moistureSensorObject, ic.stringToIdentity("MoistureSensor_" + devicesNamesSuffixes[i])));
                proxies.put("MoistureSensor_" + devicesNamesSuffixes[i], moistureSensorPrx);                
            
                BodyTemperatureSensorI btSensorObject = new BodyTemperatureSensorI();
                operationsPerServant.put("BodyTemperatureSensor_" + devicesNamesSuffixes[i], btSensorObject.listActions());            
                BodyTemperatureSensorPrx btSensorPrx = BodyTemperatureSensorPrxHelper.checkedCast(adapter.add(btSensorObject, ic.stringToIdentity("BodyTemperatureSensor_" + devicesNamesSuffixes[i])));
                proxies.put("BodyTemperatureSensor_" + devicesNamesSuffixes[i], btSensorPrx);
            }
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

