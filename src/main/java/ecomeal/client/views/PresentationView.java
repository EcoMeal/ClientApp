package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PresentationView extends VerticalLayout implements View {
	
public PresentationView(Navigator navigator) {
		
        setSizeFull();
        
        Label titre = new Label("Bienvenue sur Ecomeal");
        titre.addStyleName("ecomeal-title");
        
        Label texteChiant = new Label("Ouais, ouais énorme");
        addComponents(titre, texteChiant);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

}
