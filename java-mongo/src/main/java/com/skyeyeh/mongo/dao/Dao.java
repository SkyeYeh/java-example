package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 連接到 mongodb 服務抽象。
 */
public abstract class Dao {
    final Logger logger = LoggerFactory.getLogger(Dao.class);

    String HOST = "192.168.99.100";
    int PORT = 27017;
    String databaseName = "my_database";
    String collectionName = "my_collection";

    public Dao() {
        getMongoClient();
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
        // 連接到 mongodb 服務。
        MongoClient mongoClient = getMongoClient();

        // 連接到資料庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);

        logger.info("Connect to database success.");
        return mongoDatabase;
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別中的 createCollection() 來創建集合。
     */
    public void createCollection() {
        // 連接到資料庫。
        MongoDatabase mongoDatabase = getDayabase();

        mongoDatabase.createCollection(collectionName);
        logger.info("Create collection success.");
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別的 getCollection() 方法來獲取一個集合。
     *
     * @return 取得的集合
     */
    public MongoCollection<Document> getCollection() {
        //連接到資料庫
        MongoDatabase mongoDatabase = getDayabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        logger.info("Get collection success.");
        return collection;
    }


    /**
     * 使用 MongoCollection 類別的 insertMany() 方法來插入一個文檔。
     *
     * @param document 一個文檔
     */
    public void insertOne(Document document) {
        // 獲取一個集合。
        MongoCollection<Document> collection = getCollection();

        // 插入文檔。
        // 1. 創建文檔 Document 參數為 key-value 的格式。
        // 2. 創建文檔集合 List<Document>。
        // 3. 將文檔集合插入資料庫集合中 insertMany(List<Document>) 插入單個文檔可以用 insertOne(Document)。
        collection.insertOne(document);
        logger.info("Collection insert one success.");
    }

    /**
     * 使用 MongoCollection 類別的 insertMany() 方法來插入多個文檔。
     *
     * @param documents 多個文檔
     */
    public void insertMany(List<Document> documents) {
        // 獲取一個集合。
        MongoCollection<Document> collection = getCollection();

        // 插入文檔。
        // 1. 創建文檔 Document 參數為 key-value 的格式。
        // 2. 創建文檔集合 List<Document>。
        // 3. 將文檔集合插入資料庫集合中 insertMany(List<Document>) 插入單個文檔可以用 insertOne(Document)。
        collection.insertMany(documents);
        logger.info("Collection insert many success.");
    }

    /**
     * 使用 com.mongodb.DBCollection 類別中的 findOne() 方法來獲取第一個文檔，然後使用 remove 方法刪除。
     */
    public void delete() {
        // 連接到資料庫。
        MongoCollection<Document> collection = getCollection();

        // 刪除符合條件的第一個文檔。
        collection.deleteOne(Filters.eq("likes", 200));
        // 刪除所有符合條件的文檔。
        collection.deleteMany(Filters.eq("likes", 200));

        // 檢索查看結果。
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

    /**
     * 使用 com.mongodb.client.MongoCollection 類別中的 find() 方法來獲取集合中的所有文檔。
     *
     * @return 取得的集合
     */
    public MongoCursor<Document> find() {
        // 獲取一個集合。
        MongoCollection<Document> collection = getCollection();

        // 檢索所有文檔。
        // 1.獲取迭代器FindIterable<Document>。
        // 2.獲取游標MongoCursor<Document>。
        // 3.通過游標遍歷檢索出的文檔集合。
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
        return mongoCursor;
    }

    /**
     * com.mongodb.client.MongoCollection 類別中的 updateMany() 方法來更新集合中的文檔。
     */
    public void updateMany() {
        // 連接到資料庫。
        MongoCollection<Document> collection = getCollection();

        // 更新文檔將文檔中 likes=100 的文檔修改為 likes=200。
        collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));

        // 檢索查看結果。
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }
}
