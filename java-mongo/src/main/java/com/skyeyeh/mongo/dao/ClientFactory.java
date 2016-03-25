package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;

/**
 * 連接到 mongodb 服務介面。
 */
public interface ClientFactory {
    MongoClient getMongoClient();
}
