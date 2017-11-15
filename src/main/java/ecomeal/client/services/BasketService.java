package ecomeal.client.services;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import ecomeal.client.entity.Product;

public class BasketService extends AbstractService {
	
	private final static BasketService INSTANCE = new BasketService();
	
	public static BasketService getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @return all available Product objects.
	 */
	public synchronized List<Product> findAll() {
		return null;
	}

	@Override
	public void init() {
		client = ClientBuilder.newClient();
		target = client.target("url");
	}
	
	
	
	public Product getProductResponse(String place) {
		return target.queryParam("q", place).request(MediaType.APPLICATION_JSON).get(Product.class);
	}

}
