package ecomeal.client.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	/* The ID must be set when the order has been validated */
	private Integer id;
	private Timestamp orderTime;
	private Timestamp deliveryTime;
	private Map<Basket, Integer> paniers;
	
	public Order() {
		paniers = new HashMap<Basket, Integer>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public Map<Basket, Integer> getPaniers() {
		return paniers;
	}
	
	public Timestamp getOrderTime() {
		return orderTime;
	}
	
	public Timestamp getDeliveryTime() {
		return deliveryTime;
		
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
	
	public void clearOrder() {
		paniers.clear();
	}
	
	public int getPrice() {
		int res = 0;
		for(Map.Entry<Basket, Integer> entry : paniers.entrySet()) {
			res += (entry.getKey().getPrice() * entry.getValue());
		}
		return res;
	}
	
	public void validate(Timestamp deliveryTime) {
		
		// TODO : Generate order ID
		// id = ???
		orderTime = new Timestamp(System.currentTimeMillis());
		this.deliveryTime = deliveryTime;
	}

}