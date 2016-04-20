package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.skyeyeh.mongo.model.SaabPrivilegeData;

import java.io.File;
import java.io.IOException;

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

    /**
     * 儲存檔案。
     *
     * @param file     檔案
     * @param filename 檔案名稱
     * @throws IOException 儲存檔案例外
     */
    public void saveGridFSFile(File file, String filename) throws IOException {
        GridFS gridFS = new GridFS(datastore.getDb());
        GridFSInputFile gridFSFile = gridFS.createFile(file);
        gridFSFile.setFilename(filename);
        gridFSFile.save();
    }
}
