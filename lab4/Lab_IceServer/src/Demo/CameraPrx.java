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

public interface CameraPrx extends DevicePrx
{
    public void turnLeft(float angle)
        throws RotationOutOfRangeException;

    public void turnLeft(float angle, java.util.Map<String, String> __ctx)
        throws RotationOutOfRangeException;

    public Ice.AsyncResult begin_turnLeft(float angle);

    public Ice.AsyncResult begin_turnLeft(float angle, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnLeft(float angle, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnLeft(float angle, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnLeft(float angle, Callback_Camera_turnLeft __cb);

    public Ice.AsyncResult begin_turnLeft(float angle, java.util.Map<String, String> __ctx, Callback_Camera_turnLeft __cb);

    public Ice.AsyncResult begin_turnLeft(float angle, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnLeft(float angle, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnLeft(float angle, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnLeft(float angle, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnLeft(Ice.AsyncResult __result)
        throws RotationOutOfRangeException;

    public void turnRight(float angle)
        throws RotationOutOfRangeException;

    public void turnRight(float angle, java.util.Map<String, String> __ctx)
        throws RotationOutOfRangeException;

    public Ice.AsyncResult begin_turnRight(float angle);

    public Ice.AsyncResult begin_turnRight(float angle, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnRight(float angle, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnRight(float angle, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnRight(float angle, Callback_Camera_turnRight __cb);

    public Ice.AsyncResult begin_turnRight(float angle, java.util.Map<String, String> __ctx, Callback_Camera_turnRight __cb);

    public Ice.AsyncResult begin_turnRight(float angle, 
                                           IceInternal.Functional_VoidCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnRight(float angle, 
                                           IceInternal.Functional_VoidCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnRight(float angle, 
                                           java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_VoidCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnRight(float angle, 
                                           java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_VoidCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnRight(Ice.AsyncResult __result)
        throws RotationOutOfRangeException;

    public void turnUp(float angle)
        throws RotationOutOfRangeException;

    public void turnUp(float angle, java.util.Map<String, String> __ctx)
        throws RotationOutOfRangeException;

    public Ice.AsyncResult begin_turnUp(float angle);

    public Ice.AsyncResult begin_turnUp(float angle, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnUp(float angle, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnUp(float angle, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnUp(float angle, Callback_Camera_turnUp __cb);

    public Ice.AsyncResult begin_turnUp(float angle, java.util.Map<String, String> __ctx, Callback_Camera_turnUp __cb);

    public Ice.AsyncResult begin_turnUp(float angle, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnUp(float angle, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnUp(float angle, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnUp(float angle, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnUp(Ice.AsyncResult __result)
        throws RotationOutOfRangeException;

    public void turnDown(float angle)
        throws RotationOutOfRangeException;

    public void turnDown(float angle, java.util.Map<String, String> __ctx)
        throws RotationOutOfRangeException;

    public Ice.AsyncResult begin_turnDown(float angle);

    public Ice.AsyncResult begin_turnDown(float angle, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnDown(float angle, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnDown(float angle, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnDown(float angle, Callback_Camera_turnDown __cb);

    public Ice.AsyncResult begin_turnDown(float angle, java.util.Map<String, String> __ctx, Callback_Camera_turnDown __cb);

    public Ice.AsyncResult begin_turnDown(float angle, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnDown(float angle, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnDown(float angle, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnDown(float angle, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnDown(Ice.AsyncResult __result)
        throws RotationOutOfRangeException;
}
