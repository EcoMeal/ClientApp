package ecomeal.client.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.BasketCategory;
import ecomeal.client.views.BasketView;

public class BasketCategoryComponent extends CustomComponent {

	private static final long serialVersionUID = -574482265622816609L;

	private VerticalLayout vertical;
	
	private Image image;
	private Label title;
	public static final String javaScriptClassName = "basket-component";
	
	public BasketCategoryComponent(Navigator navigator, BasketCategory basketCategory) {
		this.image = new Image();
		this.image.setSource(new FileResource(basketCategory.getImage()));
		this.image.setHeight(250, Unit.PIXELS);
		this.image.setWidth(250, Unit.PIXELS);			
		this.image.addClickListener(e -> {
			navigator.addView(EcomealConstants.BASKET_VIEW, new BasketView(navigator, basketCategory.getId()));
			navigator.navigateTo(EcomealConstants.BASKET_VIEW);
		});
		this.title = new Label(basketCategory.getName());
		this.title.setStyleName("basket-component-title");
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
