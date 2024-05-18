package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Partido;

@Repository
public interface PartidoRespository extends JpaRepository<Partido, Integer> {
    
}