package com.data0123.fortest.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;

import static com.mongodb.client.model.Filters.lt;

/**
 * @author xiaohuqi@126.com 2017/12/26
 **/
public class GenEntNameDic {
	public static void main(String[] args) {
		filter();
	}

	private static void readFromMongo(){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/temp/entname.dic"), "utf-8"));

			MongoClient mongoClient = new MongoClient("localhost", 19130);
			MongoDatabase mdb = mongoClient.getDatabase("entkg");

			int c = 0;
			for (Document document : mdb.getCollection("basic_info").find(lt("_id", 200000000000L))) {
				Long id = document.getLong("_id");
				if (id < 100000000000L) {
					continue;
				}
				bw.write(document.getString("name").concat("\n"));
				if(++ c % 10000 == 0){
					System.out.println(c);
				}
			}
			bw.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void filter(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/temp/entname.dic"), "utf-8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/temp/entname0.dic"), "gbk"));
			String line;
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.length() == 4){
//					System.out.println(line);
					continue;
				}
				bw.write(line.concat("\n"));
			}
			br.close();
			bw.close();
		}catch (Exception e){
			e.printStackTrace();;
		}
	}
}
