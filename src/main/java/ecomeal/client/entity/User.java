package ecomeal.client.entity;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private String token;
	
	public User(final int id, final String firstName, final String lastName, final String login, final String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.token = "";
	}
	
	public User(){
		this.id = 0;
		this.login = "";
		this.token = "";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
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
	
	public void setLogin(String login){
		this.login = login;
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
	
	public String getToken() {
		return token;
	}
	
	public void setToken(final String token) {
		this.token = token;
	}
	
}
