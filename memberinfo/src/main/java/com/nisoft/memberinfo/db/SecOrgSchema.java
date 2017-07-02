package com.nisoft.memberinfo.db;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SecOrgSchema {
    public static final class SecOrgTable{
        public static final String NAME = "secondary_org";
        public static final class Cols{
            public static final String SEC_ORG_ID = "secondary_id";
            public static final String SEC_ORG_NAME = "secondary_name";
            public static final String SEC_ORG_LEVEL = "secondary_level";
            public static final String UPPER_ORG_ID = "upper_org_id";
        }
    }
}
