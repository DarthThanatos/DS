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

/**
 * Provides type-specific helper functions.
 **/
public final class PrinterStatePrxHelper extends Ice.ObjectPrxHelperBase implements PrinterStatePrx
{
    private static final String __formatToString_name = "formatToString";

    public String formatToString()
    {
        return formatToString(null, false);
    }

    public String formatToString(java.util.Map<String, String> __ctx)
    {
        return formatToString(__ctx, true);
    }

    private String formatToString(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__formatToString_name);
        return end_formatToString(begin_formatToString(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_formatToString()
    {
        return begin_formatToString(null, false, false, null);
    }

    public Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx)
    {
        return begin_formatToString(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_formatToString(Ice.Callback __cb)
    {
        return begin_formatToString(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_formatToString(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_formatToString(Callback_DeviceState_formatToString __cb)
    {
        return begin_formatToString(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, Callback_DeviceState_formatToString __cb)
    {
        return begin_formatToString(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_formatToString(IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_formatToString(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_formatToString(IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_formatToString(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, 
                                                IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_formatToString(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, 
                                                IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_formatToString(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, 
                                                 boolean __explicitCtx, 
                                                 boolean __synchronous, 
                                                 IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_formatToString(__ctx, __explicitCtx, __synchronous, 
                                    new IceInternal.Functional_TwowayCallbackArg1<String>(__responseCb, __exceptionCb, __sentCb)
                                        {
                                            public final void __completed(Ice.AsyncResult __result)
                                            {
                                                PrinterStatePrxHelper.__formatToString_completed(this, __result);
                                            }
                                        });
    }

    private Ice.AsyncResult begin_formatToString(java.util.Map<String, String> __ctx, 
                                                 boolean __explicitCtx, 
                                                 boolean __synchronous, 
                                                 IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__formatToString_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__formatToString_name, __cb);
        try
        {
            __result.prepare(__formatToString_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public String end_formatToString(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __formatToString_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            String __ret;
            __ret = __is.readString();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __formatToString_completed(Ice.TwowayCallbackArg1<String> __cb, Ice.AsyncResult __result)
    {
        Demo.DeviceStatePrx __proxy = (Demo.DeviceStatePrx)__result.getProxy();
        String __ret = null;
        try
        {
            __ret = __proxy.end_formatToString(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static PrinterStatePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static PrinterStatePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static PrinterStatePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static PrinterStatePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static PrinterStatePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static PrinterStatePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, PrinterStatePrx.class, PrinterStatePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Demo::DeviceState",
        "::Demo::PrinterState",
        "::Ice::Object"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, PrinterStatePrx v)
    {
        __os.writeProxy(v);
    }

    public static PrinterStatePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            PrinterStatePrxHelper result = new PrinterStatePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
