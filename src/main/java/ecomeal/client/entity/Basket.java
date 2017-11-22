package ecomeal.client.entity;

import java.io.File;
import java.util.List;

public class Basket {
	
	private int id;
	private BasketCategory basketCategory;
	private String name;
	private List<Product> products;
	private File image;
	
	public Basket(int id, BasketCategory basketCategory, String name, List<Product> products, File image) {
		this.id = id;
		this.basketCategory = basketCategory;
		this.name = name;
		this.products = products;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BasketCategory getBasketCategory() {
		return basketCategory;
	}
	
	public void setBasketCategory(BasketCategory basketCategory) {
		this.basketCategory = basketCategory;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

}
