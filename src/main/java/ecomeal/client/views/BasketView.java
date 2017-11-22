package ecomeal.client.views;

import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.services.BasketService;

public class BasketView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;
	
	private BasketService service = BasketService.getInstance();
	
	public BasketView(Navigator navigator) {
        setSizeFull();
        
        Label title = new Label("Voici la Liste des Paniers");

        Button button = new Button("Go to Main View");
        button.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        List<Basket> baskets = service.findAll();
        
        CssLayout css = new CssLayout();
        for(Basket basket : baskets) {
        	css.addComponent(new BasketComponent(basket.getImage(), basket.getName()));
        	//css.addComponent(new Image(basket.getName(), new FileResource(basket.getImage())));
        }
        
        VerticalLayout vertical = new VerticalLayout(title, css, button);
        vertical.setResponsive(true);
        
        addComponents(vertical);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
