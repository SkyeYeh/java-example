package com.skyeyeh.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB Java。需使用 mongo-java-driver-3.2.2.jar。
 */
public class MongoDBJDBC {
    /**
     * 連接數據庫，你需要指定數據庫名稱，如果指定的數據庫不存在，mongo 會自動創建數據庫。
     */
    public void conn() {
        //連接到mongodb服務
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //連接到數據庫
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");
    }

    /**
     * Mongo 需要驗證用戶名及密碼。
     */
    public void connByUser() {
        // 連接到 MongoDB 服務如果是遠程連接可以替換 localhost 為服務器所在 IP 地址。
        // ServerAddress() 兩個參數分別為服務器地址和端口。
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        // MongoCredential.createScramSha1Credential() 三個參數分別為用戶名數據庫名稱密碼。
        MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        // 通過連接認證獲取 MongoDB 連接。
        MongoClient mongoClient = new MongoClient(addrs, credentials);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
        System.out.println("Connect to database successfully");
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別中的 createCollection() 來創建集合。
     */
    public void vreateCollection() {
        // 連接到 mongodb 服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");

        mongoDatabase.createCollection("test");
        System.out.println("集合創建成功");
    }

    /**
     * 使用 com.mongodb.client.MongoDatabase 類別的 getCollection() 方法來獲取一個集合。
     *
     * @return 取得的集合。
     */
    public MongoCollection<Document> getCollection() {
        // 連接到mongodb服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //連接到數據庫
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合test選擇成功");
        return collection;
    }

    /**
     * 使用 com.mongodb.client.MongoCollection 類別的 insertMany() 方法來插入一個文檔。
     */
    public void insertMany() {
        //連接到 mongodb 服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合test選擇成功");

        // 插入文檔。
        // 1. 創建文檔org.bson.Document參數為key-value的格式。
        // 2. 創建文檔集合List<Document>。
        // 3. 將文檔集合插入數據庫集合中mongoCollection.insertMany(List<Document>)插入單個文檔可以用mongoCollection.insertOne(Document)。
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文檔插入成功");
    }

    /**
     * 使用 com.mongodb.client.MongoCollection 類別中的 find() 方法來獲取集合中的所有文檔。
     *
     * @return 取得的集合。
     */
    public MongoCursor<Document> find() {
        // 連接到 mongodb 服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol ");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合test選擇成功");

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
        // 連接到 mongodb 服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合test選擇成功");

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
     */
    public void delete() {
        // 連接到mongodb服務。
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 連接到數據庫。
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合test選擇成功");

        // 刪除符合條件的第一個文檔。
        collection.deleteOne(Filters.eq("likes", 200));
        // 刪除所有符合條件的文檔。
        collection.deleteMany(Filters.eq(" likes", 200));

        // 檢索查看結果。
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

    public static void main(String[] args) {
        try {
            MongoDBJDBC mongoDBJDBC = new MongoDBJDBC();
            mongoDBJDBC.conn();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
