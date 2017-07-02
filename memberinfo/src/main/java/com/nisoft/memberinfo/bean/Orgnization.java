package com.nisoft.memberinfo.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Orgnization {
    private String mName;
    private int mOrgnizationId;
    private ArrayList<String> mStructure;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getOrgnizationId() {
        return mOrgnizationId;
    }

    public void setOrgnizationId(int orgnizationId) {
        mOrgnizationId = orgnizationId;
    }

    public ArrayList<String> getStructure() {
        return mStructure;
    }

    public void setStructure(ArrayList<String> structure) {
        mStructure = structure;
    }
}
