package ifma.dcomp.mybookstore.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.dcomp.mybookstore.model.Livro;

public interface Livros extends JpaRepository<Livro, Serializable>{

}
