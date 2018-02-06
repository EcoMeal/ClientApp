package ecomeal.client.entity;

public class OrderGridRow {
	
	private final int id;
	private final Double unitPrice;
	private Integer quantity;
	private final String type;
	private final String basketName;
	
	public OrderGridRow(final int id, final String basketName, final String type, final Integer quantity, final Double unitPrice) {
		this.id = id;
		this.basketName = basketName;
		this.type = type;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	public int getId() {
		return id;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int value) {
		quantity = value;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBasketName() {
		return basketName;
	}
}
