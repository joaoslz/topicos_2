package ifma.dcomp.mybookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.dcomp.mybookstore.model.Categoria;

public interface Categorias extends JpaRepository<Categoria, Integer> {
	

}
