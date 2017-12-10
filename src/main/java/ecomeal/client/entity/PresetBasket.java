package ecomeal.client.entity;

import java.util.List;

public class PresetBasket extends Basket {

	public PresetBasket(String name, Integer price, String category, String category_image, List<Product> products) {
		super(name, price, category, category_image, products);
	}

}
