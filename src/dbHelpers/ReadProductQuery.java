/**
 * 
 */
package dbHelpers;

import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

import model.Product;

/**
 * @author danielcrittenden
 *
 */
public class ReadProductQuery {
	
	private Connection connection;
	private ResultSet results;
	static Locale locale = new Locale("en", "US");      
	static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	
	public ReadProductQuery(String dbName, String uname, String pwd){
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
	
	public void doRead(){
		String query = "SELECT * FROM product";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getInventoryQty(int productId) {
		Product product = new Product();
		String query = "SELECT * FROM product WHERE id=?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, productId);
			this.results = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(this.results.next()){
				product.setId(this.results.getInt("id"));
				product.setName(this.results.getString("name"));
				product.setPrice(this.results.getDouble("price"));
				product.setImageAddress(this.results.getString("image_addr"));
				product.setInventoryQuantity(this.results.getInt("inventory_qty"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return product.getInventoryQuantity();
	}
	
	public String getHTMLTable(){
		String table = "";
		
		table += "<table border='1px'>";
		table += "<tr><th>Name</th><th>Price</th><th>Image</th><th>Inventory</th><th colspan='2'></th></tr>";
		
		try {
			while(this.results.next()){
				Product product = new Product();
				product.setId(this.results.getInt("id"));
				product.setName(this.results.getString("name"));
				product.setPrice(this.results.getDouble("price"));
				product.setImageAddress(this.results.getString("image_addr"));
				product.setInventoryQuantity(this.results.getInt("inventory_qty"));
				
				table += "<form action='validate'>";
				table += "<input type='hidden' name='productId' value='" + product.getId() + "'>";
				table += "<tr>";
				table += "<td>";
					table += product.getName();
				table += "</td>";
				table += "<td>";
					table += currencyFormatter.format(product.getPrice());
				table += "</td>";	
				table += "<td>";
					table += "<img class='plp' src='" + product.getImageAddress() + "' />";
				table += "</td>";
				table += "<td>";
					table += product.getInventoryQuantity();
				table += "</td>";					
				table += "<td>";
					table += "<input type='text' class='textbox' name='quantity' value='1'>";
				table += "</td>";
				table += "<td>";
					if (product.getInventoryQuantity() >0) {
						table += " <input type='submit' class='button' name='submit' value='Add to Cart'>";
					} else {
						table += "Out Of Stock";
					}
				table += "</td>";
				table += "</tr>";
				table += "</form>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		table += "</table>";
		
		
		return table;
	}
 
}
