package org.ilozano.proyecto_dam_daw_2.repository;

import com.mysql.cj.xdevapi.Client;
import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByIdCliente(UUID idCliente);

    Optional<Cliente> findClienteByNombre(String nombre);

    Cliente findByNombre(String nombre);

    Optional<Cliente> findById(UUID idCliente);
    @Query("SELECT c FROM Cliente c WHERE c.usuario.idUsuario = :idUsuario")
    Cliente findByUsuarioId(@Param("idUsuario") UUID idUsuario);


    @Query("SELECT c FROM Cliente c JOIN FETCH c.usuario u WHERE "
            + "(:fechaNacimientoDesde IS NULL OR c.fechaNacimiento >= :fechaNacimientoDesde) AND "
            + "(:fechaNacimientoHasta IS NULL OR c.fechaNacimiento <= :fechaNacimientoHasta) AND "
            + "(:apellido IS NULL OR c.apellidos LIKE %:apellido%) AND "
            + "(:bajaLogica IS NULL OR u.bajaLogica = :bajaLogica) AND "
            + "(:bloqueado IS NULL OR u.bloqueado = :bloqueado)")
    List<Cliente> buscarClientes(@Param("fechaNacimientoDesde") LocalDate fechaNacimientoDesde, @Param("fechaNacimientoHasta") LocalDate fechaNacimientoHasta,
                                 @Param("apellido") String apellido, @Param("bajaLogica") Boolean bajaLogica,
                                 @Param("bloqueado") Boolean bloqueado);
}