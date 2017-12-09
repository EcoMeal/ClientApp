package ecomeal.client.views;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ecomeal.client.components.IntegerField;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.ui.MainUI;

public class PresetBasketPopin extends Window {
	
	private final MainUI ui;
	private PresetBasket basket;
	
	public PresetBasketPopin(PresetBasket basket, MainUI ui) {
		center();
		setClosable(false);
		setResizable(false);
		setModal(true);
		setDraggable(false);
		this.ui = ui;
		
		Label name = new Label(basket.getName());
		Label category = new Label(basket.getCategory());
		Label price = new Label(basket.getPrice() + "€");
		VerticalLayout infos = new VerticalLayout(name, category, price);
		Image image = new Image();
		image.setSource(new FileResource(basket.getImage()));
		image.setHeight(250, Unit.PIXELS);
		image.setWidth(250, Unit.PIXELS);
		HorizontalLayout top = new HorizontalLayout(image, infos);
		IntegerField quantity = new IntegerField();
		// TODO : Set max value according to the stock
		Button validate = new Button("Ajouter");
		Button cancel = new Button("Annuler", event -> close());
		HorizontalLayout bot = new HorizontalLayout(quantity, validate, cancel);
		VerticalLayout content = new VerticalLayout(top, bot);
		
		setContent(content);
	}

}
