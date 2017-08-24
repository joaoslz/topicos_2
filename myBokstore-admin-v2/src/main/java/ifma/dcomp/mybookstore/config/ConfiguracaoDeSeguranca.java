package ifma.dcomp.mybookstore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ifma.dcomp.mybookstore.service.SegurancaUsuarioService;

@Configuration
@EnableWebSecurity

// segurança nos métodos dos controllers
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SegurancaUsuarioService usuarioService;
	
	// autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	

		http.authorizeRequests()
		    .antMatchers("/livro/form").hasRole("ADMIN")
		    .antMatchers("/livro/pesquisa").permitAll()
		    .antMatchers(HttpMethod.POST, "/livro/remove").hasRole("ADMIN")
		    
		    // permite tudo que não foi bloqueado anteriormente
		    .antMatchers("/**").permitAll()
		    
		    // libera os arquivos estáticos
		    .antMatchers("/resources/**").permitAll()
		    
		    // verifica a autenticação para todos os requests
		    .anyRequest().authenticated()
		    .and()
		       // se não tiver logado, encaminha para o form de login
		       .formLogin()
		          .loginPage("/login").permitAll()
		    .and()
		          .logout()
		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout") );   
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService)
		.passwordEncoder(new BCryptPasswordEncoder() );
	}
	
	
	
/*	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .inMemoryAuthentication()
	                .withUser("admin").password("admin").roles("ADMIN", "USER")
	                .and().withUser("user").password("user").roles("USER");
	    }*/
	

}

