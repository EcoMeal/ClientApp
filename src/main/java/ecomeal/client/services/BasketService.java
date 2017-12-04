package ecomeal.client.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.BasketCategory;
import ecomeal.client.entity.Product;
import ecomeal.client.entity.ProductCategory;

@ApplicationScoped
public class BasketService extends AbstractService {
	
	private final static BasketService INSTANCE = new BasketService();
	
	public static BasketService getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @return all available Basket objects.
	 */
	public synchronized List<Basket> findAll() {
		
		List<Basket> res = new ArrayList<Basket>();
		
		try {
			URL url = new URL("http://vps434333.ovh.net/api/basket");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			
			String result = inputStreamToString(inputStream, 1024);
			
			JSONArray array = new JSONArray(result);
			JSONObject obj;
			for(int i = 0; i < array.length(); i++) {
				obj = (JSONObject) array.get(i);
				Basket basket = new Basket((String) obj.get("name"), (Integer) obj.get("price"), (String) obj.get("category"), "", new ArrayList<Product>());
				res.add(basket);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	// TODO : Need REST url from web app
	@Override
	public void init() {
		//client = ClientBuilder.newClient();
		//target = client.target("http://vps434333.ovh.net/api/basket");
	}
	
	
	public List<Basket> getProductResponse(String place) {
		//return target.queryParam("q", place).request(MediaType.APPLICATION_JSON).get(Product.class);
		List<Basket> res = new ArrayList<Basket>();
		res.add(target.request(MediaType.APPLICATION_JSON).get(Basket.class));
		return res;
	}
	
	
	// Temporary list of basket to test the user interface
	/*private List<Basket> getFakeBaskets() {
		List<Basket> res = new ArrayList<Basket>();
		BasketCategory classic = new BasketCategory(1, "Classique");
		ProductCategory meat = new ProductCategory(1, "Viande");
		ProductCategory vegetables = new ProductCategory(2, "Légumes");
		ProductCategory drink = new ProductCategory(3, "Boisson");
		ProductCategory dessert = new ProductCategory(4, "Dessert");
		Product chicken = new Product(1, meat, "Poulet");
		Product beef = new Product(2, meat, "Boeuf");
		Product fries = new Product(3, vegetables, "Frites");
		Product water = new Product(4, drink, "Eau");
		Product donut = new Product(5, dessert, "Donut");
		List<Product> classicChicken = new ArrayList<Product>();
		classicChicken.add(chicken);
		classicChicken.add(fries);
		classicChicken.add(water);
		classicChicken.add(donut);
		List<Product> classicBeef = new ArrayList<Product>();
		classicChicken.add(beef);
		classicChicken.add(fries);
		classicChicken.add(water);
		classicChicken.add(donut);
		ClassLoader cl = getClass().getClassLoader();
		File chickenFile = new File(cl.getResource("chicken-150x150.png").getFile());
		File beefFile = new File(cl.getResource("beef-150x150.png").getFile());
		res.add(new Basket(1, classic, "Classic Chicken 1", classicChicken, chickenFile));
		res.add(new Basket(2, classic, "Classic Beef 1", classicBeef, beefFile));
		res.add(new Basket(3, classic, "Classic Chicken 2", classicChicken, chickenFile));
		res.add(new Basket(4, classic, "Classic Beef 2", classicBeef, beefFile));
		res.add(new Basket(5, classic, "Classic Chicken 3", classicChicken, chickenFile));
		res.add(new Basket(6, classic, "Classic Beef 3", classicBeef, beefFile));
		res.add(new Basket(7, classic, "Classic Chicken 4", classicChicken, chickenFile));
		res.add(new Basket(8, classic, "Classic Beef 4", classicBeef, beefFile));
		return res;
	}*/

}
