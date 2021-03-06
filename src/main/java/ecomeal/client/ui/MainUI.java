package ecomeal.client.ui;

import java.io.File;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import ecomeal.client.components.EcomealMenuLayout;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.entity.User;
import ecomeal.client.views.*;

@Theme("mytheme")
public class MainUI extends UI {

	private static final long serialVersionUID = 2259839686859669777L;
	
	private EcomealMenuLayout layout = new EcomealMenuLayout();
	private ComponentContainer layoutContentArea = layout.getContentArea();

	private Navigator navigator;
	private Order order;
	private User user;
	
	private Button presentationButton;
	private Button homeButton;
	private Button connectionButton;
	private Button createAccountButton;
	private Button orderButton;
	private Button disconnectionButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("EcoMeal");
    	setTheme("mytheme");
    	resetMenuLayout(true);
//    	layout.setHeight(null);
//        layout.setWidth("100%");
//    	layout.addMenu(buildMenu());
    	addStyleName(ValoTheme.UI_WITH_MENU);
    	
    	// Initialize the order with an empty List of Basket
    	order = new Order();
    	
    	//Initialize user
    	user = new User();
    	
    	// Create a navigator to control the views
        navigator = new Navigator(this, layoutContentArea);

        // Create and register the views
        navigator.addView(EcomealConstants.BASKET_CATEGORY_VIEW, new BasketCategoryView(navigator));
        navigator.addView(EcomealConstants.PRESENTATION_VIEW,  new PresentationView(navigator));
        navigator.addView(EcomealConstants.MAIN_VIEW, new MainView(navigator));
        navigator.addView(EcomealConstants.CONNECTION_VIEW, new ConnectionView(navigator));
        navigator.addView(EcomealConstants.CREATE_ACCOUNT_VIEW, new CreateAccountView(navigator));
        navigator.addView(EcomealConstants.HORAIRE_VIEW, new ScheduleView(navigator));
        navigator.addView(EcomealConstants.RECAP_VIEW, new RecapView(navigator));
        
        setMenuTextSize();
        
    }
    
    private void resetMenuLayout(final boolean connected) {
    	layout = new EcomealMenuLayout();
    	layoutContentArea = layout.getContentArea();
    	setContent(layout);
    	layout.setHeight(null);
        layout.setWidth("100%");
    	layout.addMenu(buildMenu(connected));
    }
    
    public void showButtons(final boolean connected) {
    	presentationButton.setVisible(!connected);
    	homeButton.setVisible(connected);
    	connectionButton.setVisible(!connected);
    	createAccountButton.setVisible(!connected);
    	orderButton.setVisible(connected);
    	disconnectionButton.setVisible(connected);
    	setMenuTextSize();
    }
    
    private CssLayout buildMenu(final boolean connected) {
    	CssLayout menu = new CssLayout();
    	HorizontalLayout menuItemsLayout = new HorizontalLayout();
    	menuItemsLayout.setPrimaryStyleName("valo-menuitems");
    	menu.addComponent(menuItemsLayout);
    	Image logoIcon = new Image();
    	try {
    		File f = new File("src/main/resources/logoIcon.png");
    		FileUtils.copyURLToFile(new URL("http://vps434333.ovh.net/images/logo.png"), f);
    		logoIcon.setSource(new FileResource(f));
    		logoIcon.setHeight(60, UNITS_PIXELS);
    		logoIcon.setWidth(100, UNITS_PIXELS);
    	} catch (Exception e) {
    		System.err.println("Icon image not found :" + e.getStackTrace());
    	}
    	if(connected) {
    		presentationButton = new Button("Accueil");
    		presentationButton.addClickListener(e -> {
    			navigator.navigateTo(EcomealConstants.PRESENTATION_VIEW);
    		});
    		presentationButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		homeButton = new Button("Accueil");
    		homeButton.addClickListener(e -> {
    			navigator.navigateTo(EcomealConstants.MAIN_VIEW);
    		});
    		homeButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		homeButton.setVisible(false);
    		connectionButton = new Button("Connexion");
    		connectionButton.addClickListener(e -> {
    			navigator.navigateTo(EcomealConstants.CONNECTION_VIEW);
    		});
    		connectionButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		createAccountButton = new Button("Créer un compte");
    		createAccountButton.addClickListener(e -> {
    			navigator.navigateTo(EcomealConstants.CREATE_ACCOUNT_VIEW);
    		});
    		createAccountButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		orderButton = new Button("Commande");
    		orderButton.addClickListener(e -> {
    			addWindow(new OrderPopin(navigator, this));
    		});
    		orderButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		orderButton.setVisible(false);
    		disconnectionButton = new Button("Déconnexion");
    		disconnectionButton.addClickListener(e -> {
    			showButtons(false);
    			user = new User();
    			order.clearOrder();
    			navigator.navigateTo(EcomealConstants.CONNECTION_VIEW);
    		});
    		disconnectionButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
    		disconnectionButton.setVisible(false);
    		menuItemsLayout.addStyleName("ecomeal-menu");
    		menuItemsLayout.addComponents(logoIcon, presentationButton, homeButton, connectionButton, createAccountButton, orderButton, disconnectionButton);
    	}
    	
    	else {
    		menuItemsLayout.addComponents(logoIcon);
    	}
    	return menu;
    }
    
    public Order getOrder() {
    	return order;
    }
    
    public void addBasket(Basket basket) {
    	order.addBasket(basket);
    }
    
    private void setMenuTextSize() {
    	Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('ecomeal-menu');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	for (var j = 2; j < elements[i].childNodes.length; j = j + 2) {" +
				"		elements[i].childNodes[j].childNodes[0].childNodes[0].style.fontSize='34px';" +
				"		elements[i].childNodes[j].childNodes[0].childNodes[0].childNodes[0].style.width='100%';" +
				"	}" +
				"	elements[i].parentElement.style.paddingBottom='15px';" +
				"	elements[i].parentElement.style.paddingTop='15px';" +
				"}"
		);
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@WebServlet(urlPatterns = "/*", name = "MyMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyMainServlet extends VaadinServlet {

		private static final long serialVersionUID = 8807776865878950610L;
    }
    
}