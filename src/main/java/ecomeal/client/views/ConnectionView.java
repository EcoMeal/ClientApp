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
	private Label firstNameLabel;
	private TextField firstName;
	private Label lastNameLabel;
	private TextField lastName;
	private Label loginLabel;
	private TextField login;
	private Label mailLabel;
	private TextField mail;
	private Label passwordLabel;
	private PasswordField password;
	
	private Label loginLabelC;
	private TextField loginC;
	private Label passwordLabelC;
	private PasswordField passwordC;
	
	private Button register;
	private Button connection;
	private Button menuRegister;
	private Button menuConnection;
	private VerticalLayout registerLayout;
	private VerticalLayout connectionLayout;
	
	public ConnectionView(Navigator navigator){
		ui = (MainUI) navigator.getUI();
		service = new ConnectionService(new JsonTool());
		navigator.addViewChangeListener(new ViewChangeListener(){

			private static final long serialVersionUID = -67631789657216405L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				
				
				
				firstNameLabel = new Label("Prénom");
				firstNameLabel.addStyleName("ecomeal-text");
				firstName = new TextField();
				lastNameLabel = new Label("Nom");
				lastNameLabel.addStyleName("ecomeal-text");
				lastName = new TextField();
				loginLabel = new Label("Nom de compte");
				loginLabel.addStyleName("ecomeal-text");
				login = new TextField();
				mailLabel = new Label("Mail");
				mailLabel.addStyleName("ecomeal-text");
				mail = new TextField();
				passwordLabel = new Label("Mot de passe");
				passwordLabel.addStyleName("ecomeal-text");
				password = new PasswordField();
				

				loginLabelC = new Label("Nom de compte");
				loginLabelC.addStyleName("ecomeal-text");
				loginC = new TextField();
				passwordLabelC = new Label("Mot de passe");
				passwordLabelC.addStyleName("ecomeal-text");
				passwordC = new PasswordField();
				
				register = new Button("S'inscrire");
				register.addClickListener(e -> {
					boolean next = service.createUser(ui, login.getValue(), mail.getValue(), password.getValue());
					if(next){
						navigator.navigateTo(EcomealConstants.MAIN_VIEW);
					}else{
						//TODO Popup problem occured
					}
		        });
				connection = new Button("Se connecter");
				connection.addClickListener(e -> {
					boolean next = service.connect(ui, loginC.getValue(), passwordC.getValue());
					if(next){
						navigator.navigateTo(EcomealConstants.MAIN_VIEW);						
					}else{
						//TODO Popup problem occured
					}
		        });
				menuRegister = new Button("Créer un compte");
				menuRegister.addClickListener(e -> {
		        	connectionLayout.setVisible(false);
		        	registerLayout.setVisible(true);
		        });
				menuConnection = new Button("Se connecter");
				menuConnection.addClickListener(e -> {
					connectionLayout.setVisible(true);
		        	registerLayout.setVisible(false);
		        });
				
				registerLayout = new VerticalLayout(firstNameLabel,firstName,lastNameLabel,lastName,loginLabel,login,mailLabel,mail,passwordLabel,password,register);
				connectionLayout = new VerticalLayout(loginLabelC,loginC,passwordLabelC,passwordC,connection);
				connectionLayout.setVisible(true);
	        	registerLayout.setVisible(false);
	        	
	        	HorizontalLayout horizon = new HorizontalLayout(menuRegister, menuConnection);
	        	
	        	VerticalLayout vertical = new VerticalLayout(horizon, registerLayout, connectionLayout);
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
