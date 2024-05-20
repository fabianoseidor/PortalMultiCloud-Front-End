package br.com.portal.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class EnviarEmail {
		/**
		 * Utility method to send simple HTML email
		 * @param session
		 * @param toEmail
		 * @param subject
		 * @param body
		 */
		public static void configEmail(Session session, String toEmail, String subject, String body){
			try
		    {
		      MimeMessage msg = new MimeMessage(session);
		      //set message headers
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format"                   , "flowed"     );
		      msg.addHeader("Content-Transfer-Encoding", "8bit"       );

		      msg.setFrom   (new InternetAddress  ("multicloud@seidorcloud.com.br", "Portal MultiCloud"));
		      msg.setReplyTo(InternetAddress.parse("multicloud@seidorcloud.com.br", false              ));
		      msg.setSubject(subject, "UTF-8");
		      msg.setText   (body   , "UTF-8");
		      msg.setSentDate(new Date());
		      		      
		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//		      System.out.println("Message is ready");
	          // envia o Email.    	  
		      Transport.send(msg);  
//		       System.out.println("EMail Sent Successfully!!");
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
		}
		
		
		/**
		   Outgoing Mail (SMTP) Server
		   requires TLS or SSL: smtp.gmail.com (use authentication)
		   Use Authentication: Yes
		   Port for TLS/STARTTLS: 587
		 */
		public static void sendEmail( String titulo, String corpoEmail, String toEmail ) {
			
//			final String fromEmail = "fabianoamaral.ti@gmail.com"; //requires valid gmail id
//			final String password  = "maoy qatz qdhd yctx"; // correct password for gmail id
			final String fromEmail = "emailappsmtp.39c07be65b2db29e"; //requires valid gmail id
			final String password  = "widv2sWEwYC6"; // correct password for gmail id
//			final String toEmail   = "fabiano.bolacha@gmail.com"; // can be any email id 
			
			System.out.println("TLSEmail Start");
			Properties props = new Properties();
//			props.put("mail.smtp.host"           , "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.host"           , "smtp.zeptomail.com"); //SMTP Host
			props.put("mail.smtp.port"           , "587"           ); //TLS Port
			props.put("mail.smtp.auth"           , "true"          ); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"          ); //enable STARTTLS
			props.put("mail.debug"               , "true");
			
	                //create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(props, auth);
			
			configEmail(session, toEmail, titulo, corpoEmail );
			
		}		
	   
}
