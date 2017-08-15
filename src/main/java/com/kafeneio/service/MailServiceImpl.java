package com.kafeneio.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.RegistrationDTO;
@Service("mailService")
public class MailServiceImpl implements MailService {
	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendEmail(Object object) {

		RegistrationDTO registration = (RegistrationDTO) object;

		MimeMessagePreparator preparator = getMessagePreparator(registration);

		try {
			mailSender.send(preparator);
			System.out.println("Message Send...Hurrey");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	private MimeMessagePreparator getMessagePreparator(RegistrationDTO registration) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom("patel2810shweta@gmail.com");
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(registration.getLogin().getEmail()));
				mimeMessage.setText("Dear " + registration.getName()
						+ ", thank you for registering in traffic monkey .  " );
				mimeMessage.setSubject("Register into  kafeneio application");
			}
		};
		return preparator;
	}

}
