package ecomeal.client.entity;

import java.io.File;

public class BasketCategory {
	
	private int id;
	
	private String name;

	private File image;
	
	public BasketCategory(int id, String name, String image) {
		this.id = id;
		this.name = name;
		image = (image.equals(null) || image.isEmpty())? "null_logomark_400x400.jpg" : image;
		this.image = new File(getClass().getClassLoader().getResource(image).getFile());
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
}
