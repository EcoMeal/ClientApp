package ecomeal.client.services;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import ecomeal.client.entity.Product;

public class ProductService extends AbstractService {
	
	private final static ProductService INSTANCE = new ProductService();
	
	public static ProductService getInstance() {
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
