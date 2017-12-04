package ecomeal.client.entity;

import java.io.File;
import java.util.List;

public class Basket {
	
	//private int id;
	//private BasketCategory basketCategory;
	private String name;
	private Integer price;
	private String category;
	//private String category_image;
	private List<Product> products;
	private File image;
	
	/*public Basket(int id, BasketCategory basketCategory, String name, List<Product> products, File image) {
		this.id = id;
		this.basketCategory = basketCategory;
		this.name = name;
		this.products = products;
		this.image = image;
	}*/
	
	public Basket(String name, Integer price, String category, String category_image, List<Product> products) {
		this.name = name;
		this.price = price;
		this.category = category;
		category_image = (category_image.equals(null) || category_image.isEmpty())? "null_logomark_400x400.jpg" : category_image;
		this.image = new File(getClass().getClassLoader().getResource(category_image).getFile());
		this.products = products;
	}
	
	/*public int getId() {
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
	}*/
	
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

	/*public String getCategory_image() {
		return category_image;
	}

	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}*/
	
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
