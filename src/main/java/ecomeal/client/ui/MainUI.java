package ecomeal.client.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Order;
import ecomeal.client.views.*;

@Theme("mytheme")
public class MainUI extends UI {

	private static final long serialVersionUID = 2259839686859669777L;
	private Navigator navigator;
	
	private Order order;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("EcoMeal");
    	setTheme("mytheme");
    	
    	// Initialize the order with an empty List of Basket
    	order = new Order();
    	
    	// Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(EcomealConstants.BASKET_VIEW, new BasketView(navigator, this));
        navigator.addView(EcomealConstants.MAIN_VIEW, new MainView(navigator, this));
        navigator.addView(EcomealConstants.HORAIRE_VIEW, new HoraireView(navigator, this));
        
        
    }
    
    public Order getOrder() {
    	return order;
    }

    @WebServlet(urlPatterns = "/*", name = "MyMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyMainServlet extends VaadinServlet {

		private static final long serialVersionUID = 8807776865878950610L;
    }
    
}
