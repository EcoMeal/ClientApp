package ecomeal.client.ui;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import ecomeal.client.components.EcomealMenuLayout;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.views.*;

@Theme("mytheme")
public class MainUI extends UI {

	private static final long serialVersionUID = 2259839686859669777L;
	
	private final EcomealMenuLayout layout = new EcomealMenuLayout();
	private ComponentContainer layoutContentArea = layout.getContentArea();

	private Navigator navigator;
	private Order order;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("EcoMeal");
    	setTheme("mytheme");
    	setContent(layout);
    	layout.addMenu(buildMenu());
    	addStyleName(ValoTheme.UI_WITH_MENU);
    	
    	// Initialize the order with an empty List of Basket
    	order = new Order();
    	
    	// Create a navigator to control the views
        navigator = new Navigator(this, layoutContentArea);

        // Create and register the views
        navigator.addView(EcomealConstants.BASKET_VIEW, new BasketView(navigator));
        navigator.addView(EcomealConstants.MAIN_VIEW, new MainView(navigator));
        navigator.addView(EcomealConstants.HORAIRE_VIEW, new ScheduleView(navigator));
        navigator.addView(EcomealConstants.RECAP_VIEW, new RecapView(navigator));
        
        
    }
    
    private CssLayout buildMenu() {
    	CssLayout menu = new CssLayout();
    	CssLayout menuItemsLayout = new CssLayout();
    	menuItemsLayout.setPrimaryStyleName("valo-menuitems");
    	menu.addComponent(menuItemsLayout);
    	Button button = new Button("Commande");
    	button.addClickListener(e -> {
    		// Do something
    	});
    	button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    	menuItemsLayout.addComponent(button);
    	return menu;
    }
    
    public Order getOrder() {
    	return order;
    }
    
    public void addBasket(Basket basket) {
    	order.addBasket(basket);
    }

    @WebServlet(urlPatterns = "/*", name = "MyMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyMainServlet extends VaadinServlet {

		private static final long serialVersionUID = 8807776865878950610L;
    }
    
}