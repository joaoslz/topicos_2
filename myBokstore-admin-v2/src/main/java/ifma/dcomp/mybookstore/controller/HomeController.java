package ifma.dcomp.mybookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ifma.dcomp.mybookstore.component.ConexaoComponente;
import ifma.dcomp.mybookstore.model.Categoria;
import ifma.dcomp.mybookstore.model.Editora;
import ifma.dcomp.mybookstore.model.Livro;
import ifma.dcomp.mybookstore.service.CategoriaService;
import ifma.dcomp.mybookstore.service.EditoraService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private EditoraService editoraService;
	
	@Autowired
	private ConexaoComponente exemploComponente;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/home2")
	public String home2() {
		return "index2";
	}
	
	
	@GetMapping("/minhaConta")
	public String minhaConta() {
		return "conta-usuario";
	}
	
	@ModelAttribute("todasCategorias")
	public List<Categoria> todasCategorias() {
		return categoriaService.todas();
	}
	
	@ModelAttribute("editoras")
	public List<Editora> todasEditoras() {
		return editoraService.todas();
	}
}
