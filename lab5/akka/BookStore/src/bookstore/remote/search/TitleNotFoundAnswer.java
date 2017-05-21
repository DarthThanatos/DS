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
public class TitleNotFoundAnswer extends Answer{
    
    private final String title;
    
    public TitleNotFoundAnswer(String title){
        remotePath += "/SearchActor";
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
}
