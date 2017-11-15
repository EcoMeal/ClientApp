package ecomeal.client.entity;

public class Basket {
	
	private int id;
	private BasketCategory basketCategory;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BasketCategory getBasketCategory() {
		return basketCategory;
	}
	
	public void setBasketCategory(BasketCategory basketCategory) {
		this.basketCategory = basketCategory;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
