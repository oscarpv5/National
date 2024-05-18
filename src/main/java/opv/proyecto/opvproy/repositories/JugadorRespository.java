package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Jugador;

@Repository
public interface JugadorRespository extends JpaRepository<Jugador, String> {
    
}