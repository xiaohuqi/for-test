package com.data0123.fortest.db.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLServerT0 {

	public static void main(String[] args) {
		t0();
		
	}
	
	public static void t0(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:sqlserver://58.217.99.135:1433;DatabaseName=wccy", "sa", "hlet@2015#");
			Statement stmt = conn.createStatement();
			String sql = "select * from cfc_vehicleinfo";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
			    String no = rs.getString("vehiclenumber");
			    System.out.println(no);
			}
			rs.close();
			conn.close();
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
