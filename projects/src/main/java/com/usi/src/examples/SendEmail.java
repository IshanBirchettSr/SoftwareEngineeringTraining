/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
// File Name SendEmail.java
package examples;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

    public static void main(String[] args) {
	// Recipient's email ID needs to be mentioned.
	String to = "corneliuscharlene76@gmail.com";

	// Sender's email ID needs to be mentioned
	String from = "superstore0502@gmail.com";

	// Assuming you are sending email from localhost
	String host = "localhost";

	// Get system properties
	// Properties properties = System.getProperties();

	// Setup mail server
	// properties.setProperty("mail.smtp.host", host);

	Properties properties = new Properties();
	properties.put(from, to);
	properties.put("mail.smtp.auth", true);
	properties.put("mail.smtp.starttls.enable", true);
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "465");
	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	// Get the default Session object.
	// Session session = Session.getDefaultInstance(properties);
	String username = "superstore0502@gmail.com";
	String password = "superstore0502";
	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	    }
	});

	try {
	    // Create a default MimeMessage object.
	    MimeMessage message = new MimeMessage(session);

	    // Set From: header field of the header.
	    message.setFrom(new InternetAddress(from));

	    // Set To: header field of the header.
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	    // Set Subject: header field
	    message.setSubject("USI Superstore Receipt");

	    // Now set the actual message
	    message.setText("Thank you for shopping at USI Super Store");

	    // Send messages
//	    Transport transport = session.getTransport("smtp");
//	    transport.connect("smtp.gmail.com", 587, "superstore0502", password);
	    Transport.send(message);
	    System.out.println("Sent message successfully....");
	} catch (MessagingException mex) {
	    mex.printStackTrace();
	}
    }
}
