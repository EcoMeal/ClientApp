package ecomeal.client.views;

import java.sql.Timestamp;

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
import ecomeal.client.entity.Order;
import ecomeal.client.services.BasketService;
import ecomeal.client.tools.JsonTool;

public class RecapView extends HorizontalLayout implements View{
	
	private static final long serialVersionUID = 5420198751158088076L;
	
	private Label title;
	private Button returnButton;

	public RecapView(Navigator navigator){
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
        
        BasketService testService = new BasketService(new JsonTool());
        
        Order test = new Order();
        test.setOrderTime(new Timestamp(2017,12,5,17,50,0,0));
        test.setDeliveryTime(new Timestamp(2017,12,5,19,30,0,0));
        test.setId(245);
        test.addBasket(testService.findAll().get(0));
        test.addBasket(testService.findAll().get(1));
        test.addBasket(testService.findAll().get(0));
        
        Label title = new Label("Recapitulatif de la Commande fait ce jour à " + test.getOrderTime().getHours() + "h" + test.getOrderTime().getMinutes());
        
        Label FirstPhrase = new Label("Voici la liste des paniers :");
        
        CssLayout css = new CssLayout();
        
        for(Basket basket : test.getBaskets().keySet()) {
        	for(int i = 0 ; i < test.getBaskets().get(basket); i++){
        		css.addComponent(new BasketComponent(navigator, basket, false));
        	}
        }
        
        Label SecondPhrase = new Label("Pour un prix de : " + test.getPrice() + "€");
        
        Label ThirdPhrase = new Label("Le numéro de commande est le : " + test.getId());
        
        Label FourthPhrase = new Label("Votre commande sera délivré à " + test.getDeliveryTime().getHours() + "h" + test.getDeliveryTime().getMinutes());
        
        // Boutton de retour
        returnButton = new Button("Revenir au menu");
        returnButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        VerticalLayout vertical = new VerticalLayout(title,FirstPhrase, css, SecondPhrase, ThirdPhrase, FourthPhrase, returnButton);
        vertical.setResponsive(true);
        
        addComponents(vertical);
	}

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
