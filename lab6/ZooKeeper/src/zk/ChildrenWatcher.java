package zk;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ChildrenWatcher implements Watcher {
	Process p = null;
	CountDownLatch connectedSignal;
	String path;
	ZooKeeper zk;
	List<String> prev_children_names;
	
	public ChildrenWatcher(CountDownLatch connectedSignal, String path, ZooKeeper zk){
		this.path = path;
		this.connectedSignal = connectedSignal;
		this.zk = zk;
	  	System.out.println("Starting to observe " + path + " and its children");
		try{
		  	prev_children_names = zk.getChildren(path, false);
		    startObservingSubTree(path);
		}catch(Exception e){
			
		}
	}
	 
	private ChildrenWatcher(CountDownLatch connectedSignal, String path, ZooKeeper zk, String msg){
		this.path = path;
		this.connectedSignal = connectedSignal;
		this.zk = zk;
		System.out.println(msg);
		try{
			prev_children_names = zk.getChildren(path, false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

   private int countDescendants(String parent) throws KeeperException, InterruptedException{
	   String path = parent;
	   int lvl_counter = 0;
       List <String> children = zk.getChildren(path, false);
       for(int i = 0; i < children.size(); i++){
       		lvl_counter += countDescendants(path + "/" + children.get(i));
       }
       return lvl_counter + children.size();
   }

	
	public void startObservingSubTree(String path) throws KeeperException, InterruptedException{
		List <String> children = zk.getChildren(path, false);
        for(int i = 0; i < children.size(); i++){
        	String childPath = path + "/" + children.get(i);
        	zk.getChildren(childPath, new ChildrenWatcher(connectedSignal, childPath, zk, "Adding observer in subtree"));
      		startObservingSubTree(childPath);
        }	
	}
	
    public void process(WatchedEvent we) {
		System.out.println("Got event: " + we.getType() + " " + we.getPath() + " state: " + we.getState());
       if (we.getType() == Event.EventType.None) {
          switch(we.getState()) {
             case Expired:
             connectedSignal.countDown();
             break;
          }
       } 
       else if(we.getType() == Event.EventType.NodeChildrenChanged){
           try {
          	 	System.out.println("Descendants amount: " + countDescendants(ZookeeperMain.ROOT));
          	 	List<String> current_children_names = zk.getChildren(path, false);
          	 	for (String child_name : current_children_names){
          	 		if(!prev_children_names.contains(child_name)){ 
          	 			//if there is a name in the current names list that was not present before, let's add a new watcher for it
          	 			String spawned_path = path + "/" + child_name;
              			zk.getChildren(spawned_path, new ChildrenWatcher(connectedSignal, spawned_path, zk, "Setting watcher for " + spawned_path));
          	 		}
          	 	}
          	 	zk.getChildren(path,  new ChildrenWatcher(connectedSignal, path, zk, "Setting watcher for " + path));
             } catch(Exception ex) {
                System.out.println(ex.getMessage());
             }
       }
    }
 }
