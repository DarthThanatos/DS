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

public final class LaboratoryRoomHolder extends Ice.ObjectHolderBase<LaboratoryRoom>
{
    public
    LaboratoryRoomHolder()
    {
    }

    public
    LaboratoryRoomHolder(LaboratoryRoom value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof LaboratoryRoom)
        {
            value = (LaboratoryRoom)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _LaboratoryRoomDisp.ice_staticId();
    }
}