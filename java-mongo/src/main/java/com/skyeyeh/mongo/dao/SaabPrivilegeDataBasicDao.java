package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.skyeyeh.mongo.model.SaabPrivilegeData;

/**
 * 安控交易功能 BasicDao。
 */
public class SaabPrivilegeDataBasicDao extends BasicDaoImpl {
    public SaabPrivilegeDataBasicDao() {
        super(new MongoClient("192.168.99.100", 27017), "pssmgr", "saab_privilege_data");
    }

    public void insert(String app_id, String privilege_id, String c_name, char type) {
        SaabPrivilegeData saabPrivilegeData = new SaabPrivilegeData(app_id, privilege_id, c_name, type);

        insertOne(saabPrivilegeData.document);
    }
}
