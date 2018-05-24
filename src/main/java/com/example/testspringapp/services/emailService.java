package com.example.testspringapp.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.example.testspringapp.models.User;

public class emailService {
	public String email(User user) {
		
		String receiver = user.getEmail(); //"ojas.patwardhan@gmail.com";
		String sender = "webdev.summer.full@gmail.com";
		String username = "webdev.summer.full";
		String password = "9Wjet124";
		String host = "smtp.gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiver));
			message.setSubject("Password Reset");
			message.setText("Dear" + " " + user.getUsername() + "," + 
					"\n\n Your current password is" + " " + user.getPassword() + " " + "click on the link below to reset your password" + " " + "\n \n" + " " + "https://cs5610-summer-2018-pat-ojas.herokuapp.com/jQuery/components/resetPassword/resetPassword.template.client.html");

			Transport.send(message);

			System.out.println("Done");
			
			return "Success";

		} catch (MessagingException e) {
//			throw new RuntimeException(e);
			return "Something went wrong";
		}
	}
}

