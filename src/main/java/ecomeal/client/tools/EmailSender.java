package ecomeal.client.tools;

import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ecomeal.client.entity.Basket;
import ecomeal.client.entity.Order;
import ecomeal.client.services.ScheduleService;

public class EmailSender {
	
	public void sendRecap(String to, Order order, String pathToQRCode) {
		
		String username = "ecomealsociete@gmail.com";// change accordingly
		String password = "jambonbeurre";// change accordingly

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Ecomeal"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject("Fiche Récapitulative de votre commande");
			
			Multipart multipart = new MimeMultipart();
			
			MimeBodyPart textBodyPart = new MimeBodyPart();
			
			String text = "Voici votre commande,"
					+ "\n\n Vous avez commandé :";
			
			Map<Basket,Integer> basketMap = order.getBaskets();
			
			for(Map.Entry<Basket, Integer> entry : basketMap.entrySet()){
				text += "\n\n " + entry.getValue().toString() + " " +  entry.getKey().getName() + ".";
			}
			
			ScheduleService service = new ScheduleService(new JsonTool());
			
			text += "Votre commande sera prête pour " + service.ScheduleToString(order.getDeliveryTime())
					+ "\n\n Scanner le QRCode pour retirer votre commande."
					+ "\n\n On se retrouve très vite !"
					+ "\n\n Equipe Ecomeal.";
			
	        textBodyPart.setText(text);
	        
	        MimeBodyPart attachmentBodyPart= new MimeBodyPart();
	        DataSource source = new FileDataSource(pathToQRCode); // ex : "C:\\test.pdf"
	        attachmentBodyPart.setDataHandler(new DataHandler(source));
	        attachmentBodyPart.setFileName("QRCode.png"); // ex : "test.pdf"

	        multipart.addBodyPart(textBodyPart);  // add the text part
	        multipart.addBodyPart(attachmentBodyPart); // add the attachement part

	        message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
