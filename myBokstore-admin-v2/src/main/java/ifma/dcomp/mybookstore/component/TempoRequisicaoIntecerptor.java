package ifma.dcomp.mybookstore.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class TempoRequisicaoIntecerptor extends HandlerInterceptorAdapter {
	
	private static final Log LOG =  LogFactory.getLog(TempoRequisicaoIntecerptor.class);

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, 
			                  Object handler) throws Exception {
		
		request.setAttribute("tempo_inicio", System.currentTimeMillis() );
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long tempoInicio = (long) request.getAttribute("tempo_inicio");
	
		LOG.info("-- URL Requisitada: ' " 
		          + request.getRequestURL().toString() 
		          + "' -- Tempo Total " 
		          +  ( System.currentTimeMillis() - tempoInicio) 
		          + "ms");
	}
}
