/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class TitleNotFoundAnswer implements Serializable{
    
    private final String title;
    
    public TitleNotFoundAnswer(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
}
