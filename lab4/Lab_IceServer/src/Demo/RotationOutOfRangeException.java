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

public class RotationOutOfRangeException extends Ice.UserException
{
    public RotationOutOfRangeException()
    {
        reason = "";
    }

    public RotationOutOfRangeException(Throwable __cause)
    {
        super(__cause);
        reason = "";
    }

    public RotationOutOfRangeException(float minVertical, float maxVertival, float actual, String reason)
    {
        this.minVertical = minVertical;
        this.maxVertival = maxVertival;
        this.actual = actual;
        this.reason = reason;
    }

    public RotationOutOfRangeException(float minVertical, float maxVertival, float actual, String reason, Throwable __cause)
    {
        super(__cause);
        this.minVertical = minVertical;
        this.maxVertival = maxVertival;
        this.actual = actual;
        this.reason = reason;
    }

    public String
    ice_name()
    {
        return "Demo::RotationOutOfRangeException";
    }

    public float minVertical;

    public float maxVertival;

    public float actual;

    public String reason;

    protected void
    __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice("::Demo::RotationOutOfRangeException", -1, true);
        __os.writeFloat(minVertical);
        __os.writeFloat(maxVertival);
        __os.writeFloat(actual);
        __os.writeString(reason);
        __os.endWriteSlice();
    }

    protected void
    __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        minVertical = __is.readFloat();
        maxVertival = __is.readFloat();
        actual = __is.readFloat();
        reason = __is.readString();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = -418788526L;
}
