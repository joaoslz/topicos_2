package ifma.dcomp.mybookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifma.dcomp.mybookstore.model.Editora;
import ifma.dcomp.mybookstore.repository.Editoras;

@Service
public class EditoraService {
	
	@Autowired
	private Editoras editoras;
	
	
	public List<Editora> todas() {
		return editoras.findAll();
	}

}
