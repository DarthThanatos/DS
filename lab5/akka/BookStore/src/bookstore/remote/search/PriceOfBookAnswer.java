/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import bookstore.remote.Answer;

/**
 *
 * @author Robert
 */
public class PriceOfBookAnswer extends Answer{
    
    private final int price;
    private final String title;
    
    public PriceOfBookAnswer(String title, int price){
        this.price = price;
        this.title = title;
        remotePath += "/SearchActor";
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getPrice(){
        return price;
    }
}
