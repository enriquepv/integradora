package org.ilozano.proyecto_dam_daw_2.repository;

import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByIdUsuario(UUID idUsuario);

    Optional<Usuario> findUsuarioByEmail(String email);
    Usuario findByEmail(String email);



    Optional<Usuario> findUsuarioByClave(String clave);
    Usuario findByClave(String clave);


    Optional<Usuario> findUsuarioByEmailAndClave(String email, String clave);
    Usuario findByEmailAndClave(String email, String clave);

    @Query("SELECT u from Usuario u")
    List<Usuario> findAll();
}
