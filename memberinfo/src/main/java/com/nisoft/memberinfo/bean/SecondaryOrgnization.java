package com.nisoft.memberinfo.bean;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SecondaryOrgnization {
    private String mName;
    private int mSecOrgId;
    private int mUpperLevelId;
    private int mManagementLevel;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getSecOrgId() {
        return mSecOrgId;
    }

    public void setSecOrgId(int secOrgId) {
        mSecOrgId = secOrgId;
    }

    public int getUpperLevelId() {
        return mUpperLevelId;
    }

    public void setUpperLevelId(int upperLevelId) {
        mUpperLevelId = upperLevelId;
    }

    public int getManagementLevel() {
        return mManagementLevel;
    }

    public void setManagementLevel(int managementLevel) {
        mManagementLevel = managementLevel;
    }
}
