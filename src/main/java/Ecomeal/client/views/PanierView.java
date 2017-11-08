package Ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import Ecomeal.client.constants.EcomealConstant;

public class PanierView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;

	public PanierView(Navigator navigator) {
        setSizeFull();
        
        Label titre = new Label("Voici la Liste des Paniers");

        Button button = new Button("Go to Main View");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstant.MAINVIEW);
        });
        addComponents(button, titre);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
