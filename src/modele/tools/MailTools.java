package modele.tools;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class MailTools {
	
	public static void sendMail(String email,String subject, String messageText) throws AddressException, MessagingException  {
	      System.out.println("Début d'envoie...");
		String host ="smtp.gmail.com" ;
        String user = "Contact.EnsemblePourAfrique@gmail.com";
        String pass = "projetsia";
        String to = email;
        String from = "Contact.EnsemblePourAfrique@gmail.com";
        boolean sessionDebug = false;

        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(sessionDebug);
        
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject); 
        msg.setSentDate(new Date());
        msg.setText(messageText);
        /* Pour Image (En attente)
	       // Image
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(messageText);
	        Multipart multipart = new MimeMultipart();
	
	        // Set text message part
	        multipart.addBodyPart(messageBodyPart);
	        messageBodyPart = new MimeBodyPart();
	        
	        String filename = "logo.png";
	        DataSource source = new FileDataSource(filename);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(filename);
	        //Trick is to add the content-id header here
	        messageBodyPart.setHeader("Content-ID", "image_id");
	        multipart.addBodyPart(messageBodyPart);
	
	        //third part for displaying image in the email body
	        messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent("<img src='cid:image_id'>", "text/html");
	        multipart.addBodyPart(messageBodyPart);
	        
	        //Set the multipart message to the email message
	        msg.setContent(multipart);
        */
        Transport transport=mailSession.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("E-mail envoyé");
	}

}
