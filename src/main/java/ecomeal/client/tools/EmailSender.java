package ecomeal.client.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	public static void sendEmail(String adresse, String titre, String messageText){
		
	     
	        //ouverture d'une session. la session gére les informations de configuration (nom d'utilisateur, mot de passe, hôte) nécessaires pour utiliser les fonctionnalités de JavaMail
	        Properties props= new Properties();
	        props.setProperty("mail.from", "alexandre.d.info@gmail.com"); // @ expediteur
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        Session session= Session.getInstance(props);
	         
	        //Le message
	        Message     message     = new MimeMessage(session);
	        InternetAddress recipient;
			try {
				recipient = new InternetAddress(adresse);
				message.setRecipient(Message.RecipientType.TO, recipient);
				message.setSubject(titre);
		        message.setText(messageText);
		         
		        //Transport
		        Transport.send(message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
		
	}
}
