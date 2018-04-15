package org.id.mongo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

public class MongoDBJDBC {
    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(getSequence2(5000l));
            }
        }) .start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getSequence(0l);
            }
        }) .start();

    }

    //自增长id
    private static int getSequence(Long time) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test_1");
        MongoCollection<Document> collection = mongoDatabase.getCollection("sequence");
        System.out.println(Thread.currentThread().getId() +"  "+"集合 sequence 选择成功");


        Document newDocument = new Document();
        newDocument.put("$inc", new Document().append("cnt", 1));

        //更新文档
        UpdateResult result = collection.updateOne(Filters.eq("coll_name", "0"), newDocument);
       // System.out.println(result.getClass());
        //System.out.println(JSON.toJSONString(result));
        //System.out.println(result.getUpsertedId().asString().toString());

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //检索查看结果 //查看到的是最新的记录
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(Thread.currentThread().getId() +"  "+mongoCursor.next());
        }



        return 1;
    }
    //自增长id
    private static int getSequence2(Long time) {

        // 连接到MongoDB
        Mongo mongo = new Mongo("localhost", 27017);
        // 打开数据库 testDB
        DB db = mongo.getDB("test_1");
        DBCollection table = db.getCollection("sequence");

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DBObject query = new BasicDBObject();
        query.put("coll_name", "0");
        DBObject newDocument =new BasicDBObject();
        newDocument.put("$inc", new BasicDBObject().append("cnt", 1));
        DBObject ret = table.findAndModify(query, newDocument);
        if (ret == null){
            return 0;
        }else{
            return (Integer)ret.get("cnt") + 1;
        }


    }

    //连接
    private static void connect() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test_1");
            System.out.println("Connect to database successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}