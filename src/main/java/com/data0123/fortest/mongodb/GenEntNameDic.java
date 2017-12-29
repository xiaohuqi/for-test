package com.data0123.fortest.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			String line;
			List<String> list = new ArrayList<>();
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.length() < 4){
//					System.out.println(line);
					continue;
				}
				if(line.endsWith("。") || line.endsWith("*") || line.endsWith("．") || line.endsWith("＊")){
					line = line.substring(0, line.length() - 1);
				}
				if(line.endsWith("(?)")){
					line = line.substring(0, line.length() - 3);
				}
				if(!line.matches("[\\u4e00-\\u9fa5（）()《》·．\\.&○０-９0-9a-zA-ZＡ-Ｚ ]+")){
					System.out.println(line);
					continue;
				}
				list.add(line);
			}
			br.close();
			System.out.println(list.size());

			Collections.sort(list);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/temp/entname_0.dic"), "gbk"));
			for(String line0 : list) {
				bw.write(line0.concat("\n"));
			}
			bw.close();
		}catch (Exception e){
			e.printStackTrace();;
		}
	}
}
