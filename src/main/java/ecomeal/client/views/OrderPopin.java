package ecomeal.client.views;

import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;

public class OrderPopin extends Window {

	private static final long serialVersionUID = 283342705756542741L;
	
	private Grid grid;
	
	public OrderPopin(Navigator navigator, Order order) {
		super("Ma commande");
		center();
		setResizable(false);
		setModal(true);
		setDraggable(false);
		
		grid = new Grid<Basket>(Basket.class);
		
		grid.setColumns("Panier", "Quantité", "Prix unitaire");
		
		for(Map.Entry<Basket, Integer> entry : order.getBaskets().entrySet()) {
			//grid.
		}
		
		setContent(new Label("Uesh uesh Maki ! Y'a pas de sushi !"));
	}
	
}
