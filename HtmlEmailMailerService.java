package com.rrr.realestate.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.rrr.realestate.bo.MessageParamsVO;
import com.rrr.realestate.util.DateUtils;
import com.rrr.realestate.util.utils;
import com.sun.jersey.core.util.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import javax.mail.*;
import javax.mail.internet.*;
import javax.ws.rs.core.MediaType;



public class HtmlEmailMailerService {

	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private JavaMailSender mailSender;

	private static String serverName ="www.thevacationhub.com";

	// Amazon SES Setup
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = "AKIAIMFUYSINDKE2Y6UQ";  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = "AhdyRMsztNmtVNYZfPJRrDEMzE5o8X9egmtoiS7W6Mpi";  // Replace with your SMTP password.
    // Amazon SES SMTP host name.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";    
    
    // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 587;

    
    
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setServerName(String serverName) {
		this.serverName=serverName;
		System.out.println("serverName=" + serverName);
	}
	public static String getServerName() {
		return serverName;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public String queueUpMail(int num,MessageParamsVO mt,Boolean sendMail) {
		String text="";
		Map model = new HashMap();
		model.put("vo", mt);
		model.put("number", new NumberTool());
		model.put("aLocale", Locale.US);
		mt.setServerName(getServerName());


		//Mailgun Setup
//		Client client = Client.create();
//		client.addFilter(new HTTPBasicAuthFilter("api", "key-75goabn-08hpuzsu-khgc-q9at1nobc6"));
//		WebResource webResource = client.resource("https://api.mailgun.net/v2/thevacationhub.com" + "/messages");
//		MultivaluedMapImpl formData = new MultivaluedMapImpl();


  	

		try {
			mt.setToday(utils.formatDate(new Date(), "MMMMMM d, yyyy"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (num < 70) {//do this check only for specific mail fomat ids... not always.
			mt.setCheckInDate(DateUtils.date2String(mt.getBooking().getDateStart(), "MMMMMM d, yyyy"));
			mt.setCheckOutDate(DateUtils.date2String(mt.getBooking().getDateEnd(), "MMMMMM d, yyyy"));
		}
		switch (num) {

		case 0: //*cleaned up*
			mt.setSubject("Reservation Quote for " + mt.getBooking().getListing().getName());
			mt.setTitle("Vacation Rental Quote" /* + mt.getBooking().getListing().getName() */);
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/00_quote_color.vm", model);
			break;

		case 1:
			mt.setSubject("Contract document for " + mt.getBooking().getListing().getName());
			mt.setTitle("Contract document for " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/01_contract_doc_new.vm", model);
			break;

		case 3: //*cleaned up*
			mt.setSubject("Received your signed contract for " + mt.getBooking().getListing().getName());
			mt.setTitle("Signed Contract Received" /* + mt.getBooking().getListing().getName() */);
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/03_received_signed_contract.vm", model);
			break;

		case 4: //*cleaned up*
			mt.setSubject("Received a copy of your Driver License for " + mt.getBooking().getListing().getName());
			mt.setTitle("Received a Copy of Your Driver License");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/04_received_dl.vm", model);
			break;

		case 5: //*cleaned up*
			mt.setSubject("Received deposit via regular mail for " + mt.getBooking().getListing().getName());
			mt.setTitle("Received Deposit Via Regular Mail");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/05_received_deposit_mail.vm", model);
			break;

		case 6: //*cleaned up*
			mt.setSubject("You've made a Payment for " + mt.getBooking().getListing().getName() );
			mt.setTitle("Payment Received" /* + mt.getBooking().getListing().getName() */);
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/06_received_deposit_paypal.vm", model);
			break;

		case 7:
			mt.setSubject("Not received your signed contract for " + mt.getBooking().getListing().getName());
			mt.setTitle("Not received your signed contract for " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/07_not_received_signed_contract.vm", model);
			break;

		case 8:
			mt.setSubject("Not received a copy of your Drivers License for " + mt.getBooking().getListing().getName());
			mt.setTitle("Not received a copy of your Driver License for " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/08_not_received_dl.vm", model);
			break;

		case 9: //*cleaned up*
			mt.setSubject("Not received your deposit for " + mt.getBooking().getListing().getName());
			mt.setTitle("Your Deposit has not been Received");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/09_not_received_deposit.vm", model);
			break;

		case 11: //*cleaned up*
			mt.setSubject("Final payment is due in 3 days for " + mt.getBooking().getListing().getName());
			mt.setTitle("Final Payment Due in 3 Days");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/11_not_received_final_pay.vm", model);
			break;

		case 12: //*cleaned up*
			mt.setSubject("Final payment is now due for " + mt.getBooking().getListing().getName());
			mt.setTitle("Final Payment is Now Due");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/12_not_received_final_pay.vm", model);
			break;

		case 13:
			mt.setSubject("Your final reservation/confirmation letter for " + mt.getBooking().getListing().getName());
			mt.setTitle("Your final reservation/confirmation letter for " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/13_final_confirmation_doc.vm", model);
			break;

		case 14: //*cleaned up*
			mt.setSubject("Thank You for staying at " + mt.getBooking().getListing().getName());
			mt.setTitle("Thank You for Staying at " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/14_thankyou.vm", model);
			break;

		case 15:
			mt.setSubject("Booking requires Key Code input for " + mt.getBooking().getListing().getName());
			mt.setTitle("Booking requires Key Code input for " + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/15_enter_key_code.vm", model);
			break;

		case 16:
			mt.setSubject("Rentor's check out");
			mt.setTitle("Rentor's checked out");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/16_rentors_checked_out.vm", model);
			break;

		case 17:
			mt.setSubject("Damage Deposit refund" + mt.getBooking().getListing().getName());
			mt.setTitle("Damage Deposit refund" + mt.getBooking().getListing().getName());
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/17_refund_damage_deposit.vm", model);
			break;

		case 70:
			//mt.setSubject("Online Quote");
			mt.setTitle("Online quote");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/70_live_quote.vm", model);
			break;

		case 71:
			//		mt.setSubject("Online Quote");
			//		mt.setTitle("Online quote");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/71_live_quote_not_available.vm", model);
			break;

		case 72:
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/72_booked.vm", model);
			break;

		case 80:
			mt.setSubject("Beta Test Account Request");
			mt.setTitle("Beta Test Account Request");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/80_admin_sendRequestForBetaTestAccount.vm", model);
			break;

		case 81: //*cleaned up*
			mt.setSubject("Received Payment for a Booking at " + mt.getBooking().getListing().getName());
			mt.setTitle("Received Payment for Booking");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/02_payment_received__to_owner.vm", model);
			break;

		case 82:
			if(mt.getSubject() == null)
				mt.setSubject("[The Vacation Hub] - Property Inquiry");
			if(mt.getTitle() == null)
				mt.setTitle("[The Vacation Hub] - Property Inquiry");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/82_contactOwnerAboutAvailability.vm", model);
			break;

		case 90: //*cleaned up*
			mt.setSubject("Welcome to The Vacation Hub");
			mt.setTitle("Welcome to The Vacation Hub");
			if (mt.getReceiverEmail() == null) mt.setReceiverEmail("newuser@outside.com");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/90_registrationConformation.vm", model);
			break;

		case 91:
			mt.setSubject("Forgot Password - Vacation Hub");
			mt.setTitle("Forgot Password -  Vacation Hub");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/91_forgotPasswordMail.vm", model);
			break;

		case 92:
			mt.setSubject("New user on our website");
			mt.setTitle("New user on our website");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/92_newuser_on_website.vm", model);
			break;

		case 93:
			mt.setSubject("New listing on the Vacation Hub");
			mt.setTitle("New listing on the Vacation Hub");
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/dns/93_newlisting_on_website.vm", model);
			break;

			//grow more
		}
		mt.setMessage(text);		// put the content of the velocity template



		// Amazon SES
        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtps.port", 587);
    	
    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
    	// The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
    	props.put("mail.smtps.auth", "true");
    	props.put("mail.smtps.ssl.enable", "true");
    	props.put("mail.smtps.starttls.enable", "true");
    	props.put("mail.smtps.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

 
		if (sendMail) {

			//Amazon SES
			Transport transport = null;
	        // Create a message with the specified information. 
	        MimeMessage ames = new MimeMessage(session);
	        try {
				ames.setFrom(new InternetAddress("The Vacation Hub <reservation-noreply@thevacationhub.com>"));
		        ames.setSubject(mt.getSubject());
		        ames.setContent(text,"text/html");

				if(num < 80) {
					System.out.println("[Email] Attempting to send email to renter: " + mt.getBooking().getEmail());
			        ames.setRecipient(Message.RecipientType.TO, new InternetAddress(mt.getBooking().getEmail()));
				} else {
					System.out.println("[Email] Attempting to send a mail owner: " + mt.getReceiverEmail());
			        ames.setRecipient(Message.RecipientType.TO, new InternetAddress(mt.getReceiverEmail()));

					if (num >= 92) {
				        ames.setRecipient(Message.RecipientType.TO, new InternetAddress("cfofiu@gmail.com"));
					}
				}
		        
		        // Create a transport.        
		        transport = session.getTransport();
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Send the message.
	        try
	        {
	            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
	            
	            // Connect to Amazon SES using the SMTP username and password you specified above.
	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
	        	
	            // Send the email.
	            transport.sendMessage(ames, ames.getAllRecipients());
	            System.out.println("Email sent!");
	        }
	        catch (Exception ex) {
	            System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	
	        }
	        
//			try	{
//				formData.add("from", "The Vacation Hub <reservation-noreply@thevacationhub.com>");
//				formData.add("subject", mt.getSubject());
//				formData.add("html", text);
//
//
//				//TODO: Do we need to BCC every time any email is sent?
//				//		formData.add("bcc", "cfofiu@gmail.com");
//
//				//Sending email via Mailgun.com
//				System.out.println("[Email] Message Ready now.");
//				ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
//						post(ClientResponse.class, formData);
//				System.out.println("[Email] Mailgun resonse... ");
//				System.out.println(response.toString());
//
//			}
//			catch (Exception e) {
//				System.out.println("[Email] Mail Exception :" + e.getMessage());
//			}
		}
		return text;
	}




}
