/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver;

/**
 *
 * @author Robert
 */

public class PrinterI extends Demo._PrinterDisp {
    public String
    printString(String s, Ice.Current current)
    {
        System.out.println(s);
        return s + " yo";
    }
}
