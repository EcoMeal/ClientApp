package ecomeal.client.services;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.entity.Product;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;

public class BasketService extends AbstractService {
	
	
	private JsonTool jsonTool;
	
	public BasketService(JsonTool jsonTool) {
		this.jsonTool = jsonTool;
	}
	
	/**
	 * @return all available Basket objects.
	 */
	public synchronized List<PresetBasket> findAll() {
		
		List<PresetBasket> res = new ArrayList<PresetBasket>();
		
		/*URL url = new URL("http://vps434333.ovh.net/api/basket");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();*/
		
		String result;
		try {
			result = jsonTool.readJson(new UrlWrapper(EcomealConstants.URL_ECOMEAL + "/api/basket"));
			//String result = inputStreamToString(inputStream, 8096);
		
			JSONArray array = new JSONArray(result);
			JSONObject obj;
			for(int i = 0; i < array.length(); i++) {
				obj = array.getJSONObject(i);
				int basketId = obj.getInt("id");
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
				
				PresetBasket basket = new PresetBasket(basketId, basketName, basketPrice, basketCategory, basketCategoryImage, productsList);
				res.add(basket);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		
		return res;
	}
	
	/**
	 * @return all available Basket objects.
	 */
	public synchronized List<PresetBasket> findBasketsByCategory(final int categoryId) {
		
		List<PresetBasket> res = new ArrayList<PresetBasket>();
		
		/*URL url = new URL("http://vps434333.ovh.net/api/basket");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();*/
		
		String result;
		try {
			result = jsonTool.readJson(new UrlWrapper(EcomealConstants.URL_ECOMEAL + "/api/basket/category/" + categoryId));
			//String result = inputStreamToString(inputStream, 8096);
		
			JSONArray array = new JSONArray(result);
			JSONObject obj;
			for(int i = 0; i < array.length(); i++) {
				obj = array.getJSONObject(i);
				int basketId = obj.getInt("id");
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
				
				PresetBasket basket = new PresetBasket(basketId, basketName, basketPrice, basketCategory, basketCategoryImage, productsList);
				res.add(basket);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		
		return res;
	}

}