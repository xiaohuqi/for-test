package com.data0123.fortest.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class MongoT0 {

	public static void main(String[] args) {
		try{
			MongoClientOptions clientOptions =  new MongoClientOptions.Builder().connectionsPerHost(1000)
						.maxWaitTime(60000).threadsAllowedToBlockForConnectionMultiplier(50).build();
			
			List<ServerAddress> addresses = new ArrayList<ServerAddress>();
			addresses.add(new ServerAddress("192.168.25.137", 19130));
			MongoClient mongoClient = new MongoClient(addresses, clientOptions);
			
			DB db = mongoClient.getDB("lbs_daba_t0");
			DBCollection dbCollection = db.getCollection("lbs_daba_t1");
			
			dbCollection.createIndex(new BasicDBObject("vehicle_id", 1));
            dbCollection.createIndex(new BasicDBObject("latitude", 1));
            dbCollection.createIndex(new BasicDBObject("longitude", 1));
            dbCollection.createIndex(new BasicDBObject("update_time", 1));

            int vehicleNum = 10000000;
            int[] via = new int[vehicleNum];
            for(int i=0;i<vehicleNum;i++){
                via[i] = i;
            }

            List<DBObject> documentList = new ArrayList<>();
            for(int i=1;i<Integer.MAX_VALUE;i++){
                documentList.add(new BasicDBObject("vehicle_id", (int)(Math.random() * vehicleNum)).append("longitude", Math.random() * 180)
                        .append("latitude", Math.random() * 180).append("update_time", System.currentTimeMillis()));
                if(i % 10000 == 0){
                    dbCollection.insert(documentList);
                    documentList.clear();
                    System.out.println(i + "\t" + System.currentTimeMillis());
                }
            }
            dbCollection.insert(documentList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
