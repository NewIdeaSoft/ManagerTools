package com.nisoft.memberinfo.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/21.
 */

public class Company {
    private String mName;
    private String mId;
    private ArrayList<String> mStructure;

    public ArrayList<String> getStructure() {
        return mStructure;
    }

    public void setStructure(ArrayList<String> structure) {
        mStructure = structure;
    }

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
}
