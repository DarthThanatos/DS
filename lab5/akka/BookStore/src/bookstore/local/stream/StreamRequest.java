/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.stream;

import bookstore.local.Request;

/**
 *
 * @author Robert
 */
public class StreamRequest extends Request{
    
    private final String title;
    
    public StreamRequest(String title) {
        this.title = title;
        remotePath += "/Streamer";
    }
    
    public String getTitle(){
        return title;
    }
    
}
