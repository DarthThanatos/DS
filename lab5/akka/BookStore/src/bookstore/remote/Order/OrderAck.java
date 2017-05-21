/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.Order;

import bookstore.remote.Answer;

/**
 *
 * @author Robert
 */
public class OrderAck extends Answer{
    
    private String acknowledgedTitle;
    
    public OrderAck(String acknowledgedTitle){
        this.acknowledgedTitle = acknowledgedTitle;
    }    
    
    public String getAcknowledgedTitle(){
        return acknowledgedTitle;
    }
}
