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
// Generated from file `calculator.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Demo;

public enum operation implements java.io.Serializable
{
    
    MIN(0),
    
    MAX(1),
    
    AVG(2);

    public int
    value()
    {
        return __value;
    }

    public static operation
    valueOf(int __v)
    {
        switch(__v)
        {
        case 0:
            return MIN;
        case 1:
            return MAX;
        case 2:
            return AVG;
        }
        return null;
    }

    private
    operation(int __v)
    {
        __value = __v;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeEnum(value(), 2);
    }

    public static void
    __write(IceInternal.BasicStream __os, operation __v)
    {
        if(__v == null)
        {
            __os.writeEnum(Demo.operation.MIN.value(), 2);
        }
        else
        {
            __os.writeEnum(__v.value(), 2);
        }
    }

    public static operation
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readEnum(2);
        return __validate(__v);
    }

    private static operation
    __validate(int __v)
    {
        final operation __e = valueOf(__v);
        if(__e == null)
        {
            throw new Ice.MarshalException("enumerator value " + __v + " is out of range");
        }
        return __e;
    }

    private final int __value;
}
