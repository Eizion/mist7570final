package model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Double price;
	private String imageAddress;
	private int inventoryQuantity;
	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param imageAddress
	 * @param inventoryQuantity
	 */
	public Product(int id, String name, Double price, String imageAddress, int inventoryQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageAddress = imageAddress;
		this.inventoryQuantity = inventoryQuantity;
	}
	
	public Product() {
		this.id = 0;
		this.name = "";
		this.price = 0.00;
		this.imageAddress = "";
		this.inventoryQuantity = 0;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the imageAddress
	 */
	public String getImageAddress() {
		return imageAddress;
	}

	/**
	 * @param imageAddress the imageAddress to set
	 */
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	/**
	 * @return the inventoryQuantity
	 */
	public int getInventoryQuantity() {
		return inventoryQuantity;
	}

	/**
	 * @param inventoryQuantity the inventoryQuantity to set
	 */
	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", imageAddress=" + imageAddress
				+ ", inventoryQuantity=" + inventoryQuantity + "]";
	}


	

}
