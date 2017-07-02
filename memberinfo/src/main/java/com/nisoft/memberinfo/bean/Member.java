package com.nisoft.memberinfo.bean;




import java.util.Date;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Member {
    private int mMemberId;
    private String mName;
    private String mPhoneNum;
    private String mPassWord;
    private String mGender;
    private String mPoliticalStatus;
    private Date mBirthday;
    private int mJobInfoId;

    public int getMemberId() {
        return mMemberId;
    }

    public void setMemberId(int memberId) {
        mMemberId = memberId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        mPhoneNum = phoneNum;
    }

    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getPoliticalStatus() {
        return mPoliticalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        mPoliticalStatus = politicalStatus;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public int getJobInfoId() {
        return mJobInfoId;
    }

    public void setJobInfoId(int jobInfoId) {
        mJobInfoId = jobInfoId;
    }
}
