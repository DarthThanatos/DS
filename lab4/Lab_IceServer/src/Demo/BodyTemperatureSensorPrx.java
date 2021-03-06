// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `laboratory.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Demo;

public interface BodyTemperatureSensorPrx extends SensorPrx
{
    public void measureBodyTemperature(float temperature)
        throws BrokenDiodeException;

    public void measureBodyTemperature(float temperature, java.util.Map<String, String> __ctx)
        throws BrokenDiodeException;

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, Ice.Callback __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, Callback_BodyTemperatureSensor_measureBodyTemperature __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, java.util.Map<String, String> __ctx, Callback_BodyTemperatureSensor_measureBodyTemperature __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, 
                                                        IceInternal.Functional_VoidCallback __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, 
                                                        IceInternal.Functional_VoidCallback __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, 
                                                        java.util.Map<String, String> __ctx, 
                                                        IceInternal.Functional_VoidCallback __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_measureBodyTemperature(float temperature, 
                                                        java.util.Map<String, String> __ctx, 
                                                        IceInternal.Functional_VoidCallback __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_measureBodyTemperature(Ice.AsyncResult __result)
        throws BrokenDiodeException;
}
