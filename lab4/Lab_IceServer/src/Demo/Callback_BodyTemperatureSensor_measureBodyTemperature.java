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

public abstract class Callback_BodyTemperatureSensor_measureBodyTemperature
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<Demo.BodyTemperatureStatePrx>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        BodyTemperatureSensorPrxHelper.__measureBodyTemperature_completed(this, __result);
    }
}
