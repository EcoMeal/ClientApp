package ecomeal.client.entity;

import java.util.List;

public class CustomBasket extends Basket {

	public CustomBasket(int id, String name, Double price, String category, String category_image, List<Product> products) {
		super(id, name, price, category, category_image, products);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Basket basket) {
		// TODO Auto-generated method stub
		return false;
	}

}
