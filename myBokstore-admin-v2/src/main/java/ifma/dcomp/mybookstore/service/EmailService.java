package ifma.dcomp.mybookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class EmailService {
	
	private final Logger log = LoggerFactory.getLogger(EmailService.class); 

	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Async
	public void enviarEmail() throws MailException {
		
		log.info(">>> Iniciando o envio do Email ...");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// envia email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("joaoslz@gmail.com");
		mail.setFrom("jcpinheiro@ifma.edu.br");
		
		mail.setSubject("Teste de email!");
		mail.setText("Esta é uma simples mensagem de notificação");
		
	   javaMailSender.send(mail);

	   log.info(">>> Finalizando o envio do Email ...");
		
	}
		
}
