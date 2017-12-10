package ecomeal.client.views;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import ecomeal.client.components.IntegerField;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.entity.Product;

public class PresetBasketPopin extends Window {
	
	private PresetBasket basket;
	
	public PresetBasketPopin(PresetBasket basket, boolean ordering) {
		center();
		setClosable(false);
		setResizable(false);
		setModal(true);
		setDraggable(false);
		
		Label name = new Label(basket.getName());
		Label category = new Label(basket.getCategory());
		Label price = new Label(basket.getPrice() + "€");
		TextArea basketContent = new TextArea();
		StringBuilder builder = new StringBuilder();
		for(Product product : basket.getProducts()) {
			builder.append(product.getName() + "(" + product.getCategory() + ")\n");
		}
		builder.replace(builder.length() - 1, builder.length(), "");
		basketContent.setValue(builder.toString());
		VerticalLayout infos = new VerticalLayout(name, category, price, basketContent);
		Image image = new Image();
		image.setSource(new FileResource(basket.getImage()));
		image.setHeight(250, Unit.PIXELS);
		image.setWidth(250, Unit.PIXELS);
		HorizontalLayout top = new HorizontalLayout(image, infos);
		
		HorizontalLayout bot;
		if(ordering) {
			IntegerField quantity = new IntegerField();
			// TODO : Set max value according to the stock
			Button validate = new Button("Ajouter");
			validate.setStyleName(ValoTheme.BUTTON_PRIMARY);
			Button cancel = new Button("Annuler", event -> close());
			cancel.setStyleName(ValoTheme.BUTTON_DANGER);
			bot = new HorizontalLayout(quantity, validate, cancel);			
		} else {
			Button ok = new Button("OK", event -> close());
			ok.setStyleName(ValoTheme.BUTTON_PRIMARY);
			bot = new HorizontalLayout(ok);
		}
		VerticalLayout content = new VerticalLayout(top, bot);
		content.setComponentAlignment(bot, Alignment.BOTTOM_CENTER);
		
		setContent(content);
	}

}
