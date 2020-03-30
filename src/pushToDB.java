import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class pushToDB {

	public pushToDB(String usecasename, Double maximum, Double average) throws SQLException {
		// Initializing the connection
		Connection connection = null;

		//	Database Initialization
		String databaseName = "HackPro";
		String url = "jdbc:mysql://localhost:3333/" + databaseName;
		String userName = "root";
		String passWord = "1234";

		//	Initializing the Statement
		Statement statement = null;

//	Initializing the connection
		connection = DriverManager.getConnection(url, userName, passWord);

		statement = connection.createStatement();

//	Inserting the values into the table 
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("INSERT INTO table2 (useCaseName, max, avg) VALUES(?,?,?)");
		preparedStatement.setString(1, usecasename);
		preparedStatement.setDouble(2, maximum);
		preparedStatement.setDouble(3, average);

		preparedStatement.execute();
		System.out.println("Pushed to DB Called");
	}
}
