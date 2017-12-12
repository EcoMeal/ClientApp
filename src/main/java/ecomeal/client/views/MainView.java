package ecomeal.client.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;

/**
 * The Home Page of the application
 */
@DesignRoot
public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -9172606135381422482L;

	public MainView(Navigator navigator) {
		
        setSizeFull();
        
        Label titre = new Label("Bienvenue sur Ecomeal");
        titre.addStyleName("ecomeal-title");
        
        BasketCategoryView view = new BasketCategoryView(navigator);
        addComponents(titre, view);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

    
}