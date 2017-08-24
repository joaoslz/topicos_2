package ifma.dcomp.mybookstore.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifma.dcomp.mybookstore.model.DetalhesImagemLivro;
import ifma.dcomp.mybookstore.model.Livro;
import ifma.dcomp.mybookstore.repository.Livros;

@Service
public class LivroService {

	@Autowired
	private Livros livros;
	
	@Transactional
	public Livro salva(Livro livro) {
		// TODO precisa ser rafatorado
		if ( houveEdicaoSemFotoSelecionadaNo(livro) ) {
			Livro livroAntigo = buscaPor(livro.getId() );
			
			if ( livroAntigo.temFotoCapa() ) {
				livro.setDetalhesImagem( livroAntigo.getDetalhesImagem() );
			}
		}
		
        return livros.save(livro);
		
	}

	private boolean houveEdicaoSemFotoSelecionadaNo(Livro livro) {
		return (livro != null ) && (livro.getId() != null) 
				                && (livro.getDetalhesImagem() == null);
	}

	public List<Livro> todos() {
		return livros.findAll();
	}

	public Livro buscaPor(Long id) {
		return livros.findOne(id);
	}
	@Transactional
	public void excluirPelo(Long id) {
		
		Livro livro = this.buscaPor(id);
		if (livro != null && livro.getDetalhesImagem() != null)
		   this.excluirImagem(livro.getDetalhesImagem() );
		
		if (id != null ) {
		  livros.delete(id);
		  livros.flush();
		} else {
			throw new IllegalArgumentException("Informe um livro válido para exclusão");
		}
	}

	private void excluirImagem(DetalhesImagemLivro detalhesImagem) {
		
		Path path = Paths.get(detalhesImagem.getRealPathComNomeDoArquivo() );
        try {
			Files.deleteIfExists(path );
		
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
