package ecomeal.client.entity;

import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private Map<Basket, Integer> paniers;
	
	public Order() {
		paniers = new HashMap<Basket, Integer>();
	}
	
	public void addBasket(Basket basket) {
		paniers.put(basket, paniers.get(basket) + 1);
	}
	
	public void removeBasket(Basket basket) {
		paniers.put(basket, paniers.get(basket) - 1);
		if(paniers.get(basket) == 0) {
			paniers.remove(basket);
		}
	}
	
	public int getPrice() {
		int res = 0;
		for(Map.Entry<Basket, Integer> entry : paniers.entrySet()) {
			res += (entry.getKey().getPrice() * entry.getValue());
		}
		return res;
	}

}
