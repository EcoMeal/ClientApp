package ecomeal.client.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	/* The ID must be set when the order has been validated */
	private Integer id;
	
	/* The moment we validate the order */
	private Timestamp orderTime;
	
	/* The planned moment the get his basket */
	private Timestamp deliveryTime;
	private Map<Basket, Integer> baskets;
	
	public Order() {
		baskets = new HashMap<Basket, Integer>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Map<Basket, Integer> getBaskets() {
		return baskets;
	}
	
	public Timestamp getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(Timestamp orderTime){
		this.orderTime = orderTime;
	}
	
	public Timestamp getDeliveryTime() {
		return deliveryTime;
		
	}
	
	public void setDeliveryTime(Timestamp deliveryTime){
		this.deliveryTime = deliveryTime;
	}
	
	public void addBasket(Basket basket) {
		if(!baskets.containsKey(basket)){
			baskets.put(basket, 1);
		}
		else{
			baskets.put(basket, baskets.get(basket) + 1);
		}
	}
	
	public void removeBasket(Basket basket) {
		if(!baskets.containsKey(basket)){
			// Problem, on essaie de supprimer un élément qui n'est pas dans la liste
			return;
		}
		baskets.put(basket, baskets.get(basket) - 1);
		if(baskets.get(basket) == 0) {
			baskets.remove(basket);
		}
	}
	
	public void clearOrder() {
		baskets.clear();
	}
	
	public int getPrice() {
		int res = 0;
		for(Map.Entry<Basket, Integer> entry : baskets.entrySet()) {
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