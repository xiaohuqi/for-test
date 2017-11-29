package com.data0123.fortest.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaohuqi@126.com 2017-11-21 23:26
 **/
public class UpdateTest {
	public static void main(String[] args) {
		try{
			MongoClientOptions clientOptions =  new MongoClientOptions.Builder().connectionsPerHost(1000)
					.maxWaitTime(60000).threadsAllowedToBlockForConnectionMultiplier(50).build();

			List<ServerAddress> addresses = new ArrayList<>();
			addresses.add(new ServerAddress("localhost", 19130));
			MongoClient mongoClient = new MongoClient(addresses, clientOptions);

			MongoDatabase mdb = mongoClient.getDatabase("kg_cbnode_c");
			MongoCollection<Document> aoMc = mdb.getCollection("attribute_object_1");
			MongoCollection<Document> ciMc = mdb.getCollection("concept_instance");

			Set<Long> insIdSet = new HashSet<>();
			for(Document insDoc : ciMc.find()){
				insIdSet.add(insDoc.getLong("ins_id"));
			}
			System.out.println(insIdSet.size());

			long t0 = System.currentTimeMillis();
			int c = 0;
			for(Document aoDoc : aoMc.find()){
				aoMc.updateOne(new Document("_id", aoDoc.getObjectId("_id")), new Document("$set", new Document("attr_extra", Math.random())));
				if(++ c % 10000 == 0){
					System.out.println(c);
				}
			}
			System.out.println(System.currentTimeMillis() - t0);

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
