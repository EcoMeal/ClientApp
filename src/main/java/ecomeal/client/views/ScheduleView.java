package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
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
	private ScheduleService service;
	
	private Label title = new Label();
	
	private Slider to = new Slider(990,1380);
	private Slider from = new Slider(960,1350);
	
	private Label goodHoraire = new Label();
	private Button valideHoraire;
	
	private Button returnButton;
	private Button valideCommand = new Button("Validez la commande");
	
	private long deliveryTime;
	
	/**
	 * Constructeur de HoraireView
	 * @param navigator
	 */
	public ScheduleView(Navigator navigator) {
		
		navigator.addViewChangeListener(new ViewChangeListener(){

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				from.setValue((double) 960);
				to.setMin(990);
				to.setValue((double) 990);
				goodHoraire.setVisible(false);
				valideCommand.setVisible(false);
				return true;
			}
			
		});
		
		goodHoraire.addStyleName("ecomeal-text");
		
		service= new ScheduleService(new JsonTool());
		MainUI ui = (MainUI) navigator.getUI();
		
        setSizeFull();
        VerticalLayout main = new VerticalLayout();
        
        HorizontalLayout sliders = new HorizontalLayout();
        HorizontalLayout titleLayout = new HorizontalLayout();
        VerticalLayout horaireButton = new VerticalLayout();
        
        title = new Label("Selectionnez la tranche horaire souhaitée.");
        title.addStyleName("ecomeal-title");
        titleLayout.addComponents(title);
        
        // Création des Sliders
        from.setDescription(from.getCaption());
        
        from.setCaption("Début:" + service.transformToHour(from.getValue()));
        from.setOrientation(SliderOrientation.HORIZONTAL);
        from.setSizeFull();
        from.addValueChangeListener(e -> {
        	
        	this.to.setMin(e.getValue().intValue() + 30);
        	from.setCaption("Début:" + service.transformToHour(from.getValue()));
        });
        
        
        to.setCaption("Fin:" + service.transformToHour(to.getValue()));
        to.setOrientation(SliderOrientation.HORIZONTAL);
        to.setSizeFull();
        to.addValueChangeListener(e -> {
        	to.setCaption("Fin:" + service.transformToHour(to.getValue()));
        });
        
        sliders.addComponents(from, to);
        sliders.setSizeFull();
        
        // Création de la session de validation de l'horaire 
        valideHoraire = new Button("Trouver une horaire disponible");
        valideHoraire.addStyleName("ecomeal-button");
        valideHoraire.addClickListener(e -> {
        	goodHoraire.setVisible(true);
        	goodHoraire.setValue(service.ScheduleToString(deliveryTime = service.findAGoodSchedule(valideCommand, from, to)));
        });
        goodHoraire.setVisible(false);
        horaireButton.addComponents(valideHoraire, goodHoraire);
        
        // Boutton de Validation
        valideCommand.addStyleName("ecomeal-button");
        valideCommand.setVisible(false);
        valideCommand.addClickListener(e -> {
        	service.validateOrder(ui.getOrder(), deliveryTime);
        	navigator.navigateTo(EcomealConstants.RECAP_VIEW);
        	ui.getOrder().clearOrder();
        });
        
        // Boutton de retour
        returnButton = new Button("Revenir au menu");
        returnButton.addStyleName("ecomeal-button");
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
