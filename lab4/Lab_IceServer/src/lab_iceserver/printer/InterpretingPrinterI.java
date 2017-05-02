/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;
import Demo.DeviceStatePrx;
import Demo.InterpretingPrinterStatePrx;
import Demo.OutOfInkException;
import Demo.PrinterStatePrx;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class InterpretingPrinterI extends Demo._InterpretingPrinterDisp{

    private InterpretingPrinterState state;
    private InterpretingPrinterOperationsLister lister;
    private InterpretingPrinterServiceProvider provider;
    
    public InterpretingPrinterI(){
        provider =  new InterpretingPrinterServiceProvider();
        lister = new InterpretingPrinterOperationsLister();
        state = new InterpretingPrinterState();
    }
    
    @Override
    public String interpretAndPrint(String s, Current __current) throws OutOfInkException {
        return provider.interpretAndPrint(s, state);
    }

    @Override
    public String printString(String s, Current __current) throws OutOfInkException {
        return provider.printString(s, state);
    }

    @Override
    public void fillInk(Current __current) {
        provider.fillInk(state);
    }

    @Override
    public String getState(Current __current) {
        return state.getState();
    }

    @Override
    public void feedBattery(Current __current) {
        provider.feedBattery(state);
    }

    @Override
    public String[] listActions(Current __current) {
        return lister.getListOfOperations();
    }
   
}
