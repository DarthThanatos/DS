/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.remote.search;

/**
 *
 * @author Robert
 */
public class DBSearchResult {
    private String uuid;
    private Boolean found;
    private int price;
    private String dbName;
    private String bookName;
    
    public DBSearchResult(String dbName, String uuid, String bookName, Boolean found, int price){
        this.uuid = uuid;
        this.found = found;
        this.price = price;
        this.dbName = dbName;
        this.bookName = bookName;
    }
    
    public String getDbName(){
        return dbName;
    }
    
    public String getBookName(){
        return bookName;
    }
    
    public String getUUID(){
        return uuid;
    }
    
    public Boolean titleFound(){
        return found;
    }
    
    public int getPrice(){
        return price;
    }
}
