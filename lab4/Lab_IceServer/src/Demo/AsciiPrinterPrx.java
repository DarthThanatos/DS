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

public interface AsciiPrinterPrx extends PrinterPrx
{
    public String prettyPrint(String s)
        throws OutOfInkException;

    public String prettyPrint(String s, java.util.Map<String, String> __ctx)
        throws OutOfInkException;

    public Ice.AsyncResult begin_prettyPrint(String s);

    public Ice.AsyncResult begin_prettyPrint(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_prettyPrint(String s, Ice.Callback __cb);

    public Ice.AsyncResult begin_prettyPrint(String s, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_prettyPrint(String s, Callback_AsciiPrinter_prettyPrint __cb);

    public Ice.AsyncResult begin_prettyPrint(String s, java.util.Map<String, String> __ctx, Callback_AsciiPrinter_prettyPrint __cb);

    public Ice.AsyncResult begin_prettyPrint(String s, 
                                             IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_prettyPrint(String s, 
                                             IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                             IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_prettyPrint(String s, 
                                             java.util.Map<String, String> __ctx, 
                                             IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_prettyPrint(String s, 
                                             java.util.Map<String, String> __ctx, 
                                             IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                             IceInternal.Functional_BoolCallback __sentCb);

    public String end_prettyPrint(Ice.AsyncResult __result)
        throws OutOfInkException;
}
