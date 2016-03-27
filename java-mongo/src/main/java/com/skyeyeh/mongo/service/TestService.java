package com.skyeyeh.mongo.service;

import com.skyeyeh.mongo.dao.MongoDBJDBC;

/**
 * Created by Skye on 2016/3/27.
 */
public class TestService {
    public void writeFile() {
        MongoDBJDBC mongoDBJDBC = new MongoDBJDBC();
        mongoDBJDBC.getCollection();
        mongoDBJDBC.insert();
    }
}
