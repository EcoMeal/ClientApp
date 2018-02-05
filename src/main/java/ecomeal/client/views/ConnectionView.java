package ecomeal.client.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.User;
import ecomeal.client.services.ConnectionService;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.ui.MainUI;

public class ConnectionView extends HorizontalLayout implements View{
	
	private static final long serialVersionUID = -1669552651071399728L;
	
	private ConnectionService service;
	
	private MainUI ui;
	
	private Label loginLabel;
	private TextField login;
	private Label passwordLabel;
	private PasswordField password;
	
	private Button connection;
	
	public ConnectionView(Navigator navigator){
		ui = (MainUI) navigator.getUI();
		service = new ConnectionService(new JsonTool());
		navigator.addViewChangeListener(new ViewChangeListener(){

			private static final long serialVersionUID = -67631789657216405L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				
				loginLabel = new Label("Nom de compte");
				loginLabel.addStyleName("ecomeal-text");
				login = new TextField();
				passwordLabel = new Label("Mot de passe");
				passwordLabel.addStyleName("ecomeal-text");
				password = new PasswordField();
				
				connection = new Button("Se connecter");
				connection.addClickListener(e -> {
					boolean next = service.connect(ui, login.getValue(), password.getValue());
					if(next){
						navigator.navigateTo(EcomealConstants.MAIN_VIEW);						
					}else{
						//TODO Popup problem occured
					}
		        });
	        	
	        	VerticalLayout vertical = new VerticalLayout(loginLabel, login, passwordLabel, password, connection);
	        	vertical.setResponsive(true);
		        removeAllComponents();
		        addComponents(vertical);
				
				return true;
			}
			
		});
		
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
