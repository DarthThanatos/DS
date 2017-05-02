/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;
import Demo.DeviceStatePrx;
import Demo.OutOfInkException;
import Demo.PrinterStatePrx;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class PrinterI extends Demo._PrinterDisp{

    private PrinterServiceProvider provider;
    private PrinterOperationsLister lister;
    private PrinterState state;
    
    public PrinterI(){
        provider = new PrinterServiceProvider();
        lister = new PrinterOperationsLister();
        state = new PrinterState();
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
