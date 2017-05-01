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

public interface ReporterPrx extends Ice.ObjectPrx
{
    public void report(String msg);

    public void report(String msg, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_report(String msg);

    public Ice.AsyncResult begin_report(String msg, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_report(String msg, Ice.Callback __cb);

    public Ice.AsyncResult begin_report(String msg, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_report(String msg, Callback_Reporter_report __cb);

    public Ice.AsyncResult begin_report(String msg, java.util.Map<String, String> __ctx, Callback_Reporter_report __cb);

    public Ice.AsyncResult begin_report(String msg, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_report(String msg, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_report(String msg, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_report(String msg, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_report(Ice.AsyncResult __result);
}
