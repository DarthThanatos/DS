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

public abstract class _CameraDisp extends Ice.ObjectImpl implements Camera
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Demo::Camera",
        "::Demo::Device",
        "::Ice::Object"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[0];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String ice_staticId()
    {
        return __ids[0];
    }

    public final void turnDown(float angle)
        throws RotationOutOfRangeException
    {
        turnDown(angle, null);
    }

    public final void turnLeft(float angle)
        throws RotationOutOfRangeException
    {
        turnLeft(angle, null);
    }

    public final void turnRight(float angle)
        throws RotationOutOfRangeException
    {
        turnRight(angle, null);
    }

    public final void turnUp(float angle)
        throws RotationOutOfRangeException
    {
        turnUp(angle, null);
    }

    public final void feedBattery()
    {
        feedBattery(null);
    }

    public final String getName()
    {
        return getName(null);
    }

    public final String getState()
    {
        return getState(null);
    }

    public final String[] listActions()
    {
        return listActions(null);
    }

    public static Ice.DispatchStatus ___turnLeft(Camera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float angle;
        angle = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.turnLeft(angle, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(RotationOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___turnRight(Camera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float angle;
        angle = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.turnRight(angle, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(RotationOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___turnUp(Camera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float angle;
        angle = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.turnUp(angle, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(RotationOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___turnDown(Camera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float angle;
        angle = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.turnDown(angle, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(RotationOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    private final static String[] __all =
    {
        "feedBattery",
        "getName",
        "getState",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "listActions",
        "turnDown",
        "turnLeft",
        "turnRight",
        "turnUp"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _DeviceDisp.___feedBattery(this, in, __current);
            }
            case 1:
            {
                return _DeviceDisp.___getName(this, in, __current);
            }
            case 2:
            {
                return _DeviceDisp.___getState(this, in, __current);
            }
            case 3:
            {
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
            case 7:
            {
                return _DeviceDisp.___listActions(this, in, __current);
            }
            case 8:
            {
                return ___turnDown(this, in, __current);
            }
            case 9:
            {
                return ___turnLeft(this, in, __current);
            }
            case 10:
            {
                return ___turnRight(this, in, __current);
            }
            case 11:
            {
                return ___turnUp(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
