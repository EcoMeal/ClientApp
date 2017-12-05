package ecomeal.client.services;

import java.util.List;

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

}
