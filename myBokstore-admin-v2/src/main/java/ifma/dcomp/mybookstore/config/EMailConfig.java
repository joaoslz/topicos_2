package ifma.dcomp.mybookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@Configuration
public class EMailConfig {
	
	
	//@Bean
	public JavaMailSender mailSender() {
		JavaMailSender emailSender = new JavaMailSenderImpl();
		
		/*emailSender.setHost("smtp.sendgrid.net");
		emailSender.setPort(587);
		emailSender.setUsername("jcpinheiro");
		emailSender.setPassword("secreta01");
*/
		
		return emailSender;
		
		
	}

}
