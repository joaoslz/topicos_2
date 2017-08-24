package ifma.dcomp.mybookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifma.dcomp.mybookstore.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer>{

   Usuario findByNome(String nome);
   Usuario findByEmail(String email);

}
