import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class ReadFile {
	static BufferedReader br = null;
//	iterator
	static int itr = 0;
// helper at adding to json
	static String some;
//	array for calculating the max and avg
	static double[] arr = new double[1000];
	static double total = 0;
	static double max = 0;

	static Connection myConn = null;
	static Statement myStmt = null;
	ResultSet myRs = null;
	static String databaseName = "HackPro";
	static String url = "jdbc:mysql://localhost:3333/" + databaseName;
	static String userName = "root";
	static String passWord = "1234";

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

		try {
			myConn = DriverManager.getConnection(url, userName, passWord);

			System.out.println("Database connection successful!\n");

			myStmt = myConn.createStatement();

			PreparedStatement pStmt = (PreparedStatement) myConn
					.prepareStatement("INSERT into table1 (transaction, avg, max) values(?,?,?)");
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
			while ((line = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
				pStmt.setString(1, "Transaction1");
				while (stringTokenizer.hasMoreElements()) {
//iterating to required cpu value in a row
					int x = 0;
					while (x < 8) {
						stringTokenizer.nextElement().toString();
						x++;
					}

//					required line
					Double reqCPU = Double.parseDouble(stringTokenizer.nextElement().toString());
					if (max < reqCPU)
						max = reqCPU;
					while (x < 11) {
						stringTokenizer.nextElement().toString();
						x++;
					}

//					StringBuilder sb = new StringBuilder();
//					sb.append(itr + "s" + reqCPU);
//					iterator

					itr++;
//					pushing it to json
					some = itr + "s";
					obj.put(some, reqCPU);
					arr[itr] = reqCPU;
//					System.out.println(sb.toString());
				}

			}
			for (int i = 0; i < arr.length; i++) {
				total = total + arr[i];
			}
			double average = total / arr.length;

//			  System.out.println(total);
//			  System.out.println(average);

			obj.put("total", total);
			obj.put("average", average);
			pStmt.setDouble(2, average);
			pStmt.setDouble(3, total);
			pStmt.execute();
//			  
//			printing the json
			System.out.println(obj);
			// 1. Get a connection to database

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
