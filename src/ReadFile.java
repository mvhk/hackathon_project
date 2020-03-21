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

//	total for counting the average
	static double total = 0;

//	maximum value in the given input
	static double max = 0;

//	Initializing the connection
	static Connection connection = null;

//	Initializing the Statement
	static Statement statement = null;

//	Resultset
	ResultSet rs = null;

//	Database Initialization
	static String databaseName = "HackPro";
	static String url = "jdbc:mysql://localhost:3333/" + databaseName;
	static String userName = "root";
	static String passWord = "1234";

	public static void main(String[] args) {

//		Json object initialization
		JSONObject obj = new JSONObject();

		try {

//			getting the connection from the server
			connection = DriverManager.getConnection(url, userName, passWord);

			System.out.println("Database connection successful!\n");

			statement = connection.createStatement();

//			Inserting the values into the table 
			PreparedStatement pStmt = (PreparedStatement) connection
					.prepareStatement("INSERT into table1 (transaction, avg, max) values(?,?,?)");

			String line;

//			getting the required file
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
//			looping through the entire file
			while ((line = br.readLine()) != null) {

//				getting the values in the file based on the space separating them
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

//				pushing the transaction name to the table for the particular row
				pStmt.setString(1, "Transaction2");

				while (stringTokenizer.hasMoreElements()) {
//					iterating to required CPU value in a row
					int x = 0;
//					looping until the required CPU data
					while (x < 8) {
						stringTokenizer.nextElement().toString();
						x++;
					}

//					required line
					Double reqCPU = Double.parseDouble(stringTokenizer.nextElement().toString());

//					changing the max value
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

//			pushing to db
			pStmt.setDouble(2, average);
			pStmt.setDouble(3, total);
			pStmt.execute();
			  
//			printing the json
			System.out.println(obj);

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
