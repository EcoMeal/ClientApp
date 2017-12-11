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

import ecomeal.client.components.BasketCategoryComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.BasketCategory;
import ecomeal.client.services.BasketCategoryService;
import ecomeal.client.tools.JsonTool;

public class BasketCategoryView extends HorizontalLayout implements View {

	private static final long serialVersionUID = -5103570575946626930L;

	private BasketCategoryService service;
	
	/**
	 * Constructor of the Basket Category View that initialize the page
	 * 
	 * @param navigator Used to navigate between all the Vaadin views
	 */
	public BasketCategoryView(Navigator navigator) {
		service = new BasketCategoryService(new JsonTool());
		
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
        
        Label title = new Label("Paniers disponibles");

        Button button = new Button("Retour");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        // Get all the basket categories
        List<BasketCategory> basketCategories = service.findAll();
        
        // Css Layout allow the auto line return for BasketCategory Image when we resize the window
        CssLayout css = new CssLayout();
        for(BasketCategory basketCategory : basketCategories) {
        	css.addComponent(new BasketCategoryComponent(navigator, basketCategory, true));
        }
        
        
        VerticalLayout vertical = new VerticalLayout(title, css, button);
        vertical.setResponsive(true);
        
        addComponents(vertical);
        
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// Set background color for Basket Components
		Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('" + BasketCategoryComponent.javaScriptClassName + "');" +
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
