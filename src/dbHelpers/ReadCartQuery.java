/**
 * 
 */
package dbHelpers;

import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

import model.Product;
import model.User;

/**
 * @author danielcrittenden
 *
 */
public class ReadCartQuery {
	
	private Connection connection;
	private ResultSet results;
	static Locale locale = new Locale("en", "US");      
	static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	
	public ReadCartQuery(String dbName, String uname, String pwd){
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		// set up the driver
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				this.connection = DriverManager.getConnection(url, uname, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean isInCart(User user, int productId) {
		
		boolean existsInDb = false;
		
		try {
			
			
			PreparedStatement ps = connection.prepareStatement("SELECT quantity FROM cart WHERE userId = ? and productId = ?");
			
			ps.setInt(1, user.getId());
			ps.setInt(2, productId);
			
			ResultSet results = ps.executeQuery();
			
			if (results.next()) {
				existsInDb = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existsInDb;
	}
	
	public int lookupQuantity(User user, int productId) {
		
		String query = "SELECT quantity FROM cart WHERE userId = ? and productId = ?";
		int productCartQuantity = 0;
		
		try {
			
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, user.getId());
			ps.setInt(2, productId);
			
			ResultSet results = ps.executeQuery();
			
			if (results.next()) {
				productCartQuantity = results.getInt("quantity");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productCartQuantity;
	}
	
	public void doRead(User user){
		String query = "SELECT * FROM cart, product WHERE productId = id AND userId = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ps.setInt(1, user.getId());
			
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public String getHTMLTable(){
		String table = "";
		String productName = "";
		int quantity = 0;
		double totalPrice = 0;
		double grandTotal = 0;
		table += "<table border='1'>";
		table += "<thead>";
		table += "<tr><th>Product Name</th><th>Quantity</th><th>Total Price</th></tr>";
		table += "</thead>";
		
		try {
			while(this.results.next()){
				
				productName = this.results.getString("name");
				quantity = this.results.getInt("quantity");
				totalPrice = this.results.getDouble("price") * quantity;
				grandTotal += totalPrice;
				
				table += "<tr>";
				table += "<td>";
					table += productName;
				table += "</td>";
				table += "<td>";
					table += quantity;
				table += "</td>";					
				table += "<td>";
					table += currencyFormatter.format(totalPrice);
				table += "</td>";		
				table += "</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table += "<tfoot>";
		table += "<tr><th colspan='2'>Grand Total</th><th>";
		table += currencyFormatter.format(grandTotal);
		table += "</th></tr>";
		table += "</tfoot>";
		table += "</table>";
		
		return table;
	}

}
