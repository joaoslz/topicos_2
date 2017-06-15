package ifma.dcomp.mybookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifma.dcomp.mybookstore.controller.util.SalvaArquivoNoServidor;
import ifma.dcomp.mybookstore.model.Editora;
import ifma.dcomp.mybookstore.model.Livro;
import ifma.dcomp.mybookstore.service.EditoraService;
import ifma.dcomp.mybookstore.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private EditoraService editoraService;
	
	
	@GetMapping("/form")
	public String form(Model model, Livro livro) {
		
		model.addAttribute("livro", livro);
		// model.addAttribute("editoras", editoraService.todas() );
		return "livro/cadastro-livro";
	}
	
	@ModelAttribute("editoras")
	public List<Editora> todasEditoras() {
		return editoraService.todas();
	}
	
    @PostMapping("/salva")
	public String salva(@Validated Livro livro, 
			            Errors validacao, 
			            Integer editoraId,
			            RedirectAttributes redirect,
			            MultipartFile imagemDoLivro,
			            HttpServletRequest request ) {
    	
    	if (validacao.hasErrors() ) {
    		return "livro/cadastro-livro";
    	}
    	
    	if ( foiSelecionadaA(imagemDoLivro )) {
    		
    		SalvaArquivoNoServidor salvaArquivo = new SalvaArquivoNoServidor();
    		String fotoPath = salvaArquivo.salvaImagem("imagens/img-livros" , imagemDoLivro, request);
    		
    		livro.setFotoPath(fotoPath);
    		
    	}
    	livroService.salva(livro, editoraId);
    	
    	redirect.addFlashAttribute("mensagem_sucesso", "O livro foi Salvo com Sucesso" );
    	String rota = livro.ehNovo() ? "redirect:/livro/form" : "redirect:/livro/pesquisa";

    	return rota;
	}

	

	private boolean foiSelecionadaA(MultipartFile imagem) {
		return (imagem != null ) && ( !imagem.isEmpty() );
	}

	@GetMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("livro/pesquisa-livro" );
		modelAndView.addObject("livros", livroService.todos() );
		
		return modelAndView;	
	}
	
	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Livro livro = livroService.buscaPor(id );
		
		ModelAndView modelAndView = new ModelAndView("livro/cadastro-livro");
		modelAndView.addObject("livro", livro);
		
		return modelAndView;
		
	}
	
	@GetMapping("/{id}/detalhes")
	public ModelAndView detalhes(@PathVariable("id") Long id) {
		
		Livro livro = livroService.buscaPor(id);
		
		ModelAndView modelAndView = new ModelAndView("livro/detalhes-livro");
		modelAndView.addObject("livro", livro);
		
		return modelAndView;
		
	}
	

}
