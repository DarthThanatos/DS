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

public class DeviceState extends Ice.ObjectImpl
{
    public DeviceState()
    {
        lastUserName = "";
    }

    public DeviceState(float batteryLevel, String lastUserName, long operationTime)
    {
        this.batteryLevel = batteryLevel;
        this.lastUserName = lastUserName;
        this.operationTime = operationTime;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new DeviceState();
        }

        public void destroy()
        {
        }
    }
    private static Ice.ObjectFactory _factory = new __F();

    public static Ice.ObjectFactory
    ice_factory()
    {
        return _factory;
    }

    public static final String[] __ids =
    {
        "::Demo::DeviceState",
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

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.writeFloat(batteryLevel);
        __os.writeString(lastUserName);
        __os.writeLong(operationTime);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        batteryLevel = __is.readFloat();
        lastUserName = __is.readString();
        operationTime = __is.readLong();
        __is.endReadSlice();
    }

    public float batteryLevel;

    public String lastUserName;

    public long operationTime;

    public DeviceState
    clone()
    {
        return (DeviceState)super.clone();
    }

    public static final long serialVersionUID = -183288483L;
}
