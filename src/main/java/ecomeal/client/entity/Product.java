package ecomeal.client.entity;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 592950251371096496L;

	private String name;
	
	private String category;
	
	
	public Product(String name, String category) {
		this.name = name;
		this.category = category;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
