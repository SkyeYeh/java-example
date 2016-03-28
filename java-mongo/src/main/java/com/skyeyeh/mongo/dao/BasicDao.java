package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 連接到 mongodb 服務抽象。
 */
public abstract class BasicDao {
    final Logger logger = LoggerFactory.getLogger(BasicDao.class);
    protected DatastoreImpl datastore;

    String HOST = "192.168.99.100";
    int PORT = 27017;
    String databaseName = "my_database";
    String collectionName = "my_collection";

    public BasicDao(final MongoClient mongoClient, final String databaseName, final String collectionName) {
        initDataStore(mongoClient, databaseName, collectionName);
    }

    /**
     * 初始化集合物件。
     *
     * @param mongoClient  mongodb 服務
     * @param databaseName 資料庫
     * @param collectionName   集合
     */
    private void initDataStore(MongoClient mongoClient, String databaseName, String collectionName) {
        datastore = new DatastoreImpl(mongoClient, databaseName, collectionName);
    }

    /**
     * 連接到 mongodb 服務。
     *
     * @return Moongo 服務連線
     */
    abstract MongoClient getMongoClient();

    /**
     * 連接資料庫，你需要指定資料庫名稱，如果指定的資料庫不存在，mongo 會自動創建資料庫。
     *
     * @return 資料庫
     */
    public MongoDatabase getDayabase() {
        return datastore.getDayabase();
    }

    /**
     * 使用 MongoDatabase 類別中的 createCollection() 來創建集合。
     */
    public void createCollection() {
        datastore.createCollection();
    }

    /**
     * 使用 MongoDatabase 類別的 getCollection() 方法來獲取一個集合。
     *
     * @return 取得的集合
     */
    public MongoCollection<Document> getCollection() {
        return datastore.getCollection();
    }


    /**
     * 使用 MongoCollection 類別的 insertMany() 方法來插入一個文檔。
     *
     * @param document 一個文檔
     */
    public void insertOne(Document document) {
        datastore.insertOne(document);
    }

    /**
     * 使用 MongoCollection 類別的 insertMany() 方法來插入多個文檔。
     *
     * @param documents 多個文檔
     */
    public void insertMany(List<Document> documents) {
        datastore.insertMany(documents);
    }

    /**
     * 使用 DBCollection 類別中的 findOne() 方法來獲取第一個文檔，然後使用 remove 方法刪除。
     */
    public void delete() {
        datastore.delete();
    }

    /**
     * 使用 MongoCollection 類別中的 find() 方法來獲取集合中的所有文檔。
     *
     * @return 取得的集合
     */
    public MongoCursor<Document> find() {
        return datastore.find();
    }

    /**
     * MongoCollection 類別中的 updateMany() 方法來更新集合中的文檔。
     */
    public void updateMany() {
        datastore.updateMany();
    }
}
