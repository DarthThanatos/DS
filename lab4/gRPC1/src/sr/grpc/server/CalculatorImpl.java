package sr.grpc.server;

import sr.grpc.gen.ArithmeticOpResult;
import sr.grpc.gen.CalculatorGrpc.CalculatorImplBase;
import sr.grpc.gen.ThreeNumsRes;

public class CalculatorImpl extends CalculatorImplBase 
{
	@Override
	public void add(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("addRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() + request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		try { Thread.sleep(1000); } catch(java.lang.InterruptedException ex) { }
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("subtractRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() - request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void multiplyThreeNums(sr.grpc.gen.ThreeArgs request, io.grpc.stub.StreamObserver<sr.grpc.gen.ThreeNumsRes> responseObserver) {
		System.out.println("Multiply request: " + request.getArg1() + " " + request.getArg2() + " " + request.getArg3());
		int resVal = request.getArg1() * request.getArg2() * request.getArg3();
		ThreeNumsRes res = ThreeNumsRes.newBuilder().setRes(resVal).build();
		responseObserver.onNext(res);
		responseObserver.onCompleted();};
	
	
	@Override
	public void multiply(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("multiplyRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() * request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

}
