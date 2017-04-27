package sr.thrift.server;

import org.apache.thrift.TException;

// Generated code
import sr.rpc.thrift.*;

public class CalculatorHandler implements Calculator.Iface {

	int id;

	public CalculatorHandler(int id) {
		this.id = id;
	}

	@Override
	public int add(int n1, int n2) {
		System.out.println("C#" + id + " add(" + n1 + "," + n2 + ")");
		if(n1 > 1000 || n2 > 1000) { 
			try { Thread.sleep(6000); } catch(java.lang.InterruptedException ex) { }
			System.out.println("DONE");
		}
		return n1 + n2;
	}

	@Override
	public int subtract(int num1, int num2) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multiply(int num1, int num2) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multiply3Nums(int num1, int num2, int num3) throws TException {
		System.out.println("C# Multiplying " + num1 + " and " + num2 + " and " + num3);
		return num1 * num2 * num3;
	}

}

