package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Club;

@Repository
public interface ClubRespository extends JpaRepository<Club, Integer> {
    
}