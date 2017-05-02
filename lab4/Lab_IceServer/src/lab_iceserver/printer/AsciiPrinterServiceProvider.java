/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_iceserver.printer;

import Demo.OutOfInkException;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Robert
 */
public class AsciiPrinterServiceProvider extends PrinterServiceProvider{
    
    public String prettyPrint(String s, AsciiPrinterState state) throws OutOfInkException{
        state.operationName = "<<Pretty Print>>";
        if ( 3 * s.length() > state.inkLevel)
            throw new OutOfInkException(state.inkLevel, s.length(), "Having " + state.inkLevel + " units of ink, printer could not print string of length "
                    + s.length() + " as it demands " + 3 * s.length() + " units; please call fillInk() operation first");
        waitRandom(state);
        state.inkLevel -= 3 * s.length();
        String res = "";
        int width = 150;
        int height = 28;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(s, 2, 20);
        for (int y = 0; y < height; y++) {
                StringBuilder strbuild = new StringBuilder();
                for (int x = 0; x < width; x++) 
                        strbuild.append(image.getRGB(x, y) == -16777216 ? " " : "a");
                if (strbuild.toString().trim().isEmpty()) 
                        continue;
                //System.out.println(strbuild);
                res += strbuild + "\n";
        }
        state.prettyResult = res;
        return "printed pretty version of string " + s + " on a console:\n" + res;
    }
}
