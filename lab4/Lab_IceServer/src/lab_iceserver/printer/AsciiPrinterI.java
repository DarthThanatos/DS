/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;
import Demo.OutOfInkException;
import Ice.Current;
/**
 *
 * @author Robert
 */
public class AsciiPrinterI extends Demo._AsciiPrinterDisp{

    private AsciiPrinterState state;
    private AsciiPrinterOperationsLister lister;
    private AsciiPrinterServiceProvider provider;
    
    public AsciiPrinterI(){
        provider = new AsciiPrinterServiceProvider();
        state = new AsciiPrinterState();
        lister = new AsciiPrinterOperationsLister();
    }
    
    @Override
    public String prettyPrint(String s, Current __current) throws OutOfInkException {
        return provider.prettyPrint(s, state);
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
