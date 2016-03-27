package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 實作連接到 mongodb 服務。
 */
public class DaoImpl extends Dao {
    final Logger logger = LoggerFactory.getLogger(DaoImpl.class);

    public DaoImpl() {
        super();
    }

    /**
     * 連接到 mongodb 服務。
     *
     * @return Moongo 服務連線
     */
    @Override
    public MongoClient getMongoClient() {
        return new MongoClient(HOST, PORT);
    }
}
