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
import ecomeal.client.services.ScheduleService;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.ui.MainUI;

/**
 * The Schedule Page of the application
 */
public class ScheduleView extends HorizontalLayout implements View {
	
	private static final long serialVersionUID = -419142715000622537L;
	private final MainUI ui;
	private ScheduleService service;
	
	private Label title = new Label();
	
	private Slider to;
	private Slider from;
	
	private Label goodHoraire = new Label();
	private Button valideHoraire;
	
	private Button returnButton;
	private Button valideCommand;
	
	/**
	 * Constructeur de HoraireView
	 * @param navigator
	 */
	public ScheduleView(Navigator navigator, MainUI ui) {
		this.ui = ui;
		service= new ScheduleService(new JsonTool());
		
        setSizeFull();
        VerticalLayout main = new VerticalLayout();
        
        HorizontalLayout sliders = new HorizontalLayout();
        HorizontalLayout titleLayout = new HorizontalLayout();
        VerticalLayout horaireButton = new VerticalLayout();
        
        title = new Label("Selectionnez la tranche horaire souhaitée.");
        titleLayout.addComponents(title);
        
        // Création des Sliders
        from = new Slider(960,1350);
        from.setDescription(from.getCaption());
        
        from.setCaption("Début:" + service.transformToHour(from.getValue()));
        from.setOrientation(SliderOrientation.HORIZONTAL);
        from.setSizeFull();
        from.addValueChangeListener(e -> {
        	
        	this.to.setMin(e.getValue().intValue() + 30);
        	from.setCaption("Début:" + service.transformToHour(from.getValue()));
        });
        
        
        to = new Slider(990, 1380);
        to.setCaption("Fin:" + service.transformToHour(to.getValue()));
        to.setOrientation(SliderOrientation.HORIZONTAL);
        to.setSizeFull();
        to.addValueChangeListener(e -> {
        	to.setCaption("Fin:" + service.transformToHour(to.getValue()));
        });
        
        sliders.addComponents(from, to);
        sliders.setSizeFull();
        
        // Création de la session de validation de l'horaire 
        valideHoraire = new Button("Validez l'horaire");
        valideHoraire.addClickListener(e -> {
        	goodHoraire.setVisible(true);
        	goodHoraire.setValue(service.findAGoodSchedule(valideCommand, from, to));
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

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private void reset(){
		
	}
}
