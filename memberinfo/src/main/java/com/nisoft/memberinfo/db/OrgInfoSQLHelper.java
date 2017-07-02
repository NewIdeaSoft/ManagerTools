package com.nisoft.memberinfo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nisoft.memberinfo.db.JobInfoSchema.JobInfoTable;
import com.nisoft.memberinfo.db.MemberSchema.MemberTable;
import com.nisoft.memberinfo.db.OrgDbSchma.OrgTable;
import com.nisoft.memberinfo.db.SecOrgSchema.SecOrgTable;

/**
 * Created by Administrator on 2017/5/22.
 */

public class OrgInfoSQLHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "org_info.db";
    private Context mContext;
    private static final String CREATE_ORG = "create table "
            + OrgTable.NAME
            + "(_id integer primary key autoincrement,"
            + OrgTable.Cols.ORG_ID + " integer,"
            + OrgTable.Cols.ORG_NAME + " text,"
            + OrgTable.Cols.ORG_STRUCTURE + "text)";
    private static final String CREATE_SEC_ORG = "create table "
            + SecOrgTable.NAME
            + "(_id integer primary key autoincrement,"
            + SecOrgTable.Cols.SEC_ORG_ID + " integer,"
            + SecOrgTable.Cols.SEC_ORG_LEVEL + " integer,"
            + SecOrgTable.Cols.SEC_ORG_NAME + "text,"
            + SecOrgTable.Cols.UPPER_ORG_ID + " integer)";
    private static final String CREATE_JOB_INFO = "create table "
            + JobInfoTable.NAME
            + "(_id integer primary key autoincrement,"
            + JobInfoTable.Cols.JOB_ID + " integer,"
            + JobInfoTable.Cols.JOB_ORG_ID + " integer,"
            + JobInfoTable.Cols.JOB_STATION + "text,"
            + JobInfoTable.Cols.JOB_WORK_TYPE + " text,"
            + JobInfoTable.Cols.JOB_DUTIES + "text)";
    private static final String CREATE_MEMBER = "create table "
            + MemberTable.NAME
            + "(_id integer primary key autoincrement,"
            + MemberTable.Cols.MEMBER_ID + " integer,"
            + MemberTable.Cols.MEMBER_JOB_ID + " integer,"
            + MemberTable.Cols.MEMBER_NAME + "text,"
            + MemberTable.Cols.MEMBER_PHONE_NUM + " text,"
            + MemberTable.Cols.MEMBER_PASSWORD + " text,"
            + MemberTable.Cols.MEMBER_BIRTHDAY + " long,"
            + MemberTable.Cols.MEMBER_GENDER + " text,"
            + MemberTable.Cols.MEMBER_POLITICAL_STATUS + " text,)";

    public OrgInfoSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ORG);
        db.execSQL(CREATE_SEC_ORG);
        db.execSQL(CREATE_JOB_INFO);
        db.execSQL(CREATE_MEMBER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
