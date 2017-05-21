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
    private final String temppath;
    
    public EndOfFileStream(String title, String temppath){
        this.temppath = temppath;
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getTempPath(){
        return temppath;
    }
}
