package ecomeal.client.components;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BasketComponent extends CustomComponent {
	
	private static final long serialVersionUID = -2509219462088694581L;
	
	private VerticalLayout vertical;
	
	private Image image;
	private Label title;
	
	public BasketComponent(File image, String title) {
		this.image = new Image();
		this.image.setSource(new FileResource(image));
		this.title = new Label(title);
		init();
		setSizeUndefined();
		setCompositionRoot(vertical);
	}
	
	public void init() {
		this.vertical = new VerticalLayout();
		this.vertical.addComponents(image, title);
		this.vertical.addStyleName("basket-component-background");
		JavaScript.getCurrent().execute("$('.basket-component-background').css('background-color', 'grey')");
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
