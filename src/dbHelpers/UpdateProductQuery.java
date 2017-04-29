package dbHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.User;

public class UpdateProductQuery {
	
	private Connection connection;
	
	public UpdateProductQuery(String dbName, String uname, String pwd) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeInventoryQuantity(int productId, int cartQuantity){
		
		String query = "UPDATE product SET inventory_qty=? WHERE id=?";
		ReadProductQuery rpq = new ReadProductQuery("online_store", "root", "root");
		int productInventoryQuantity = rpq.getInventoryQty(productId);
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, productInventoryQuantity - cartQuantity);
			ps.setInt(2, productId);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
