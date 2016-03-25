package com.skyeyeh.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TV6015 on 2016/3/25.
 */
public class ＣlientFactoryByUserImpl implements ClientFactory {
    public static final String HOST = "192.168.99.100";
    public static final int PORT = 27017;
    public static final String USER_NAME = "admin";
    public static final String SOURCE = "source";
    public static final String PASSWORD = "pass";

    /**
     * 連接到 mongodb 服務。
     *
     * @return Moongo 服務連線
     */
    @Override
    public MongoClient getMongoClient() {
        // 連接到 MongoDB 服務如果是遠程連接可以替換 localhost 為服務器所在 IP 地址。
        // ServerAddress() 兩個參數分別為服務器地址和端口。
        ServerAddress serverAddress = new ServerAddress(HOST, PORT);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        // MongoCredential.createScramSha1Credential() 三個參數分別為用戶名資料庫名稱密碼。
        MongoCredential credential = MongoCredential.createScramSha1Credential(USER_NAME, SOURCE, PASSWORD.toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        // 通過連接認證獲取 MongoDB 連接。
        return new MongoClient(addrs, credentials);
    }
}
