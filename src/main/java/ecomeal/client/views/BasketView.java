package ecomeal.client.views;

import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.services.BasketService;

public class BasketView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;
	
	private BasketService service = BasketService.getInstance();
	
	/**
	 * Constructor of the Basket View that initialize the page
	 * 
	 * @param navigator Used to navigate between all the Vaadin views
	 */
	public BasketView(Navigator navigator) {
		
		// For the horizontal scrollbar
        setHeight(null);
        setWidth("100%");
        
        Label title = new Label("Voici la Liste des Paniers");

        Button button = new Button("Go to Main View");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        // TODO : switch to all the AVAILABLE baskets
        // Get all the baskets
        List<Basket> baskets = service.findAll();
        
        // Css Layout allow the auto line return for Baskte Image when we resize the window
        CssLayout css = new CssLayout();
        for(Basket basket : baskets) {
        	css.addComponent(new BasketComponent(basket.getImage(), basket.getName()));
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
		
	}
}
