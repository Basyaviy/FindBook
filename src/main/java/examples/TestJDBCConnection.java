package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBCConnection {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/my_schema?useSSL=false&serverTimezone=UTC";
		String user = "buser";
		String pass = "buser";
		
		try {
			Connection myConn = 
					DriverManager.getConnection(url, user, pass);
			System.out.println("Connection is succesful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
				
	}

}
