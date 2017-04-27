package sr.ice.client;
// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************


import Demo.*;
import Ice.AsyncResult;

public class Client 
{
	public static void main(String[] args) 
	{
		int status = 0;
		Ice.Communicator communicator = null;

		try {
			// 1. Inicjalizacja ICE
			communicator = Ice.Util.initialize(args);

			// 2. Uzyskanie referencji obiektu na podstawie linii w pliku konfiguracyjnym
			//Ice.ObjectPrx base = communicator.propertyToProxy("Calc1.Proxy");
			// 2. To samo co powy¿ej, ale mniej ³adnie
			Ice.ObjectPrx base = communicator.stringToProxy("calc/calc11:tcp -h localhost -p 10000:udp -h localhost -p 10000");
			Ice.ObjectPrx parallel = communicator.stringToProxy("calc/calc88:tcp -h localhost -p 10000:udp -h localhost -p 10000");
			
			// 3. Rzutowanie, zawê¿anie
			CalcPrx obj = CalcPrxHelper.checkedCast(base);
			CalcPrx parallelObj = CalcPrxHelper.checkedCast(parallel);
			if (obj == null) throw new Error("Invalid proxy");
			
	        CalcPrx objOneway = (CalcPrx)obj.ice_oneway();
	        CalcPrx objBatchOneway = (CalcPrx)obj.ice_batchOneway();
	        CalcPrx objDatagram = (CalcPrx)obj.ice_datagram();
	        CalcPrx objBatchDatagram = (CalcPrx)obj.ice_batchDatagram();
	        
			// 4. Wywolanie zdalnych operacji
			
			String line = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			AsyncResult ar = null;
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
					if (line.equals("add"))
					{
						parallelObj.begin_add(20, 8);
						parallelObj.begin_add(7000, 8, new Callback_Calc_addI());
						long r = obj.add(30, 8);
						System.out.println("RESULT = " + r);
					}
					if (line.equals("subtract"))
					{
						long r = obj.subtract(7, 8);
						System.out.println("RESULT = " + r);
					}
					else if(line.equals("multiply")){
						long r = obj.multiply(2, 2);
						System.out.println("Result: " + r);
					}
	                else if(line.equals("o"))
	                {
	                    objOneway.add(7,8);
	                }
	                else if(line.equals("O"))
	                {
	                    objBatchOneway.add(7, 8);
	                }
	                else if(line.equals("d"))
	                {
	                    objDatagram.add(7, 8);
	                }
	                else if(line.equals("D"))
	                {
	                    objBatchDatagram.add(7, 8);
	                }
					else if (line.equals("x"))
					{
						// Nothing to do
					}
				}
				catch (java.io.IOException ex)
				{
					System.err.println(ex);
				}
			}
			while (!line.equals("x"));


		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) {
			// Clean up
			//
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}