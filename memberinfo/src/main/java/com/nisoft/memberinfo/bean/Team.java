package com.nisoft.memberinfo.bean;

/**
 * Created by Administrator on 2017/5/21.
 */

public class Team {
    private String mName;
    private String mId;
    private String mDepartmentId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        mDepartmentId = departmentId;
    }
}
