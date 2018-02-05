package ecomeal.client.entity;

import java.io.Serializable;

public class Recap implements Serializable {

	private static final long serialVersionUID = 7184470602774522825L;
	
	private final Order order;
	private final String deliveryTime;
	
	public Recap(final Order order, final String deliveryTime) {
		this.order = order;
		this.deliveryTime = deliveryTime;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public String getDeliveryTime() {
		return deliveryTime;
	}
}
