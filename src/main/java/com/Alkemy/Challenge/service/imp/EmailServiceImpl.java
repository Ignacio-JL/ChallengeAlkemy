package com.Alkemy.Challenge.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.Alkemy.Challenge.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private Environment environment;
	
	@Value("${challenge.email.sender}")
	private String emailSender;
	

	@Override
	public void sendWelcomeEmailTo(String to) {

		String apiKey = environment.getProperty("EMAIL_API_KEY");
		
		Email fromEmail = new Email(emailSender);
		Email toEmail = new Email(to);
		Content content = new Content(
				"text/plain",
				"Bienvenid@ a Disney Ü");
		String subject = "Disney App";
		
		Mail mail = new Mail(fromEmail, subject, toEmail, content);
		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
		} catch (Exception e) {
			System.out.println("Error trying to send this mail");
		}
		
	}

}
