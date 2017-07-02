package com.nisoft.memberinfo.db;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MemberSchema {
    public static final class MemberTable {
        public static final String NAME = "member";
        public static final class Cols {
            public static final String MEMBER_ID = "member_id";
            public static final String MEMBER_NAME = "member_name";
            public static final String MEMBER_PHONE_NUM = "member_phone_num";
            public static final String MEMBER_PASSWORD = "member_password";
            public static final String MEMBER_GENDER = "member_gender";
            public static final String MEMBER_POLITICAL_STATUS = "member_political_status";
            public static final String MEMBER_BIRTHDAY = "member_birthday";
            public static final String MEMBER_JOB_ID = "member_job_id";

        }
    }
}
