package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB Java。需使用 mongo-java-driver-3.2.2.jar。
 */
public class MongoDBJDBC extends ClientFactoryImpl {
    final Logger logger = LoggerFactory.getLogger(MongoDBJDBC.class);
    private String databaseName = "my_database";

    /**
     * @param databaseName 資料庫名稱。
     */
    public MongoDBJDBC(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * 連接資料庫，你需要指定資料庫名稱，如果指定的資料庫不存在，mongo 會自動創建資料庫。
     *
     * @return 資料庫
     */
    private MongoDatabase getDayabase() {
        // 連接到 mongodb 服務。
        MongoClient mongoClient = getMongoClient();

        // 連接到資料庫。
        databaseName = "databaseName";
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName
        );

        logger.info("Connect to database success.");
        return mongoDatabase;
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別中的 createCollection() 來創建集合。
     *
     * @param collectionName 集合名稱
     */
    public void createCollection(String collectionName) {
        // 連接到資料庫。
        MongoDatabase mongoDatabase = getDayabase();

        mongoDatabase.createCollection(collectionName);
        logger.info("Create collection success.");
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別的 getCollection() 方法來獲取一個集合。
     *
     * @param collectionName 集合名稱
     * @return 取得的集合
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        //連接到資料庫
        MongoDatabase mongoDatabase = getDayabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        logger.info("Get collection success.");
        return collection;
    }

    /**
     * 使用 com.mongodb.client.MongoCollection 類別的 insertMany() 方法來插入一個文檔。
     *
     * @param collectionName 集合名稱
     */
    public void insertMany(String collectionName) {
        // 獲取一個集合。
        MongoCollection<Document> collection = getCollection(collectionName);

        // 插入文檔。
        // 1. 創建文檔org.bson.Document參數為key-value的格式。
        // 2. 創建文檔集合List<Document>。
        // 3. 將文檔集合插入資料庫集合中mongoCollection.insertMany(List<Document>)插入單個文檔可以用mongoCollection.insertOne(Document)。
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");//.append("file_pdf", new File("D:\\Downloads\\level05.pdf"));
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文檔插入成功");
    }

    public void GridFS() throws IOException {
        // 連接到 mongodb 服務。
        MongoClient mongoClient = getMongoClient();

        GridFS gridFS = new GridFS(mongoClient.getDB("test"));
        GridFSInputFile file = gridFS.createFile(new File("D:\\Downloads\\level05.pdf"));
        file.setFilename("new_pdf.pdf");
        file.save();
    }

    /**
     * 使用 com.mongodb.client.MongoCollection 類別中的 find() 方法來獲取集合中的所有文檔。
     *
     * @param collectionName 獲取一個集合
     * @return 取得的集合
     */
    public MongoCursor<Document> find(String collectionName) {
        // 獲取一個集合。
        MongoCollection<Document> collection = getCollection(collectionName);

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
     *
     * @param collectionName 集合名稱
     */
    public void updateMany(String collectionName) {
        // 連接到資料庫。
        MongoCollection<Document> collection = getCollection(collectionName);

        // 更新文檔將文檔中 likes=100 的文檔修改為 likes=200。
        collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));

        // 檢索查看結果。
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

    /**
     * 使用 com.mongodb.DBCollection 類別中的 findOne() 方法來獲取第一個文檔，然後使用 remove 方法刪除。
     *
     * @param collectionName 集合名稱
     */
    public void delete(String collectionName) {
        // 連接到資料庫。
        MongoCollection<Document> collection = getCollection(collectionName);

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

    public static void main(String[] args) {
        try {
            MongoDBJDBC mongoDBJDBC = new MongoDBJDBC("my_database");
            mongoDBJDBC.insertMany("test_data");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
