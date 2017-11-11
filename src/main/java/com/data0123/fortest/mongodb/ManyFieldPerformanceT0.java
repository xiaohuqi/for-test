package com.data0123.fortest.mongodb;

import com.data0123.fortest.file.ReadFileUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.*;

public class ManyFieldPerformanceT0 {

	public static void main(String[] args) {
		try {
			String text = new ReadFileUtil().readFileByCharSet("d:/temp/1.txt", "utf-8");

			MongoClientOptions clientOptions = new MongoClientOptions.Builder().connectionsPerHost(1000)
					.maxWaitTime(60000).threadsAllowedToBlockForConnectionMultiplier(50).build();

			List<ServerAddress> addresses = new ArrayList<>();
			addresses.add(new ServerAddress("192.168.2.9", 19130));
			MongoClient mongoClient = new MongoClient(addresses, clientOptions);

			MongoCollection<Document> mc = mongoClient.getDatabase("kg_test").getCollection("attribute_data_1");

			Map<Integer, Integer> attDataTypeMap = new HashMap<>();
			Map<Integer, Integer> attConceptMap = new HashMap<>();
			Map<Integer, List<Integer>> conceptAttMap = new HashMap<>();

			for(int i=0;i<1000;i++){   //1000个属性
				int attDataType = (int)(Math.random() * 5) + 1;
				attDataTypeMap.put(i, attDataType);
				if(attDataType != 5){
					mc.createIndex(new Document("att_" + i, 1));
				}
				int conceptId = (int)(100 * Math.random());
				attConceptMap.put(i, conceptId);

				if(!conceptAttMap.containsKey(conceptId)){
					conceptAttMap.put(conceptId, new ArrayList<>());
				}
				conceptAttMap.get(conceptId).add(i);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Document> documentList = new ArrayList<>();
			for(int i=1;i<=10000000;i++){    //1千万实体
				int entityType = (int)(Math.random() * 1000);
				List<Integer> attList = conceptAttMap.get(entityType);
				for(int k=0,size=attList.size();k<size;k++){
					int attId = attList.get(k);
					Document document = new Document("entity_id", i).append("entity_type", entityType).append("attr_id", attId);
					int attDataType = attDataTypeMap.get(attId);
					switch (attDataType){
						case 1: document.append("attr_value", (int)(Integer.MAX_VALUE * Math.random())); break;
						case 2: document.append("attr_value", Integer.MAX_VALUE * Math.random()); break;
						case 3: document.append("attr_value", sdf.format(new Date())); break;
						case 4: int bp1 = (int)(1000 * Math.random()); document.append("attr_value", text.substring(bp1, bp1 * 2)); break;
						case 5: int bp2 = (int)(100000 * Math.random()); document.append("attr_value", text.substring(bp2)); break;
					}
					documentList.add(document);
				}

				if(i % 100 == 0){
					mc.insertMany(documentList);
					documentList.clear();
				}

			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
