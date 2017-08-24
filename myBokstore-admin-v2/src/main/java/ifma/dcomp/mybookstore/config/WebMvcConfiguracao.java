package ifma.dcomp.mybookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ifma.dcomp.mybookstore.component.TempoRequisicaoIntecerptor;

@Configuration
public class WebMvcConfiguracao extends WebMvcConfigurerAdapter {
	 
	@Autowired
	private TempoRequisicaoIntecerptor interceptador;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptador);
	}
	
	
}
