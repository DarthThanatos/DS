package pl.edu.agh.dsrg.sr.protos;

public class Main {
    
    public static void main(String[] args) throws Exception {
    	try{
    		String userName = args[0]; 
    		String multicastIp = args[1];
    		ClientPanel cp = new ClientPanel();
    		//cp.visualize();
    		Coordinator coord = new Coordinator(cp);
    		new Thread(coord).start();
    		new Thread(new JChannelClient(userName, multicastIp, cp,coord)).start();
    	}catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Usage: java JChannelClient username ip");
    	}
    }
}
