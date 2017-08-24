package ifma.dcomp.mybookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifma.dcomp.mybookstore.model.Categoria;
import ifma.dcomp.mybookstore.repository.Categorias;

@Service
public class CategoriaService {
	
	@Autowired
	private Categorias categorias;
	
	
	public List<Categoria> todas() {
		return categorias.findAll();
	}

}
