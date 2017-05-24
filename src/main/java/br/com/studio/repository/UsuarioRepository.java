package br.com.studio.repository;

import br.com.studio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findOneBySenha(Integer senha);

}
