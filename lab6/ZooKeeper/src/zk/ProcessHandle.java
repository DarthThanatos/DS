package zk;

import java.io.IOException;

public class ProcessHandle {
	
	private Process p = null;
	
	public Process getProcess(){
		return p;
	}
	
	public void createProcess(String cmd) throws IOException{
		p  = Runtime.getRuntime().exec(cmd);
	}
	
	public void endProcess(){
		if (p != null) {
			p.destroy();
			p = null;
		}
	}
	
}
