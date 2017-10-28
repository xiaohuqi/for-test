package com.data0123.fortest.db.mysql;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author xiaohuqi@126.com 2017-08-04 19:27
 **/
public class MySQLT0 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test1();
	}


	public static void test1(){
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			new Driver();
			Connection con = java.sql.DriverManager.getConnection(
					"jdbc:mysql://10.20.129.39:33067/wccy?useUnicode=true&characterEncoding=utf-8",
					"root", "root@hlet");
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select id from cheliangjiance limit 0, 1");
			while (rst.next()) {
				System.out.println(rst.getString(1));
			}


//			stmt.executeUpdate("CREATE TABLE `test1` (  `id` varchar(255) NOT NULL,  PRIMARY KEY  (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");

//			for(int i=(byte)'a';i<=(byte)'z';i++){
//				for(int j=(byte)'a';j<=(byte)'z';j++){
//					String tableName = (char)i + "" + (char)j;
//					stmt.executeUpdate("CREATE TABLE `" + tableName + "` (  `id` varchar(255) NOT NULL,  PRIMARY KEY  (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
//				}
//			}

			// 关闭连接、释放资源
			rst.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
