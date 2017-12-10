package ecomeal.client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.entity.OrderGridRow;

public class OrderPopin extends Window {

	private static final long serialVersionUID = 283342705756542741L;
	
	private Grid<OrderGridRow> grid;
	
	public OrderPopin(Navigator navigator, Order order) {
		super("Ma commande");
		center();
		setResizable(false);
		setModal(true);
		setDraggable(false);
		setWidth("80%");
		setHeight("80%");
		
		grid = new Grid<OrderGridRow>(OrderGridRow.class);
		grid.setColumns("basketName", "type", "quantity", "unitPrice");
		// TODO : Change columns title
		
		int totalPrice = 0;
		List<OrderGridRow> rows = new ArrayList<OrderGridRow>();
		for(Map.Entry<Basket, Integer> entry : order.getBaskets().entrySet()) {
			Basket basket = entry.getKey();
			String basketName = basket.getName();
			String basketCategory = basket.getCategory();
			Integer quantity = entry.getValue();
			Integer unitPrice = basket.getPrice();
			totalPrice += (unitPrice * quantity);
			rows.add(new OrderGridRow(basketName, basketCategory, quantity, unitPrice));
		}
		
		grid.setItems(rows);
		grid.setSizeFull();
				
		Label totalPriceLabel = new Label("Prix total = " + totalPrice + "€");
		
		Button cancel = new Button("Retour", event -> close());
		Button validate = new Button("Commander");
		validate.addClickListener(event -> {
			close();
			navigator.navigateTo(EcomealConstants.HORAIRE_VIEW);
		});
		HorizontalLayout bot = new HorizontalLayout(cancel, validate);
		
		VerticalLayout content = new VerticalLayout(grid, totalPriceLabel, bot);
		
		setContent(content);
	}
	
}
