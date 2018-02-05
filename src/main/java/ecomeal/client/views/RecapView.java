package ecomeal.client.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ecomeal.client.components.BasketComponent;
import ecomeal.client.constants.EcomealConstants;
import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.services.ScheduleService;
import ecomeal.client.tools.JsonTool;
import ecomeal.client.tools.QRCodeEncoder;
import ecomeal.client.ui.MainUI;

public class RecapView extends HorizontalLayout implements View{
	
	private static final long serialVersionUID = 5420198751158088076L;
	
	private ScheduleService scheduleService = new ScheduleService(new JsonTool());
	
	private Label title;
	private Button returnButton;
	private Label FirstPhrase;
	private Label SecondPhrase;
	private Label ThirdPhrase;
	private Label FourthPhrase;
	
	private CssLayout css; 
	
	private MainUI ui;
	private String deliveryTime = "00h00";
	private String orderTime = "00h00";
	private int id = 0;
	private int price = 0;
	private Map<Basket,Integer> baskets = new HashMap<Basket,Integer>();
	private Order myOrder;

	public RecapView(Navigator navigator){
		ui = (MainUI) navigator.getUI();
		navigator.addViewChangeListener(new ViewChangeListener(){

			private static final long serialVersionUID = -67631749657216405L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				myOrder = ui.getOrder();
				deliveryTime = scheduleService.transformToHour(
						((myOrder.getDeliveryTime() % (60*60*24)) - (myOrder.getDeliveryTime() % 60)) / 60
						);
				orderTime = scheduleService.transformToHour(
						((myOrder.getOrderTime() % (60*60*24)) - (myOrder.getOrderTime() % 60)) / 60
						);
				id = myOrder.getId();
				price = myOrder.getPrice();
				baskets = myOrder.getBaskets();
				
				title = new Label("Recapitulatif de la Commande fait ce jour à " + orderTime);
				title.addStyleName("ecomeal-text");
		        
		        FirstPhrase = new Label("Voici la liste des paniers :");
		        FirstPhrase.addStyleName("ecomeal-text");
		        
		        css = new CssLayout();
		        
		        for(Basket basket : baskets.keySet()) {
		        	for(int i = 0 ; i < baskets.get(basket); i++){
		        		css.addComponent(new BasketComponent(navigator, basket, false));
		        	}
		        }
		        
		        SecondPhrase = new Label("Pour un prix de : " + price + "€");
		        SecondPhrase.addStyleName("ecomeal-text");
		        
		        ThirdPhrase = new Label("Le numéro de commande est le : " + id);
		        ThirdPhrase.addStyleName("ecomeal-text");
		        
		        FourthPhrase = new Label("Votre commande sera délivré à " + deliveryTime);
		        FourthPhrase.addStyleName("ecomeal-text");
		        
		        // Boutton de retour
		        returnButton = new Button("Revenir au menu");
		        returnButton.addStyleName("ecomeal-button");
		        returnButton.addClickListener(e -> {
		        	navigator.navigateTo(EcomealConstants.MAIN_VIEW);
		        });
		        
		     // TODO Maxime : Generate QR Code
				byte[] data = QRCodeEncoder.encodeObjectToString(myOrder);
				if(data == null) {
					System.err.println("Failed to encode data");
				}
				BitMatrix bitMatrix;
				try {
					bitMatrix = QRCodeEncoder.generateMatrix(new String(data));
					if(bitMatrix == null) {
						// TODO : make better error log message
						System.err.println("Failed to generate QRCode byte matrix");
					}
					FileOutputStream fileOutputStream = new FileOutputStream("order" + myOrder.getId() + ".png");
			        MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
			        fileOutputStream.close();
				} catch (IOException e) {
					System.err.println("Failed to generate QRCode");
				}
				
				Image qrCodeImage = new Image();
				qrCodeImage.setSource(new FileResource(new File("order" + myOrder.getId() + ".png")));
		        
		        VerticalLayout vertical = new VerticalLayout(title,FirstPhrase, css, SecondPhrase, ThirdPhrase, FourthPhrase, qrCodeImage, returnButton);
		        vertical.setResponsive(true);
		        removeAllComponents();
		        addComponents(vertical);
		        
		        // TODO Alexandre : Send email
		        //EmailSender.sendEmail("alexandre.d.info@gmail.com", "Nouvelle commande", "Vous avez une nouvelle commande d'Ecomeal");
		        
		        
				return true;
			}
			
		});
		
		// For the vertical scrollbar
        setHeight(null);
        setWidth("100%");
	}
	
	

	@Override
	public void enter(ViewChangeEvent event) {
		// Set background color for Basket Components
		Page.getCurrent().getJavaScript().execute(
				"var elements = document.getElementsByClassName('" + BasketComponent.javaScriptClassName + "');" +
				"for (var i = 0; i < elements.length; i++) {" +
				"	for (var j = 0; j < elements[i].childNodes.length; j++) {" +
				"		if(elements[i].childNodes[j].classList.contains('v-slot')) {" +
				"			elements[i].childNodes[j].style.backgroundColor='#F5F5F5';" +
				"			elements[i].childNodes[j].style.border='1px solid #ddd';" +
				"		}" +
				"		else if(elements[i].childNodes[j].classList.contains('v-spacing')) {" +
				"			elements[i].childNodes[j].style.width='150px';" +
				"			elements[i].childNodes[j].style.height='0px';" +
				"			elements[i].childNodes[j].style.backgroundColor='#B0B0B0';" +
				"		}" +
				"	}" +
				"}"
		);
		
		
			
	}

}
