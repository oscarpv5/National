package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Usuario;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByNombre(String nombre);
}