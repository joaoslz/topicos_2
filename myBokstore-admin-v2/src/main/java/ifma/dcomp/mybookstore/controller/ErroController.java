package ifma.dcomp.mybookstore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ErroController {
	
	//@ExceptionHandler(Exception.class)
	public String exibeErroNoServidor() {
		return "/error/500";
	}

}
