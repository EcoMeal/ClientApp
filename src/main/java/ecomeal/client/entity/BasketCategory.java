package ecomeal.client.entity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class BasketCategory {
	
	private int id;
	
	private String name;

	private File image;
	
	public BasketCategory(int id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = new File("category" + id);
		image = (image.equals(null) || image.isEmpty())? "null_logomark_400x400.jpg" : image;
		try {
			FileUtils.copyURLToFile(new URL("http://vps434333.ovh.net/uploads/images/" + image), this.image);
			} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			this.image = new File(image);
			e.printStackTrace();
		}
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
