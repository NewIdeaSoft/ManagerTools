package com.nisoft.memberinfo.bean;

/**
 * Created by Administrator on 2017/5/21.
 */

public class Department {
    private String mName;
    private String mId;
    private String mPartCompanyId;

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

    public String getPartCompanyId() {
        return mPartCompanyId;
    }

    public void setPartCompanyId(String partCompanyId) {
        mPartCompanyId = partCompanyId;
    }
}
