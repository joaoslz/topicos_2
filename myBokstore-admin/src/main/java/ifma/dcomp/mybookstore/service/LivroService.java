package ifma.dcomp.mybookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifma.dcomp.mybookstore.model.Editora;
import ifma.dcomp.mybookstore.model.Livro;
import ifma.dcomp.mybookstore.repository.Editoras;
import ifma.dcomp.mybookstore.repository.Livros;

@Service
public class LivroService {

	@Autowired
	private Livros livros;
	
	@Autowired
	private Editoras editoras;

	@Transactional
	public void salva(Livro livro, Integer editoraId) {
		Editora editora = editoras.findOne(editoraId);
		livro.setEditora(editora);
		livros.save(livro);
		// fazer outras operações no banco
	}

	public List<Livro> todos() {
		return livros.findAll();
	}

	public Livro buscaPor(Long id) {
		return livros.findOne(id);
	}
	
	
	

}
