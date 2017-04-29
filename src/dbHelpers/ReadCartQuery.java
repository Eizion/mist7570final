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
	
	public int doCount(User user){
		String query = "SELECT COUNT(*) AS count FROM cart WHERE userId = ?";
		int queryCount = 0;
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ps.setInt(1, user.getId());
			
			this.results = ps.executeQuery();
			
			while(this.results.next()){
				queryCount = results.getInt("count");
			}
			
			System.out.print(queryCount);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryCount;
	} 
		
	
	
	public String getHTMLTable(User user) throws SQLException{
		
		doRead(user);
		
		String table = "";
		int quantity = 0;
		double totalPrice = 0;
		double grandTotal = 0;
		
		table += "<table border='1px'>";
		table += "<thead>";
		table += "<tr><th>Product Name</th><th>Inventory Remaining</th><th>Price</th><th>Quantity</th><th colspan='2'>Action</th><th>Total Price</th></tr>";
		table += "</thead>";
		
		// if count of items in cart > 0
			
			try {
				while(this.results.next()){
					Product product = new Product();
					product.setId(this.results.getInt("id"));
					product.setName(this.results.getString("name"));
					product.setPrice(this.results.getDouble("price"));
					product.setImageAddress(this.results.getString("image_addr"));
					product.setInventoryQuantity(this.results.getInt("inventory_qty"));
					
					quantity = this.results.getInt("quantity");
					totalPrice = product.getPrice() * quantity;
					grandTotal += totalPrice;
					
					table += "<form action='validate'>";
					table += "<input type='hidden' name='productId' value='" + product.getId() + "'>";
					table += "<tr>";
					table += "<td>";
						table += product.getName();
					table += "</td>";
					table += "<td>";
					table += product.getInventoryQuantity();
				table += "</td>";				
					table += "<td>";
						table += product.getPrice();
					table += "</td>";				
					table += "<td>";
						table += "<input type='text' class='textbox' name='quantity' value='" + quantity + "'>";
					table += "</td>";
					table += "<td>";
						table += " <input type='submit' class='button' name='submit' value='Update'>";
					table += "</td>";
					table += "<td>";
						table += " <input type='submit' class='button' name='submit' value='Remove'>";
					table += "</td>";				
					table += "<td>";
						table += currencyFormatter.format(totalPrice);
					table += "</td>";		
					table += "</tr>";
					table += "</form>";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		table += "<tfoot>";
		table += "<tr><th colspan='6'>Grand Total</th><th>";
		table += currencyFormatter.format(grandTotal);
		table += "</th></tr>";
		table += "</tfoot>";
		table += "</table>";
		
		// else, return plain text telling user that nothing is in cart
	
		return table;
	}

}
