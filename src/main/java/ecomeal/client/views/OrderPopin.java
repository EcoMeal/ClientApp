package ecomeal.client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

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
		setClosable(false);
		setResizable(false);
		setModal(true);
		setDraggable(false);
		setWidth("80%");
		
		grid = new Grid<OrderGridRow>(OrderGridRow.class);
		grid.getHeaderRow(0).setStyleName("ecomeal-title");
		grid.addStyleName("ecomeal-grid");
		grid.setSelectionMode(SelectionMode.NONE);
		grid.setColumns("basketName", "type", "quantity", "unitPrice");
		setGridSize();
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
		totalPriceLabel.addStyleName("ecomeal-text");
		
		Button cancel = new Button("Retour", event -> close());
		cancel.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.addStyleName("ecomeal-button");
		Button validate = new Button("Commander");
		validate.setStyleName(ValoTheme.BUTTON_PRIMARY);
		validate.addStyleName("ecomeal-button");
		validate.addClickListener(event -> {
			close();
			navigator.navigateTo(EcomealConstants.HORAIRE_VIEW);
		});
		HorizontalLayout bot = new HorizontalLayout(cancel, validate);
		
		VerticalLayout content = new VerticalLayout(grid, totalPriceLabel, bot);
		content.setComponentAlignment(bot, Alignment.BOTTOM_CENTER);
		
		setContent(content);
	}
	
	private void setGridSize() {
		Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('ecomeal-grid');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	var table = elements[i].childNodes[2].childNodes[0];" +
				"	for (var j = 0; j < table.childNodes.length; j++) {" +
				"		table.childNodes[j].childNodes[0].childNodes[0].childNodes[0].style.fontSize='28px';" +
				"	}" +
				"}"
		);
	}
	
}
