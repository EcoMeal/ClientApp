package ecomeal.client.entity;

public class Recap {

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
