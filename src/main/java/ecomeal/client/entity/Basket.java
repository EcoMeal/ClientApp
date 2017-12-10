package ecomeal.client.entity;

import java.io.File;
import java.util.List;

public abstract class Basket {
	
	private String name;
	private Integer price;
	private String category;
	private List<Product> products;
	private File image;
	
	public Basket(String name, Integer price, String category, String category_image, List<Product> products) {
		this.name = name;
		this.price = price;
		this.category = category;
		category_image = (category_image.equals(null) || category_image.isEmpty())? "null_logomark_400x400.jpg" : category_image;
		this.image = new File(getClass().getClassLoader().getResource(category_image).getFile());
		this.products = products;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public File getImage() {
		return image;
	}
	
	public void setImage(File image) {
		this.image = image;
	}
	
	public abstract boolean equals(Basket basket);
	
}
