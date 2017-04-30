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
    public BodyTemperatureStatePrx measureBodyTemperature(temperature temperatureObj);

    public BodyTemperatureStatePrx measureBodyTemperature(temperature temperatureObj, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, Ice.Callback __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, Callback_BodyTemperatureSensor_measureBodyTemperature __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, java.util.Map<String, String> __ctx, Callback_BodyTemperatureSensor_measureBodyTemperature __cb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, 
                                                        IceInternal.Functional_GenericCallback1<BodyTemperatureStatePrx> __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, 
                                                        IceInternal.Functional_GenericCallback1<BodyTemperatureStatePrx> __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, 
                                                        java.util.Map<String, String> __ctx, 
                                                        IceInternal.Functional_GenericCallback1<BodyTemperatureStatePrx> __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_measureBodyTemperature(temperature temperatureObj, 
                                                        java.util.Map<String, String> __ctx, 
                                                        IceInternal.Functional_GenericCallback1<BodyTemperatureStatePrx> __responseCb, 
                                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                        IceInternal.Functional_BoolCallback __sentCb);

    public BodyTemperatureStatePrx end_measureBodyTemperature(Ice.AsyncResult __result);
}