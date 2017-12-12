package ecomeal.client.entity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;

public abstract class Basket {
	
	private int id;
	private String name;
	private Integer price;
	private String category;
	private List<Product> products;
	private File image;
	
	public Basket(int id,String name, Integer price, String category, String category_image, List<Product> products) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = new File("src/main/resources/basket" + id + "-" + category + ".png");
		category_image = (category_image.equals(null) || category_image.isEmpty())? "ddd0b571e08dd61152d89d2c6b973b6a.png" : category_image;
		try {
			FileUtils.copyURLToFile(new URL("http://vps434333.ovh.net/uploads/images/" + category_image), this.image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.products = products;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
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
