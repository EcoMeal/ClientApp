package ecomeal.client.views;

import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketCategoryComponent;
import ecomeal.client.entity.BasketCategory;
import ecomeal.client.services.BasketCategoryService;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.ui.MainUI;

public class BasketCategoryView extends HorizontalLayout implements View {

	private static final long serialVersionUID = -5103570575946626930L;

	private BasketCategoryService service;
	private MainUI ui;
	private List<BasketCategory> basketCategories;
	private CssLayout css;
	
	/**
	 * Constructor of the Basket Category View that initialize the page
	 * 
	 * @param navigator Used to navigate between all the Vaadin views
	 */
	public BasketCategoryView(Navigator navigator) {
		ui = (MainUI) navigator.getUI();
		service = new BasketCategoryService(new JsonTool());
		
		navigator.addViewChangeListener(new ViewChangeListener() {
			
			private static final long serialVersionUID = -7219053322465512034L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				if(!ui.getUser().getToken().equals("")) {
					// For the vertical scrollbar
			        setHeight(null);
			        setWidth("100%");
			        
			        removeAllComponents();
					
					// Get all the basket categories
			        if(!ui.getUser().getToken().equals("")){
			        	basketCategories = service.findAll(ui.getUser().getToken());
			        }
			        css = new CssLayout();
			        for(BasketCategory basketCategory : basketCategories) {
			        	css.addComponent(new BasketCategoryComponent(navigator, basketCategory));
			        }
			        
			        VerticalLayout vertical = new VerticalLayout(css);
			        vertical.setResponsive(true);
			        
			        addComponents(vertical);
					return true;
				}
				return true;
			}
			
		});
        
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
