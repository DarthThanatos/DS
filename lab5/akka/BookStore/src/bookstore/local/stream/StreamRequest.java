/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.stream;

import java.io.Serializable;


/**
 *
 * @author Robert
 */
public class StreamRequest implements Serializable{
    
    private final String title;
    
    public StreamRequest(String title) {
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
}
