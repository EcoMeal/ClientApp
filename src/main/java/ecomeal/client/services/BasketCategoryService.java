package ecomeal.client.services;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.BasketCategory;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.UrlWrapper;

public class BasketCategoryService extends AbstractService {

	private JsonTool jsonTool;

	public BasketCategoryService(JsonTool jsonTool) {
		this.jsonTool = jsonTool;
	}

	/**
	 * @return all available BasketCategory objects.
	 */
	public synchronized List<BasketCategory> findAll(String token) {

		List<BasketCategory> res = new ArrayList<BasketCategory>();

		/*
		 * URL url = new URL("http://vps434333.ovh.net/api/basket");
		 * HttpURLConnection connection = (HttpURLConnection)
		 * url.openConnection(); connection.connect(); InputStream inputStream =
		 * connection.getInputStream();
		 */

		String result = "";
		try {
			result = jsonTool.readJson(new UrlWrapper(EcomealConstants.URL_ECOMEAL + "/api/basket_category"), token);
			System.out.println(result);
	        if(result.equals("PROBLEME")){
	        	return res;
	        }else{
				JSONArray array = new JSONArray(result);
				JSONObject obj;
				for (int i = 0; i < array.length(); i++) {
					obj = array.getJSONObject(i);
					int basketCategoryId = obj.getInt("id");
					String basketCategoryName = obj.getString("name");
					String basketCategoryImage = obj.get("image").equals(null) ? ""
							: obj.getString("image");
	
					BasketCategory basketCategory = new BasketCategory(basketCategoryId, basketCategoryName, basketCategoryImage);
					res.add(basketCategory);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return res;
		}

		return res;
	}

}
