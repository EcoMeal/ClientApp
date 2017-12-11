package ecomeal.client.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.ui.MainUI;

/**
 * The Home Page of the application
 */
@DesignRoot
public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -9172606135381422482L;

	public MainView(Navigator navigator) {
		
        setSizeFull();
        
        Label titre = new Label("Bienvenue sur Ecomeal");
        
        HorizontalLayout buttons = new HorizontalLayout();

        Button basketButton = new Button("Liste des Paniers");
        basketButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.BASKET_VIEW);
        });
        
        buttons.addComponents(basketButton);
        
        Button basketCategoryButton = new Button("Liste des categories de panier");
        basketCategoryButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.BASKET_CATEGORY_VIEW);
        });
        
        buttons.addComponents(basketCategoryButton);
        
        addComponents(titre, buttons);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

    
}