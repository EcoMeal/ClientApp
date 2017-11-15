package ecomeal.client.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.views.MainView;
import ecomeal.client.views.BasketView;

@Theme("mytheme")
public class MainUI extends UI {

	private static final long serialVersionUID = 2259839686859669777L;
	Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("EcoMeal");
    	
    	// Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(EcomealConstants.BASKET_VIEW, new BasketView(navigator));
        navigator.addView(EcomealConstants.MAIN_VIEW, new MainView(navigator));
        
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyMainServlet extends VaadinServlet {

		private static final long serialVersionUID = 8807776865878950610L;
    }
}
