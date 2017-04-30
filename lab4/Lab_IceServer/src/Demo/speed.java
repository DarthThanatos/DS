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

public enum speed implements java.io.Serializable
{
    
    SLOW(0),
    
    MEDIUM(1),
    
    QUICK(2);

    public int
    value()
    {
        return __value;
    }

    public static speed
    valueOf(int __v)
    {
        switch(__v)
        {
        case 0:
            return SLOW;
        case 1:
            return MEDIUM;
        case 2:
            return QUICK;
        }
        return null;
    }

    private
    speed(int __v)
    {
        __value = __v;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeEnum(value(), 2);
    }

    public static void
    __write(IceInternal.BasicStream __os, speed __v)
    {
        if(__v == null)
        {
            __os.writeEnum(Demo.speed.SLOW.value(), 2);
        }
        else
        {
            __os.writeEnum(__v.value(), 2);
        }
    }

    public static speed
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readEnum(2);
        return __validate(__v);
    }

    private static speed
    __validate(int __v)
    {
        final speed __e = valueOf(__v);
        if(__e == null)
        {
            throw new Ice.MarshalException("enumerator value " + __v + " is out of range");
        }
        return __e;
    }

    private final int __value;
}