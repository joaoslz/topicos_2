package ifma.dcomp.mybookstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifma.dcomp.mybookstore.controller.util.ArquivoUpload;
import ifma.dcomp.mybookstore.model.Categoria;
import ifma.dcomp.mybookstore.model.DetalhesImagemLivro;
import ifma.dcomp.mybookstore.model.Editora;
import ifma.dcomp.mybookstore.model.Livro;
import ifma.dcomp.mybookstore.service.CategoriaService;
import ifma.dcomp.mybookstore.service.EditoraService;
import ifma.dcomp.mybookstore.service.LivroService;


@Controller
@RequestMapping("/livro")
public class LivroController {
	
    private static final Log LOG = LogFactory.getLog(RelatorioController.class );
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private EditoraService editoraService;

	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/form")
	public String form(Model model, Livro livro) {
		model.addAttribute("livro", livro);
		
		//int x = 5 / 0;
		
		return "livro/cadastro-livro";
	}
	
	@ModelAttribute("editoras")
	public List<Editora> todasEditoras() {
		return editoraService.todas();
	}
	
	@ModelAttribute("todasCategorias")
	public List<Categoria> todasCategorias() {
		return categoriaService.todas();
	}
    @PostMapping("/salva")
	public String salva(@Validated Livro livro, 
			            Errors validacao, 
			            RedirectAttributes redirect,
			            MultipartFile imagemDoLivro,
			            HttpServletRequest request ) {
    	
    	if (validacao.hasErrors() ) {
    		return "livro/cadastro-livro";
    	}
    	
    	livro = livroService.salva(livro);
    	
    	if ( foiSelecionadaA(imagemDoLivro )) {
    		ArquivoUpload arquivoUpload = new ArquivoUpload(imagemDoLivro);
    		
    		arquivoUpload.salvaImagem("imagens/img-livros", livro.getId(), request );
    		
    		DetalhesImagemLivro imagemLivro = arquivoUpload.criaDetalhesImagemLivro();
    		
    		livro.setDetalhesImagem(imagemLivro);
    		
    	}
    	livroService.salva(livro);
    	LOG.info("Método salva() -- Livro " + livro.getTitulo() );
    	
    	
    	
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
	@PostMapping("/remove")
	public ModelAndView remove(@ModelAttribute("id") Long livroId, RedirectAttributes redirect) {
		livroService.excluirPelo(livroId );
		redirect.addFlashAttribute("mensagem_sucesso", "O Livro foi Removido com Sucesso" );
		
		return this.pesquisa();
		
		
	}
	
	@PostMapping("/removeLista")
	@ResponseBody
	public String removeLivros(@RequestBody ArrayList<Long> listaLivroId) {
		
		System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " + listaLivroId);
		
		listaLivroId.forEach( id -> livroService.excluirPelo(id) );
				
		return "livros excluídos com sucesso";
		
	}
	
	@GetMapping("/{id}/detalhes")
	public ModelAndView detalhes(@PathVariable("id") Long id) {
		
		Livro livro = livroService.buscaPor(id);
		
		ModelAndView modelAndView = new ModelAndView("livro/detalhes-livro");
		modelAndView.addObject("livro", livro);
		
		return modelAndView;
		
	}
	
	@PostMapping("/filtrar")
	public ModelAndView filtrar(
			  @RequestParam(required=false) String titulo, 
			  @RequestParam Long editoraId ) {
		
		System.out.println("-------------------------------");
		System.out.println(titulo);
		System.out.println(editoraId);
		
		ModelAndView modelAndView = new ModelAndView("livro/pesquisa-livro" );
		modelAndView.addObject("livros", livroService.todos() );
		
		return modelAndView;	
	}
}
