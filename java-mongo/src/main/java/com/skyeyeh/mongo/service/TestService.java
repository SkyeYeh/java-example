package com.skyeyeh.mongo.service;

import com.skyeyeh.mongo.dao.SaabPrivilegeDataBasicDao;
import com.skyeyeh.mongo.model.SaabPrivilegeData;

import java.io.File;
import java.io.IOException;

/**
 * Created by Skye on 2016/3/27.
 */
public class TestService {
    public void insert() {
        SaabPrivilegeDataBasicDao saabPrivilegeDataDao = new SaabPrivilegeDataBasicDao();
        saabPrivilegeDataDao.insert("APCS", "CA01", "測試用 CA 選單", SaabPrivilegeData.TYPE_MENU);
        try {
            saabPrivilegeDataDao.saveGridFSFile(new File("D:\\Downloads\\test.js"), "測試檔案01");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
