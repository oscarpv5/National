package opv.proyecto.opvproy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opv.proyecto.opvproy.domain.Campo;

@Repository
public interface CampoRespository extends JpaRepository<Campo, String> {

}