package ecomeal.client.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.PresetBasket;
import ecomeal.client.views.PresetBasketPopin;

/**
 * Vaadin component that represents each Box to consult the details of a Basket
 */
public class BasketComponent extends CustomComponent {
	
	private static final long serialVersionUID = -2509219462088694581L;
	
	private VerticalLayout vertical;
	
	private Image image;
	private Label title;
	public static final String javaScriptClassName = "basket-component";
	
	public BasketComponent(Navigator navigator, Basket basket, boolean ordering) {
		this.image = new Image();
		this.image.setSource(new FileResource(basket.getImage()));
		this.image.setHeight(250, Unit.PIXELS);
		this.image.setWidth(250, Unit.PIXELS);
		if(basket instanceof PresetBasket) {			
			this.image.addClickListener(e -> {
				navigator.getUI().addWindow(new PresetBasketPopin(navigator, (PresetBasket) basket, ordering));
			});
		}
		this.title = new Label(basket.getName());
		this.title.setWidth(null);
		this.title.setStyleName("ecomeal-basket-component-title");
		init();
		setSizeUndefined();
		setCompositionRoot(vertical);
	}
	
	public void init() {
		this.vertical = new VerticalLayout();
		this.vertical.addComponents(image, title);
		this.vertical.addStyleName(javaScriptClassName);
		this.vertical.setComponentAlignment(title, Alignment.BOTTOM_CENTER);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
	}

}
