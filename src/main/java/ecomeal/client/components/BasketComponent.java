package ecomeal.client.components;

import java.io.File;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;

public class BasketComponent extends CustomComponent {
	
	private static final long serialVersionUID = -2509219462088694581L;
	
	private VerticalLayout vertical;
	
	private Image image;
	private Label title;
	public static final String javaScriptClassName = "basket-component";
	
	public BasketComponent(File image, String title, Navigator navigator) {
		this.image = new Image();
		this.image.setSource(new FileResource(image));
		this.image.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.HORAIRE_VIEW);
        });
		this.title = new Label(title);
		this.image.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.HORAIRE_VIEW);
        });
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
