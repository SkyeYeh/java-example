package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * MongoDB Java。需使用 mongo-java-driver-3.2.2.jar。
 */
public class MongoDBJDBC extends DaoImpl {
    final Logger logger = LoggerFactory.getLogger(MongoDBJDBC.class);

    public MongoDBJDBC() {
        super();
    }

    public void insert() {
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");

        insertOne(document);
    }

    /**
     * 增加檔案。
     *
     * @throws IOException 增加檔案例外
     */
    public void createFile() throws IOException {
        // 連接到 mongodb 服務。
        MongoClient mongoClient = getMongoClient();

        GridFS gridFS = new GridFS(mongoClient.getDB(databaseName));
        GridFSInputFile file = gridFS.createFile(new File("D:\\Downloads\\level05.pdf"));
        file.setFilename("new_pdf.pdf");
        file.save();
    }
}
