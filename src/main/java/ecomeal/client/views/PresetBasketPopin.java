package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import ecomeal.client.components.IntegerField;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.entity.Product;
import ecomeal.client.ui.MainUI;

public class PresetBasketPopin extends Window {
	
	private PresetBasket basket;
	
	public PresetBasketPopin(Navigator navigator, PresetBasket basket, boolean ordering) {
		center();
		setClosable(false);
		setResizable(false);
		setModal(true);
		setDraggable(false);
		
		Label name = new Label(basket.getName());
		name.addStyleName("ecomeal-text");
		Label category = new Label(basket.getCategory());
		category.addStyleName("ecomeal-text");
		Label price = new Label(basket.getPrice() + "€");
		price.addStyleName("ecomeal-text");
		TextArea basketContent = new TextArea();
		basketContent.addStyleName("ecomeal-text");
		setTextAreaSize();
		StringBuilder builder = new StringBuilder();
		for(Product product : basket.getProducts()) {
			builder.append(product.getName() + " (" + product.getCategory() + ")\n");
		}
		builder.replace(builder.length() - 1, builder.length(), "");
		basketContent.setValue(builder.toString());
		basketContent.setEnabled(false);
		VerticalLayout infos = new VerticalLayout(name, category, price, basketContent);
		Image image = new Image();
		image.setSource(new FileResource(basket.getImage()));
		HorizontalLayout top = new HorizontalLayout(image, infos);
		
		HorizontalLayout bot;
		if(ordering) {
			IntegerField quantity = new IntegerField();
			// TODO : Set max value according to the stock
			Button validate = new Button("Ajouter");
			validate.setStyleName(ValoTheme.BUTTON_PRIMARY);
			validate.addStyleName("ecomeal-button");
			validate.addClickListener(event -> {
				MainUI ui = (MainUI) navigator.getUI();
				ui.getOrder().addBasket(basket, quantity.getQuantity());
				close();
				Notification notif = new Notification("Le panier a été ajouté à la commande", Notification.Type.HUMANIZED_MESSAGE);
				notif.setPosition(Position.TOP_CENTER);
				notif.setStyleName("basket-add-success");
				notif.setDelayMsec(500);
				notif.show(Page.getCurrent());
			});
			Button cancel = new Button("Annuler", event -> close());
			cancel.setStyleName(ValoTheme.BUTTON_DANGER);
			cancel.addStyleName("ecomeal-button");
			bot = new HorizontalLayout(quantity, validate, cancel);			
		} else {
			Button ok = new Button("OK", event -> close());
			ok.setStyleName(ValoTheme.BUTTON_PRIMARY);
			ok.addStyleName("ecomeal-button");
			bot = new HorizontalLayout(ok);
		}
		VerticalLayout content = new VerticalLayout(top, bot);
		content.setComponentAlignment(bot, Alignment.BOTTOM_CENTER);
		
		setContent(content);
	}
	
	private void setTextAreaSize() {
		Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('v-textarea-ecomeal-text');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	elements[i].style.width='100%';" +
				"	elements[i].style.fontSize='28px';" +
				"}" +
				"elements = document.getElementsByClassName('v-textfield-ecomeal-text');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	elements[i].style.height='100%';" +
				"	elements[i].style.fontSize='28px';" +
				"}"
		);
	}

}
