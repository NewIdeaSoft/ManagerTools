package com.nisoft.managertools.entity;

/**
 * Created by NewIdeaSoft on 2017/4/24.
 */

public class Member {
    private String mName;
    private String mNumber;
    private String mPassword;
    private String mTelephoneNumber;
    private String mMoblephoneNumber;
    private Organization mOrganization;
    private String mJop;
    private String mPosition;
    private int mPermissionLevel;

    public Member() {
    }

    public Member(String name, String moblephoneNumber) {
        mName = name;
        mMoblephoneNumber = moblephoneNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getTelephoneNumber() {
        return mTelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        mTelephoneNumber = telephoneNumber;
    }

    public String getMoblephoneNumber() {
        return mMoblephoneNumber;
    }

    public void setMoblephoneNumber(String moblephoneNumber) {
        mMoblephoneNumber = moblephoneNumber;
    }

    public Organization getOrganization() {
        return mOrganization;
    }

    public void setOrganization(Organization organization) {
        mOrganization = organization;
    }
}
