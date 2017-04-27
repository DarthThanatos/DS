package sr.grpc.gen;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: calculator.proto")
public final class CalculatorGrpc {

  private CalculatorGrpc() {}

  public static final String SERVICE_NAME = "calculator.Calculator";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<sr.grpc.gen.ArithmeticOpArguments,
      sr.grpc.gen.ArithmeticOpResult> METHOD_ADD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "calculator.Calculator", "Add"),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpArguments.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<sr.grpc.gen.ArithmeticOpArguments,
      sr.grpc.gen.ArithmeticOpResult> METHOD_SUBTRACT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "calculator.Calculator", "Subtract"),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpArguments.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<sr.grpc.gen.ArithmeticOpArguments,
      sr.grpc.gen.ArithmeticOpResult> METHOD_MULTIPLY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "calculator.Calculator", "Multiply"),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpArguments.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ArithmeticOpResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<sr.grpc.gen.ThreeArgs,
      sr.grpc.gen.ThreeNumsRes> METHOD_MULTIPLY_THREE_NUMS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "calculator.Calculator", "MultiplyThreeNums"),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ThreeArgs.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sr.grpc.gen.ThreeNumsRes.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorStub newStub(io.grpc.Channel channel) {
    return new CalculatorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CalculatorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CalculatorFutureStub(channel);
  }

  /**
   */
  public static abstract class CalculatorImplBase implements io.grpc.BindableService {

    /**
     */
    public void add(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD, responseObserver);
    }

    /**
     */
    public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SUBTRACT, responseObserver);
    }

    /**
     */
    public void multiply(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MULTIPLY, responseObserver);
    }

    /**
     */
    public void multiplyThreeNums(sr.grpc.gen.ThreeArgs request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ThreeNumsRes> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MULTIPLY_THREE_NUMS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD,
            asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.ArithmeticOpArguments,
                sr.grpc.gen.ArithmeticOpResult>(
                  this, METHODID_ADD)))
          .addMethod(
            METHOD_SUBTRACT,
            asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.ArithmeticOpArguments,
                sr.grpc.gen.ArithmeticOpResult>(
                  this, METHODID_SUBTRACT)))
          .addMethod(
            METHOD_MULTIPLY,
            asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.ArithmeticOpArguments,
                sr.grpc.gen.ArithmeticOpResult>(
                  this, METHODID_MULTIPLY)))
          .addMethod(
            METHOD_MULTIPLY_THREE_NUMS,
            asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.ThreeArgs,
                sr.grpc.gen.ThreeNumsRes>(
                  this, METHODID_MULTIPLY_THREE_NUMS)))
          .build();
    }
  }

  /**
   */
  public static final class CalculatorStub extends io.grpc.stub.AbstractStub<CalculatorStub> {
    private CalculatorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorStub(channel, callOptions);
    }

    /**
     */
    public void add(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SUBTRACT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void multiply(sr.grpc.gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void multiplyThreeNums(sr.grpc.gen.ThreeArgs request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.ThreeNumsRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY_THREE_NUMS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CalculatorBlockingStub extends io.grpc.stub.AbstractStub<CalculatorBlockingStub> {
    private CalculatorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.ArithmeticOpResult add(sr.grpc.gen.ArithmeticOpArguments request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD, getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.ArithmeticOpResult subtract(sr.grpc.gen.ArithmeticOpArguments request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SUBTRACT, getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.ArithmeticOpResult multiply(sr.grpc.gen.ArithmeticOpArguments request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MULTIPLY, getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.ThreeNumsRes multiplyThreeNums(sr.grpc.gen.ThreeArgs request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MULTIPLY_THREE_NUMS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CalculatorFutureStub extends io.grpc.stub.AbstractStub<CalculatorFutureStub> {
    private CalculatorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CalculatorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CalculatorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.ArithmeticOpResult> add(
        sr.grpc.gen.ArithmeticOpArguments request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.ArithmeticOpResult> subtract(
        sr.grpc.gen.ArithmeticOpArguments request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SUBTRACT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.ArithmeticOpResult> multiply(
        sr.grpc.gen.ArithmeticOpArguments request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.ThreeNumsRes> multiplyThreeNums(
        sr.grpc.gen.ThreeArgs request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY_THREE_NUMS, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_SUBTRACT = 1;
  private static final int METHODID_MULTIPLY = 2;
  private static final int METHODID_MULTIPLY_THREE_NUMS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CalculatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CalculatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((sr.grpc.gen.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_SUBTRACT:
          serviceImpl.subtract((sr.grpc.gen.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_MULTIPLY:
          serviceImpl.multiply((sr.grpc.gen.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_MULTIPLY_THREE_NUMS:
          serviceImpl.multiplyThreeNums((sr.grpc.gen.ThreeArgs) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.ThreeNumsRes>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class CalculatorDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.CalculatorProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CalculatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorDescriptorSupplier())
              .addMethod(METHOD_ADD)
              .addMethod(METHOD_SUBTRACT)
              .addMethod(METHOD_MULTIPLY)
              .addMethod(METHOD_MULTIPLY_THREE_NUMS)
              .build();
        }
      }
    }
    return result;
  }
}
