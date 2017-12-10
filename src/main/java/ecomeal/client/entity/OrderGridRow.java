package ecomeal.client.entity;

public class OrderGridRow {
	
	private final Integer unitPrice;
	private final Integer quantity;
	private final String type;
	private final String basketName;
	
	public OrderGridRow(final String basketName, final String type, final Integer quantity, final Integer unitPrice) {
		this.basketName = basketName;
		this.type = type;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	public Integer getUnitPrice() {
		return unitPrice;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBasketName() {
		return basketName;
	}
}
