package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private Map<Product, Integer> cartCollection; 	// Map of products in cart and quantities
	/**
	 * @param userId
	 * @param userProduct
	 */
	public Cart(int userId, Map<Product, Integer> cartCollection) {
		this.userId = userId;
		this.cartCollection = cartCollection;
	}

	public Cart() {
		this.userId = 0;
		this.cartCollection = new HashMap<Product, Integer>();
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the cartCollection
	 */
	public Map<Product, Integer> getCartCollection() {
		return cartCollection;
	}

	/**
	 * @param cartCollection the cartCollection to set
	 */
	public void setCartCollection(Map<Product, Integer> cartCollection) {
		this.cartCollection = cartCollection;
	}
	

}
