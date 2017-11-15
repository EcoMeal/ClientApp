package ecomeal.client.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstant;

@DesignRoot
public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -9172606135381422482L;

	public MainView(Navigator navigator) {
        setSizeFull();
        
        Label titre = new Label("Bienvenue sur Ecomeal");
        
        HorizontalLayout buttons = new HorizontalLayout();

        Button basketButton = new Button("Liste des Paniers");
        basketButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstant.BASKET_VIEW);
        });
        
        Button productButton = new Button("Liste des Produits");
        productButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstant.PRODUCT_VIEW);
        });
        
        buttons.addComponents(basketButton, productButton);
        addComponents(titre, buttons);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

    
}