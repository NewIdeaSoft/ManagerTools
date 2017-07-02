package com.nisoft.memberinfo.bean;

/**
 * Created by Administrator on 2017/5/22.
 */

public class JobInfo {
    private int mJobId;
    private int mSecOrgId;
    private String mStation;
    private String mWorkType;
    private String mDuties;

    public int getJobId() {
        return mJobId;
    }

    public void setJobId(int jobId) {
        mJobId = jobId;
    }

    public int getSecOrgId() {
        return mSecOrgId;
    }

    public void setSecOrgId(int secOrgId) {
        mSecOrgId = secOrgId;
    }

    public String getStation() {
        return mStation;
    }

    public void setStation(String station) {
        mStation = station;
    }

    public String getWorkType() {
        return mWorkType;
    }

    public void setWorkType(String workType) {
        mWorkType = workType;
    }

    public String getDuties() {
        return mDuties;
    }

    public void setDuties(String duties) {
        mDuties = duties;
    }
}
