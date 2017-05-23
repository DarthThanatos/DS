/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.search;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class SearchRequest implements Serializable{
    
    private final String searchedTitle;
    
    public SearchRequest(String searchedTitle){
        this.searchedTitle = searchedTitle;
    }
    
    public String getSearchedTitle(){
        return searchedTitle;
    }
}
