package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PresentationView extends VerticalLayout implements View {

	private static final long serialVersionUID = -2616779115003032214L;

	public PresentationView(Navigator navigator) {
		
        setSizeFull();
        
        Label titre = new Label("EcoMeal, C’EST QUOI ?");
        titre.addStyleName("ecomeal-presentation-title");
        titre.addStyleName("ecomeal-title");
        
        Label texte1 = new Label("C’est un food-truck où vous pouvez manger sainement. Chez EcoMeal, vous êtes libre de choisir votre panier repas et ainsi de composer votre assiette comme vous l’aimez.");
        texte1.addStyleName("ecomeal-text");
        texte1.setResponsive(true);
        texte1.setHeight(null);
        texte1.setWidth("100%");
        Label texte2 = new Label("Le gâchis aujourd’hui c’est fini !");
        texte2.addStyleName("ecomeal-text");
        texte2.setResponsive(true);
        texte2.setHeight(null);
        texte2.setWidth("100%");
        Label texte3 = new Label("Avec EcoMeal, participez à la sauvegarde de la planète tout en mangeant sain pour pas grand chose. Nous plaçons l’écologie et la satisfaction de nos clients au centre de nos préoccupations.");
        texte3.addStyleName("ecomeal-text");
        texte3.setResponsive(true);
        texte3.setHeight(null);
        texte3.setWidth("100%");
        Label welcome = new Label("Bienvenue sur EcoMeal !");
        welcome.addStyleName("ecomeal-text");
        
        setHeight(null);
        setWidth("100%");
        
        addComponents(titre, texte1, texte2, texte3, welcome);
    }

	@Override
    public void enter(ViewChangeEvent event) {
        //NOTHING
    }

}
