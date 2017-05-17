package com.nisoft.managertools.entity;

/**
 * Created by NewIdeaSoft on 2017/4/24.
 */

public class Organization {
    private static String [] mOrgnizationStructure;
    private String [] mOrgnizationStructureNames;

    private Organization(String[] orgnizationStructure) {
        mOrgnizationStructure = orgnizationStructure;
    }

    public static Organization newInstance(String[] orgnizationStructure){
        return new Organization(orgnizationStructure);
    }

    public void setOrgnizationStructureNames(String[] orgnizationStructureNames) {
        mOrgnizationStructureNames = orgnizationStructureNames;
    }

    public static String[] getmOrgnizationStructure() {

        return mOrgnizationStructure;
    }

    public String[] getOrgnizationStructureNames() {
        return mOrgnizationStructureNames;
    }
}
