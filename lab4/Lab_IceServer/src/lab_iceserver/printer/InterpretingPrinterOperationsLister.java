/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Robert
 */
public class InterpretingPrinterOperationsLister extends PrinterOperationsLister{
    
    @Override 
    public String[] getListOfOperations(){
        String[] baseOperations = super.getListOfOperations();
        String[] InterpretedPrinterOperations = new String[]{"interpretAndPrint (string s)"};
        ArrayList<String> ops = new ArrayList<>();
        ops.addAll(Arrays.asList(baseOperations));
        ops.addAll(Arrays.asList(InterpretedPrinterOperations));
        Object[] objArray = ops.toArray();
        return Arrays.copyOf(objArray, objArray.length, String[].class);         
    }
    
}
