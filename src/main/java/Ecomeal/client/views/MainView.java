package Ecomeal.client.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import Ecomeal.client.constants.EcomealConstant;

@DesignRoot
public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -9172606135381422482L;

	public MainView(Navigator navigator) {
        setSizeFull();
        
        Label titre = new Label("Bienvenue sur Ecomeal");
        
        HorizontalLayout buttons = new HorizontalLayout();

        Button buttonPanier = new Button("Liste des Paniers");
        buttonPanier.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstant.PANIERVIEW);
        });
        
        Button buttonIngredient = new Button("Liste des Ingrédients");
        buttonIngredient.addClickListener(e -> {
        	Notification.show("Pas encore de vue");
        });
        
        buttons.addComponents(buttonPanier, buttonIngredient);
        addComponents(titre, buttons);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

    
}