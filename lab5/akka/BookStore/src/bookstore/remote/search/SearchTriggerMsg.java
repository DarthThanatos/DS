/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

import bookstore.local.search.SearchRequest;
import java.util.UUID;
/**
 *
 * @author Robert
 */
public class SearchTriggerMsg {
    
    private final String searchedTitle;
    private final String uuid;
    
    public SearchTriggerMsg(SearchRequest rq){
        searchedTitle = rq.getSearchedTitle();
        uuid = UUID.randomUUID().toString();
    }
    
    
    public String getUUID(){
        return uuid;
    }
    
    public String getSearchedTitle(){
        return searchedTitle;
    }
}
