package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import ecomeal.client.constants.EcomealConstants;

public class ProductView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;

	public ProductView(Navigator navigator) {
        setSizeFull();
        
        Label title = new Label("Voici la Liste des Produits");

        Button button = new Button("Go to Main View");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        addComponents(button, title);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
