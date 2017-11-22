package ecomeal.client.entity;

public class Product {
	
	private int id;
	
	private ProductCategory productCategory;
	
	private String name;
	
	public Product(int id, ProductCategory productCategory, String name) {
		this.id =id;
		this.productCategory = productCategory;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
