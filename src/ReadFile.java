import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
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

//	total for counting the avg
	static double total = 0;

//	maximum value in the given input
	static double maximum = 0;

//	Initializing the connection
	static Connection connection = null;

//	Initializing the Statement
	static Statement statement = null;
	static String transname = "Transaction10";
	static JSONArray array = new JSONArray();
//	Resultset
	ResultSet rs = null;

//	Database Initialization
	static String databaseName = "HackPro";
	static String url = "jdbc:mysql://localhost:3333/" + databaseName;
	static String userName = "root";
	static String passWord = "1234";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

//		Json obj1ect initialization
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();
//		pushing the entire data into a file
		FileWriter fileWriter = new FileWriter("sample.json");

//		pushing the data fetched from db to HMTL file
		PrintWriter printWriter = new PrintWriter("sample.html");

//		writing the data to files - Starting of table before looping in the table
		printWriter.println(
				"<table border=1><caption>CPU VALUES</caption><tr><th>Transaction Name</th><th>MAXIMUM CPU TIME</th><th>AVERAGE CPU TIME</th></tr>");
		try {

//			getting the connection from the server
			connection = DriverManager.getConnection(url, userName, passWord);

			System.out.println("Database connection successful!\n");

			statement = connection.createStatement();

//			Inserting the values into the table 
			PreparedStatement pStmt = (PreparedStatement) connection
					.prepareStatement("INSERT into table1 (transaction, max, avg) values(?,?,?)");

			String line;

//			getting the required file
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
//			looping through the entire file
			while ((line = br.readLine()) != null) {

//				getting the values in the file based on the space separating them
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

//				pushing the transaction name to the table for the particular row
//				change the transaction name for each run because it is primary key

				pStmt.setString(1, transname);

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

//					System.out.println(reqCPU);
//					changing the max value
					if (maximum < reqCPU)
						maximum = reqCPU;

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

					obj1.put(some, reqCPU);

					arr[itr] = reqCPU;
//					System.out.println(sb.toString());
				}

			}
			for (int i = 0; i < arr.length; i++) {

				total += arr[i];
			}

			double average = total / arr.length;

			System.out.println(total);
			System.out.println(average);
			maximum = Math.round(maximum * 100.0) / 100.0;
			average = Math.round(average * 100.0) / 100.0;

//			inserting the values into json
			obj2.put("values", obj1);
			obj2.put("max", maximum);
			obj2.put("average", average);

			obj3.put(transname, obj2);
			array.add(obj3);
//			pushing to db
			pStmt.setDouble(2, maximum);
			pStmt.setDouble(3, average);
			pStmt.execute();

//			printing the json
			System.out.println(obj2);

			try {
//				converting json object to string and writing it to a file
				fileWriter.write(array.toJSONString());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				fileWriter.flush();
				fileWriter.close();
			}

//			Fetching the db details using "SELECT Command writing it to the HTML"
			ResultSet results = statement.executeQuery("SELECT * FROM hackpro.table1");

			while (results.next()) {

//			Get the data from the current row using the column index - column data
				String transactionname = results.getString(1);
				double maximumresult = results.getDouble(2);
				double averageresult = results.getDouble(3);

//				System.out.println(transactionname + " " + maximumresult + " " + averageresult);

//				on new Iteration pushing the transactionname, maximumresult, averageresult to the db as new row
				printWriter.println("<tr>" + "<td>" + transactionname + "</td>" + "<td>" + maximumresult + "</td>"
						+ "<td>" + averageresult + "</td>" + "</tr>" + "\n");
			}
			printWriter.println("</table>");
//			closing the PrintWriter 
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

//				closing the BufferedReader
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
