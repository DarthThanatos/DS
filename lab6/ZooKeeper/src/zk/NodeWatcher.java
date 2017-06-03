package zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.Watcher;

public class NodeWatcher implements Watcher{
	Process p = null;
	CountDownLatch connectedSignal;
	String cmd;
	
	public NodeWatcher(CountDownLatch connectedSignal, String cmd){
		this.cmd = cmd;
		this.connectedSignal = connectedSignal;
	}
	
    public void process(WatchedEvent we) {
		System.out.println("Got event: " + we.getType());
       if (we.getType() == Event.EventType.None) {
          switch(we.getState()) {
             case Expired:
             connectedSignal.countDown();
             break;
          }
					
       } else if(we.getType() == Event.EventType.NodeCreated) {
			
           try {
        	   System.out.println("Creating window");
        	  p  = Runtime.getRuntime().exec(cmd);
           } catch(Exception ex) {
              System.out.println(ex.getMessage());
           }
        }
        else if(we.getType() == Event.EventType.NodeDeleted) {
           try {
        	   if(p!= null){
        		   System.out.println("Deleting window");
        		   p.destroy();
        	   }
   		   	  
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         }
    }
}

