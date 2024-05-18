package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Liga;

@Repository
public interface LigaRespository extends JpaRepository<Liga, Integer> {
    
}