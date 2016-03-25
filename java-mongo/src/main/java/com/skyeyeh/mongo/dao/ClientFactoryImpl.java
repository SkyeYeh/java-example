package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;

/**
 * 連接到 mongodb 服務實作。
 */
public class ClientFactoryImpl implements ClientFactory {
    public static final String HOST = "192.168.99.100";
    public static final int PORT = 27017;

    /**
     * 連接到 mongodb 服務。
     *
     * @return Moongo 服務連線
     */
    @Override
    public MongoClient getMongoClient() {
        return new MongoClient(ClientFactoryImpl.HOST, ClientFactoryImpl.PORT);
    }
}
