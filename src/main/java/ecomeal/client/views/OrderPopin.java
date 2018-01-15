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

import ecomeal.client.components.IntegerField;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.entity.OrderGridRow;
import ecomeal.client.ui.MainUI;

public class OrderPopin extends Window {

	private static final long serialVersionUID = 283342705756542741L;
	
	private Grid<OrderGridRow> grid;
	
	private MainUI ui;
	
	private final HorizontalLayout quantityManager;
	private final IntegerField basketQuantity;
	private final Button deleteBasket;
	private OrderGridRow selectedRow = null;
	
	private int totalPrice;
	private Label totalPriceLabel;
	
	public OrderPopin(Navigator navigator, MainUI ui) {
		super("Ma commande");
		center();
		setClosable(false);
		setResizable(false);
		setModal(true);
		setDraggable(false);
		setWidth("80%");
		
		this.ui = ui;
		
		grid = new Grid<OrderGridRow>(OrderGridRow.class);
		grid.getHeaderRow(0).setStyleName("ecomeal-title");
		grid.addStyleName("ecomeal-grid");
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumns("basketName", "type", "quantity", "unitPrice");
		totalPrice = 0;
		List<OrderGridRow> rows = new ArrayList<OrderGridRow>();
		for(Map.Entry<Basket, Integer> entry : ui.getOrder().getBaskets().entrySet()) {
			Basket basket = entry.getKey();
			int basketId = basket.getId();
			String basketName = basket.getName();
			String basketCategory = basket.getCategory();
			Integer quantity = entry.getValue();
			Integer unitPrice = basket.getPrice();
			totalPrice += (unitPrice * quantity);
			rows.add(new OrderGridRow(basketId, basketName, basketCategory, quantity, unitPrice));
		}
		
		grid.setItems(rows);
		grid.setSizeFull();
				
		totalPriceLabel = new Label("Prix total = " + totalPrice + "€");
		totalPriceLabel.setStyleName("ecomeal-text");
		
		basketQuantity = new IntegerField();
		deleteBasket = new Button("Supprimer");
		quantityManager = new HorizontalLayout(new Label("Quantity :"), basketQuantity, deleteBasket);
		quantityManager.setVisible(false);
		
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
		
		Button clearOrder = new Button("Vider la commande");
		clearOrder.addClickListener(event -> {
			// TODO : Maxime : faire popin de confirmation
			ui.getOrder().clearOrder();
			close();
		});
		
		HorizontalLayout bot = new HorizontalLayout(cancel, validate, clearOrder);
		bot.setComponentAlignment(clearOrder, Alignment.MIDDLE_RIGHT);
		
		VerticalLayout content = new VerticalLayout(grid, totalPriceLabel, quantityManager, bot);
		content.setComponentAlignment(bot, Alignment.BOTTOM_CENTER);
		
		setContent(content);
		
		setGridSize();
		
		basketQuantity.getPlus().addClickListener(event -> {
			if(selectedRow != null && selectedRow.getQuantity() < basketQuantity.getMaximum()) {				
				totalPrice += selectedRow.getUnitPrice();
				modifyQuantity();
			}
		});
		
		basketQuantity.getMinus().addClickListener(event -> {
			if(selectedRow != null && selectedRow.getQuantity() > basketQuantity.getMinimum()) {				
				totalPrice -= selectedRow.getUnitPrice();
				modifyQuantity();
			}
		});
		deleteBasket.addClickListener(event -> {
			if(selectedRow != null) {
				ui.getOrder().removeBasketsById(selectedRow.getId());
				totalPrice = totalPrice - (selectedRow.getUnitPrice() * selectedRow.getQuantity());
				totalPriceLabel.setValue("Prix total = " + totalPrice + "€");
				quantityManager.setVisible(false);
				refreshGrid();
				grid.clearSortOrder();
			}
		});
		
		grid.asSingleSelect().addValueChangeListener(event -> {
			selectedRow = event.getValue();
			if(selectedRow != null) {				
				quantityManager.setVisible(true);
				basketQuantity.setQuantity(selectedRow.getQuantity());
			}
		});
	}
	
	private void modifyQuantity() {
		ui.getOrder().setQuantityById(selectedRow.getId(), basketQuantity.getQuantity());
		OrderGridRow row = grid.getSelectedItems().iterator().next();
		row.setQuantity(basketQuantity.getQuantity());
		grid.clearSortOrder();
		totalPriceLabel.setValue("Prix total = " + totalPrice + "€");
	}
	
	private void refreshGrid() {
		totalPrice = 0;
		List<OrderGridRow> rows = new ArrayList<OrderGridRow>();
		for(Map.Entry<Basket, Integer> entry : ui.getOrder().getBaskets().entrySet()) {
			Basket basket = entry.getKey();
			int basketId = basket.getId();
			String basketName = basket.getName();
			String basketCategory = basket.getCategory();
			Integer quantity = entry.getValue();
			Integer unitPrice = basket.getPrice();
			totalPrice += (unitPrice * quantity);
			rows.add(new OrderGridRow(basketId, basketName, basketCategory, quantity, unitPrice));
		}
		
		grid.setItems(rows);
				
		totalPriceLabel = new Label("Prix total = " + totalPrice + "€");
		totalPriceLabel.setStyleName("ecomeal-text");
	}
	
	private void setGridSize() {
		Page.getCurrent().getJavaScript().execute(
				"if (document.readyState !== 'loading') {" + 
				"var elements = document.getElementsByClassName('ecomeal-grid');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	var table = elements[i].childNodes[2].childNodes[0];" +
				"	for (var j = 0; j < table.childNodes.length; j++) {" +
				"		var row = table.childNodes[j];" + 
				"		if (row.classList.contains('v-grid-header')) {" +
				"			row = row.childNodes[0];" +
				"			for (var k = 0; k < row.childNodes.length; k++) {" +
				"				row.childNodes[k].childNodes[0].style.fontSize='28px';" +
				"			}" +
				"		}" +
				"		else if(row.classList.contains('v-grid-body') && row.childElementCount > 0) {" +
				"			row = row.childNodes[0];" +
				"			for (var k = 0; k < row.childNodes.length; k++) {" +
				"				row.childNodes[k].style.fontSize='28px';" +
				"			}" +
				"		}" +
				"	}" +
				"}"
		);
	}
	
}
