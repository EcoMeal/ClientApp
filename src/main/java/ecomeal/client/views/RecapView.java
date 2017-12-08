package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.ui.MainUI;

public class RecapView extends HorizontalLayout implements View{
	
	private static final long serialVersionUID = 5420198751158088076L;
	private final MainUI ui;
	
	private Label title;
	private Button returnButton;

	public RecapView(Navigator navigator, MainUI ui){
		this.ui = ui;
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
        
        Label title = new Label("Recapitulatif de la Commande fait ce jour à " + ui.getOrder().getOrderTime());
        
        TextField FirstPhrase = new TextField("Voici la liste des paniers :");
        
        CssLayout css = new CssLayout();
        
        for(Basket basket : ui.getOrder().getBaskets().keySet()) {
        	for(int i = 0 ; i < ui.getOrder().getBaskets().get(basket); i++){
        		css.addComponent(new BasketComponent(basket.getImage(), basket.getName(), navigator));
        	}
        }
        
        TextField SecondPhrase = new TextField("Pour un prix de : " + ui.getOrder().getPrice() + "€");
        
        TextField ThirdPhrase = new TextField("Le numéro de commande est le : " + ui.getOrder().getId());
        
        TextField FourthPhrase = new TextField("Votre commande sera délivré à " + ui.getOrder().getDeliveryTime());
        
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
