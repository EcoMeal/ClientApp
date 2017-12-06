package ecomeal.client.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Product;
import ecomeal.client.tools.JsonTool;

public class BasketService extends AbstractService {
	
	
	private JsonTool jsonTool;
	
	public BasketService(JsonTool jsonTool) {
		this.jsonTool = jsonTool;
	}
	
	/**
	 * @return all available Basket objects.
	 */
	public synchronized List<Basket> findAll() {
		
		List<Basket> res = new ArrayList<Basket>();
		
		/*URL url = new URL("http://vps434333.ovh.net/api/basket");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();*/
		
		String result = jsonTool.readJson("http://vps434333.ovh.net/api/basket");
		//String result = inputStreamToString(inputStream, 8096);
		
		JSONArray array = new JSONArray(result);
		JSONObject obj;
		for(int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			String basketName = obj.getString("name");
			Integer basketPrice = obj.getInt("price");
			String basketCategory = obj.getString("category");
			String basketCategoryImage = obj.get("category_image").equals(null) ? "" : obj.getString("category_image");
			
			JSONArray products = obj.getJSONArray("products");
			List<Product> productsList = new ArrayList<Product>();
			for(int j = 0; j < products.length(); j++) {
				JSONObject jsonProduct = products.getJSONObject(j);
				String productName = jsonProduct.getString("name");
				String productCategory = jsonProduct.getString("category");
				Product product = new Product(productName, productCategory);
				productsList.add(product);
			}
			
			Basket basket = new Basket(basketName, basketPrice, basketCategory, basketCategoryImage, productsList);
			res.add(basket);
		}
		
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
