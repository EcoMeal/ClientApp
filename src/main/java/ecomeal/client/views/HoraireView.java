package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;

public class HoraireView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;
	private Label title = new Label();
	
	private Slider to;
	private Slider from;
	
	private Label goodHoraire = new Label();
	private Button valideHoraire;
	
	private Button returnButton;
	private Button valideCommand;
	

	public HoraireView(Navigator navigator) {
        setSizeFull();
        VerticalLayout main = new VerticalLayout();
        
        HorizontalLayout sliders = new HorizontalLayout();
        HorizontalLayout titleLayout = new HorizontalLayout();
        VerticalLayout horaireButton = new VerticalLayout();
        
        title = new Label("Selectionnez la tranche horaire souhaitée.");
        titleLayout.addComponents(title);
        
        // Création des Sliders
        to = new Slider(960,1350);
        to.setDescription(to.getCaption());
        
        to.setCaption("Début:" + transformToHour(to.getValue()));
        to.setOrientation(SliderOrientation.HORIZONTAL);
        to.setSizeFull();
        to.addValueChangeListener(e -> {
        	
        	this.from.setMin(e.getValue().intValue() + 30);
        	this.from.setEnabled(true);
        	to.setCaption("Début:" + transformToHour(to.getValue()));
        });
        
        
        from = new Slider(990, 1380);
        from.setCaption("Fin:" + transformToHour(from.getValue()));
        from.setOrientation(SliderOrientation.HORIZONTAL);
        from.setEnabled(false);
        from.setSizeFull();
        from.addValueChangeListener(e -> {
        	from.setCaption("Fin:" + transformToHour(from.getValue()));
        });
        
        sliders.addComponents(to, from);
        sliders.setSizeFull();
        
        // Création de la session de validation de l'horaire 
        valideHoraire = new Button("Validez l'horaire");
        valideHoraire.addClickListener(e -> {
        	goodHoraire.setVisible(true);
        	goodHoraire.setValue(findAGoodHoraire());
        });
        goodHoraire.setVisible(false);
        horaireButton.addComponents(valideHoraire, goodHoraire);
        
        // Boutton de Validation
        valideCommand = new Button("Validez la commande");
        valideCommand.setVisible(false);
        valideCommand.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        // Boutton de retour
        returnButton = new Button("Revenir au menu");
        returnButton.addClickListener(e -> {
        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
        });
        
        main.addComponents(titleLayout, sliders, horaireButton, valideCommand, returnButton);
        addComponents(main);
    }
	/**
	 * Methode permettant de retourner une horraire valide via un appel à l'API
	 * @return
	 */
	private String findAGoodHoraire() {
		/*
		 *  Demande à l'URL de retourné une bonne horaire
		 */
		if(to.getValue() == 960){
			valideCommand.setVisible(false);
			return "Il n'y a pas d'horaire valide";
		}else{
			valideCommand.setVisible(true);
			return "Horaire selectionnée :" + transformToHour(to.getValue() + (Math.random()*30));
		}
	}

	/**
	 * Transforme une valeur double représentant le nombre de minutes en chaine de caractères XXhYY
	 * @param Dvalue le nombre de minutes dans une journée
	 * @return
	 */
	private String transformToHour(double Dvalue) {
		int value = (int) Dvalue;
		String hour = (value / 60) + "h";
		String minute;
		if(value % 60 < 10){
			minute = "0" + (value % 60);
		}
		else{
			minute = "" + (value % 60);
		}
		
		return hour + minute;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
