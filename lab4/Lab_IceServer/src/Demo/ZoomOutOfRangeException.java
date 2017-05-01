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

public class ZoomOutOfRangeException extends Ice.UserException
{
    public ZoomOutOfRangeException()
    {
        reason = "";
    }

    public ZoomOutOfRangeException(Throwable __cause)
    {
        super(__cause);
        reason = "";
    }

    public ZoomOutOfRangeException(int maxLvl, int actualLvl, String reason)
    {
        this.maxLvl = maxLvl;
        this.actualLvl = actualLvl;
        this.reason = reason;
    }

    public ZoomOutOfRangeException(int maxLvl, int actualLvl, String reason, Throwable __cause)
    {
        super(__cause);
        this.maxLvl = maxLvl;
        this.actualLvl = actualLvl;
        this.reason = reason;
    }

    public String
    ice_name()
    {
        return "Demo::ZoomOutOfRangeException";
    }

    public int maxLvl;

    public int actualLvl;

    public String reason;

    protected void
    __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice("::Demo::ZoomOutOfRangeException", -1, true);
        __os.writeInt(maxLvl);
        __os.writeInt(actualLvl);
        __os.writeString(reason);
        __os.endWriteSlice();
    }

    protected void
    __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        maxLvl = __is.readInt();
        actualLvl = __is.readInt();
        reason = __is.readString();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 1554114953L;
}