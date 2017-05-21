/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.order;

import bookstore.local.Request;

/**
 *
 * @author Robert
 */
public class OrderRequest extends Request{
    
    private final String title;
    
    public OrderRequest(String title){
        this.title = title;
        localPath += "/OrderActor";
        remotePath += "/OrdersTaker";
    }
    
    public String getTitle(){
        return title;
    }
    
}
