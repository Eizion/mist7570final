package dbHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.User;

public class UpdateCartQuery {
	
	private Connection connection;
	
	public UpdateCartQuery(String dbName, String uname, String pwd) {
		
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
	
	
	public void doAdd(User user, int productId, int quantity) {
		
		String query = "INSERT INTO cart (userId, productId, quantity) VALUES (?, ?, ?)";
		UpdateProductQuery upq = new UpdateProductQuery("online_store", "root", "root");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, user.getId());
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			
			ps.executeUpdate();
			
			// update inventory
			upq.changeInventoryQuantity(productId, quantity);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doUpdateQuantity(User user, int productId, int updateQuantity, int cartQuantity){
		
		String query = "UPDATE cart SET quantity=? WHERE userId=? and productId=?";
		UpdateProductQuery upq = new UpdateProductQuery("online_store", "root", "root");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, updateQuantity);
			ps.setInt(2, user.getId());
			ps.setInt(3, productId);
			
			ps.executeUpdate();
			
			// update inventory
			upq.changeInventoryQuantity(productId, updateQuantity - cartQuantity);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doRemove(User user, int productId) {
		
		String query = "DELETE FROM cart WHERE userId=? and productId=?";
		UpdateProductQuery upq = new UpdateProductQuery("online_store", "root", "root");
		ReadCartQuery rcq = new ReadCartQuery("online_store", "root", "root");
		int cartQuantity = rcq.lookupQuantity(user, productId);
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, user.getId());
			ps.setInt(2, productId);
			
			ps.executeUpdate();
			
			// update inventory
			
			upq.changeInventoryQuantity(productId, -cartQuantity);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}
