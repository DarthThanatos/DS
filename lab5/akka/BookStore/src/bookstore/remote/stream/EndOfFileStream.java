/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.stream;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class EndOfFileStream implements Serializable{
    private final String title;
    
    public EndOfFileStream(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
}
