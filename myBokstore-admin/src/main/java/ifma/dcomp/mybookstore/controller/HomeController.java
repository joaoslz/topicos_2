package ifma.dcomp.mybookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/minhaConta")
	public String minhaConta() {
		return "conta-usuario";
	}
	
	

}
