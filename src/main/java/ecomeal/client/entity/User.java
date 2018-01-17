package ecomeal.client.entity;

public class User {

	private final int id;
	private String firstName;
	private String lastName;
	private final String login;
	private String password;
	private String email;
	private Token token;
	
	public User(final int id, final String firstName, final String lastName, final String login, final String password, final String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}
	
	public boolean hasToken() {
		return token != null;
	}
	
	public Token getToken() {
		return token;
	}
	
	public void setToken(final Token token) {
		this.token = token;
	}
	
}
