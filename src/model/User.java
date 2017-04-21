package model;

public class User {
	
	private int id;
	private String userName;
	private String password;
	private String fName;
	private String lName;
	
	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @param id
	 * @param userName
	 * @param password
	 * @param fName
	 * @param lName
	 */
	public User(int id, String userName, String password, String fName, String lName) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
	

}
