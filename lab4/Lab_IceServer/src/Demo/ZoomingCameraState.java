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

public abstract class ZoomingCameraState extends CameraState
                                         implements _ZoomingCameraStateOperations,
                                                    _ZoomingCameraStateOperationsNC
{
    public ZoomingCameraState()
    {
        super();
    }

    public ZoomingCameraState(float batteryLevel, String lastUserName, long operationTime, float horizontalAngle, float verticalAngle, int zoomLvl)
    {
        super(batteryLevel, lastUserName, operationTime, horizontalAngle, verticalAngle);
        this.zoomLvl = zoomLvl;
    }

    public static final String[] __ids =
    {
        "::Demo::CameraState",
        "::Demo::DeviceState",
        "::Demo::ZoomingCameraState",
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
        return __ids[2];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[2];
    }

    public static String ice_staticId()
    {
        return __ids[2];
    }

    private final static String[] __all =
    {
        "formatToString",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
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
                return DeviceState.___formatToString(this, in, __current);
            }
            case 1:
            {
                return ___ice_id(this, in, __current);
            }
            case 2:
            {
                return ___ice_ids(this, in, __current);
            }
            case 3:
            {
                return ___ice_isA(this, in, __current);
            }
            case 4:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, false);
        __os.writeInt(zoomLvl);
        __os.endWriteSlice();
        super.__writeImpl(__os);
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        zoomLvl = __is.readInt();
        __is.endReadSlice();
        super.__readImpl(__is);
    }

    public int zoomLvl;

    public ZoomingCameraState
    clone()
    {
        return (ZoomingCameraState)super.clone();
    }

    public static final long serialVersionUID = 1509424064L;
}
