/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local.search;

import bookstore.local.Request;
import java.util.UUID;
/**
 *
 * @author Robert
 */
public class SearchRequest extends Request{
    
    private final String searchedTitle;
    private final String uuid;
    
    public SearchRequest(String searchedTitle){
        this.searchedTitle = searchedTitle;
        uuid = UUID.randomUUID().toString();
        remotePath += "/SearchManager";
    }
    
    public String getUUID(){
        return uuid;
    }
    
    public String getSearchedTitle(){
        return searchedTitle;
    }
}
