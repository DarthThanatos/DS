/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.local;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class Request implements Serializable{
    protected String localPath;
    protected String remotePath;
    
    public Request(){
        localPath = "akka.tcp://local_system@127.0.0.1:2552/user/local";
        remotePath = "akka.tcp://remote_system@127.0.0.1:3552/user/remote";
    }
    
    
    public String getLocalPath(){
        return localPath;
    }
    
    public String getRemotePath(){
        return remotePath;
    }
    
    
}
