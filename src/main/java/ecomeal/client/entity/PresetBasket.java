package ecomeal.client.entity;

import java.util.List;

public class PresetBasket extends Basket {

	public PresetBasket(String name, Integer price, String category, String category_image, List<Product> products) {
		super(name, price, category, category_image, products);
	}

	@Override
	public boolean equals(Basket basket) {
		if(!this.getName().equals(basket.getName())) {
			return false;
		}
		
		if(!this.getCategory().equals(basket.getCategory())) {
			return false;
		}
		
		if(!this.getPrice().equals(basket.getPrice())) {
			return false;
		}
		
		return true;
	}

}
