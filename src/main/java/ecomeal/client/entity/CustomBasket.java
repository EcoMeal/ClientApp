package ecomeal.client.entity;

import java.util.List;

public class CustomBasket extends Basket {

	public CustomBasket(String name, Integer price, String category, String category_image, List<Product> products) {
		super(name, price, category, category_image, products);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Basket basket) {
		// TODO Auto-generated method stub
		return false;
	}

}
