package ecomeal.client.views;

import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.services.BasketService;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.ui.MainUI;

public class BasketView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;
	
	private BasketService service = new BasketService(new JsonTool());
	
	private final MainUI ui;
	
	/**
	 * Constructor of the Basket View that initialize the page
	 * 
	 * @param navigator Used to navigate between all the Vaadin views
	 */
	public BasketView(final Navigator navigator, final MainUI ui) {
		
		this.ui = ui;
		
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
        
        Label title = new Label("Voici la Liste des Paniers");

        Button button = new Button("Retour");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        // TODO : switch to all the AVAILABLE baskets
        // Get all the baskets
        List<Basket> baskets = service.findAll();
        
        // Css Layout allow the auto line return for Basket Image when we resize the window
        CssLayout css = new CssLayout();
        for(Basket basket : baskets) {
        	css.addComponent(new BasketComponent(basket.getImage(), basket.getName(), navigator));
        }
        
        
        VerticalLayout vertical = new VerticalLayout(title, css, button);
        vertical.setResponsive(true);
        
        addComponents(vertical);
        
    }

	/**
	 * Code executed when we enter the view
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		// Set background color for Basket Components
		Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('" + BasketComponent.javaScriptClassName + "');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	for (var j = 0; j < elements[i].childNodes.length; j++) {" +
				"		if(elements[i].childNodes[j].classList.contains('v-slot')) {" +
				"			elements[i].childNodes[j].style.backgroundColor='#F5F5F5';" +
				"			elements[i].childNodes[j].style.border='1px solid #ddd';" +
				"		}" +
				"		else if(elements[i].childNodes[j].classList.contains('v-spacing')) {" +
				"			elements[i].childNodes[j].style.width='150px';" +
				"			elements[i].childNodes[j].style.height='0px';" +
				"			elements[i].childNodes[j].style.backgroundColor='#B0B0B0';" +
				"		}" +
				"	}" +
				"}"
		);		
	}
}
