package sr.thrift.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;

import sr.rpc.thrift.AdvancedCalculator;
import sr.rpc.thrift.Calculator;
import sr.rpc.thrift.Calculator.AsyncClient.add_call;
import sr.rpc.thrift.OperationType;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

//https://www.javacodegeeks.com/2012/03/apache-thrift-quickstart-tutorial.html

public class ThriftClient 
{
	public static void main(String [] args) throws Exception
	{

		String opt = "simple";

		TProtocol protocol = null;
		final TSocket socket0 = new TSocket("localhost", 9090);
		final TSocket socket1 = new TSocket("localhost", 9091);
		final TSocket socket2 = new TSocket("localhost", 9092);
		final TNonblockingSocket socket3= new TNonblockingSocket("localhost", 9093);
		TTransport transport = null;
		
		Calculator.Client synCalc1 = null;
		Calculator.Client synCalc2 = null;
		Calculator.Client synCalc3 = null;
		Calculator.AsyncClient asynCalc1 = null; 
		AdvancedCalculator.Client synAdvCalc1 = null;

		System.out.println("Running client in the " + opt + " mode");
		try {
			if (opt.contains("simple")) 
			{
				//socket.setTimeout(3000); //przerywa wywo³anie po stronie klienta
				
				
				transport = socket0;
				//protocol = new TBinaryProtocol(transport);
				protocol = new TJSONProtocol(transport);
				//protocol = new TCompactProtocol(transport);
				
				
				synCalc1 = new Calculator.Client(protocol);
				synAdvCalc1 = new AdvancedCalculator.Client(protocol); //wywo³anie na tym samym obiekcie
				/*
				TNonblockingTransport nonBlockTransport = new TNonblockingSocket("localhost", 9091);
				TProtocolFactory nonBlockProtocolFactory = new TBinaryProtocol.Factory();
				asynCalc1 = new Calculator.AsyncClient(nonBlockProtocolFactory, new TAsyncClientManager(), nonBlockTransport);
				*/
			}
			else if(opt.contains("multiplex"))
			{
				transport = socket1; //new TSocket("localhost", 9091);
				
				protocol = new TBinaryProtocol(transport, true, true);
				//protocol = new TJSONProtocol(transport);

				synCalc1 = new Calculator.Client(new TMultiplexedProtocol(protocol, "S1"));
				synCalc2 = new Calculator.Client(new TMultiplexedProtocol(protocol, "S2"));
				synAdvCalc1 = new AdvancedCalculator.Client(new TMultiplexedProtocol(protocol, "A1"));
			}
			else if (opt.contains("non-block")) {
				//wymaga non-block po stronie serwera
				transport = new TFramedTransport(socket3);//new TFramedTransport(new TSocket("localhost", 9093));
				protocol = new TBinaryProtocol(transport);
			}
			else if(opt.contains("asyn")) {
				TNonblockingTransport nbtransport = null;
				TAsyncClientManager clientManager = null;
				try {
					nbtransport = socket3;//new TNonblockingSocket("localhost", 9093);
					clientManager = new TAsyncClientManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
				TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
				asynCalc1 = new Calculator.AsyncClient(protocolFactory, clientManager, nbtransport);
			}
			else if (opt.contains("multi")) { //TODO
				transport = new TSocket("localhost", 9092);
			}
			else 
			{
				System.out.println("Unknown or unsupported transport - exiting");
				return;
			}

			if(transport != null) transport.open();

			
			String line = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in)); 
			do
			{
				try
				{
					System.out.print("==> ");
					System.out.flush();
					line = in.readLine();
					if (line == null)
					{
						break;
					}
					else if(line.equals("avg"))
					{
						double res = synAdvCalc1.op(OperationType.AVG, new HashSet<Double>(Arrays.asList(4.0, 5.0)));
						System.out.println("op(AVG, {4.0,5.0)) returned " + res);
					}
					else if (line.equals("multiply3Nums")){
						double res = synCalc1.multiply3Nums(4, 3, 2);
						System.out.println("Multplied 4 3 2 = " + res);
					}
					else if(line.equals("add1a"))
					{
						int arg1 = 44;
						int arg2 = 55;
						int res = synCalc1.add(arg1, arg2);
						System.out.println("ping(" + arg1 + "," + arg2 + ") returned " + res);
					}
					else if(line.equals("add1b"))
					{
						int arg1 = 4400;
						int arg2 = 5500;
						int res = synCalc1.add(arg1, arg2);
						System.out.println("ping(" + arg1 + "," + arg2 + ") returned " + res);
					}
					else if(line.equals("add2"))
					{
						int arg1 = 44;
						int arg2 = 55;
						int res = synCalc2.add(arg1, arg2);
						System.out.println("ping(" + arg1 + "," + arg2 + ") returned " + res);
					}
					else if(line.equals("add3"))
					{
						int arg1 = 44;
						int arg2 = 55;
						int res = synAdvCalc1.add(arg1, arg2);
						System.out.println("ping(" + arg1 + "," + arg2 + ") returned " + res);
					}
					else if(line.equals("asyn-add"))
					{
						asynCalc1.add(4400, 44, new AddMethodCallback());//.method_call(parameters, new Callback());
						System.out.println("returned immediately, waiting for callback");
					}
					else if(line.equalsIgnoreCase("parallel-simple-add")){
						Thread thread1 = new Thread(){

							@Override
							public void run() {
								try {
									TTransport transport = socket0;
									TProtocol protocol = new TJSONProtocol(transport);
									AdvancedCalculator.Client synAdvCalc = new AdvancedCalculator.Client(protocol); //wywo³anie na tym samym obiekcie
									int res = synAdvCalc.add(4000, 5000);
									System.out.println("ping(" + 4000 + "," + 5000 + ") returned " + res);	
								} catch (TException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
							}
							
						};
						
						Thread thread2 = new Thread(){

							@Override
							public void run() {
								try {
									TTransport transport = socket0;
									TProtocol protocol = new TJSONProtocol(transport);
									AdvancedCalculator.Client synAdvCalc1 = new AdvancedCalculator.Client(protocol); //wywo³anie na tym samym obiekcie
									int res = synAdvCalc1.add(4001, 5001);
									System.out.println("ping(" + 4001 + "," + 5001 + ") returned " + res);	
								} catch (TException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
							}
							
						};
						thread1.start();
						thread2.start();
					}
					else if (line.equals("parallel-multiplex-add")){
						Thread thread1 = new Thread(){

							@Override
							public void run() {
								try {
									TTransport transport = socket3;
									TProtocol protocol = new TBinaryProtocol(transport, true, true);
									Calculator.Client synCalc1 = new Calculator.Client(new TMultiplexedProtocol(protocol, "S1"));
									transport = new TSocket("localhost", 9091);
									
									int res = synCalc1.add(4000, 5000);
									System.out.println("ping(" + 4000 + "," + 5000 + ") returned " + res);	
								} catch (TException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
							}
							
						};
						
						Thread thread2 = new Thread(){

							@Override
							public void run() {
								try {
									TTransport transport = socket3;
									TProtocol protocol = new TBinaryProtocol(transport, true, true);
									Calculator.Client synCalc = new Calculator.Client(new TMultiplexedProtocol(protocol, "S2"));
									transport = new TSocket("localhost", 9091);
									
									int res = synCalc.add(4000, 5000);
									System.out.println("ping(" + 4000 + "," + 5000 + ") returned " + res);	
								} catch (TException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
							}
							
						};
						thread1.start();
						thread2.start();
						
					}
					else if (line.equals("x"))
					{
						// Nothing to do
					}
				}
				catch (Exception ex)
				{
					System.err.println(ex);
					ex.printStackTrace();
				}
			}
			while (!line.equals("x"));
			
			transport.close();
			
		} catch (TException ex) {
			ex.printStackTrace();
		} 
	}
}

class AddMethodCallback implements AsyncMethodCallback<Integer> 
{

	@Override
	public void onError(Exception e) {
		System.out.println("Error : ");
		e.printStackTrace();
	}

	@Override
	public void onComplete(Integer arg0) {
		System.out.println("Result: " + arg0.intValue());
	}
}
