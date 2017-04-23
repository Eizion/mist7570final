package dbHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class LoginQuery {
	
	private PreparedStatement authenticateUserStatement;
	private Connection connection;
	
	public LoginQuery(String dbName, String uname, String pwd) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
			authenticateUserStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public User authenticateUser(String username, String password) {
		
		User user = null;
		
		try {
			authenticateUserStatement.setString(1, username);
			authenticateUserStatement.setString(2, password);
			ResultSet rs = authenticateUserStatement.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fName"), rs.getString("lName"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return user;
	}

}