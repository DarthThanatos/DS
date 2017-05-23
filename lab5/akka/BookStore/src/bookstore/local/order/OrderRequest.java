/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.order;

import java.io.Serializable;


/**
 *
 * @author Robert
 */
public class OrderRequest implements Serializable{
    
    private final String title;
    
    public OrderRequest(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
}
