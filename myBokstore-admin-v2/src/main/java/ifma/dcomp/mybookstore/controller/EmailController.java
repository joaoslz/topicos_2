package ifma.dcomp.mybookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ifma.dcomp.mybookstore.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private final Logger log = LoggerFactory.getLogger(EmailController.class); 
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/teste")
	public String enviar() {
		
			log.info("Antes de chamar o método enviaEmail");
			
			emailService.enviarEmail();
			
			log.info("Depois de chamar o método enviaEmail");
		    
			return "Email enviado com sucesso";
		  
	}
	
	

}
