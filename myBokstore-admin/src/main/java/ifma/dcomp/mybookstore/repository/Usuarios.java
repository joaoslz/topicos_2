package ifma.dcomp.mybookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.dcomp.mybookstore.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer>{

}
