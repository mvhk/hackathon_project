import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
import com.mysql.jdbc.PreparedStatement;

public class Main {

	static BufferedReader bufferedReader = null;

//	change the useCaseName, It is Primary Key in the Table
	static String usecasename = "Sample6";

//	Initializing the required variables
	static Double maximum = 0.0, average = 0.0, memory_val = 0.0, total = 0.0;

//	iterator for 1s,2s,...
	static Integer itr = 0;

// for skipping the every second line initializing the variable
	static int lineNum = 0;

	static String val = "";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

//		json objects for the json file
		JSONObject jsonObject0 = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();

//		getting the required input file
		File inputFile = new File("C:\\Users\\haris\\Desktop\\Memory.txt");

		bufferedReader = new BufferedReader(new FileReader(inputFile));

		String readLine = null;

		while ((readLine = bufferedReader.readLine()) != null) {

//			trimming the line 
			readLine = readLine.trim();

//			iterating the line on reading that line
			lineNum++;

//			skipping the every second line
			if (lineNum % 2 == 0)
				continue;

//			pushing everything into string array
			String[] array = readLine.split("   ");

//			getting the required memory value
			val = array[1].trim();

			memory_val = (Double.parseDouble(val) / 1000);

//			finding maximum value
			if (maximum < memory_val) {
				maximum = memory_val;
			}

//			finding the total value
			total = total + memory_val;
			itr++;

//			rounding the value to 2 decimal values and adding it to the json
			jsonObject1.put(itr + "s", Math.round(memory_val * 100.0) / 100.0);

		}

//		finding average
		average = (total / itr);

		maximum = Math.round(maximum * 100.0) / 100.0;

		average = Math.round(average * 100.0) / 100.0;

//		pushing to DB 
		System.out.println(pushToDB());

//		create a JSON File
		System.out.println(createJsonFile(jsonObject1,jsonObject0));
	}

	@SuppressWarnings("resource")
	private static String createJsonFile(JSONObject jsonObject1,JSONObject jsonObject0) throws IOException {

		jsonObject0.put("Usecasename", usecasename);
		jsonObject0.put("AverageMemory(MB)", average);
		jsonObject0.put("MaxMemory(MB)", maximum);
		jsonObject0.put("values", jsonObject1);

		FileWriter fileWriter = new FileWriter("Sample.json");
		fileWriter.write(jsonObject0.toJSONString());
		fileWriter.flush();
		return "Json Created";

	}

	private static String pushToDB() throws SQLException {

//		Initializing the connection
		Connection connection = null;

//		Database Initialization
		String databaseName = "HackPro";
		String url = "jdbc:mysql://localhost:3333/" + databaseName;
		String userName = "root";
		String passWord = "1234";

//		Initializing the Statement
		Statement statement = null;

//		Initializing the connection
		connection = DriverManager.getConnection(url, userName, passWord);

		statement = connection.createStatement();

//		Inserting the values into the table 
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("INSERT INTO table2 (useCaseName, max, avg) VALUES(?,?,?)");
		preparedStatement.setString(1, usecasename);
		preparedStatement.setDouble(2, maximum);
		preparedStatement.setDouble(3, average);

		preparedStatement.execute();

		return "Pushed to DB";

	}
}
