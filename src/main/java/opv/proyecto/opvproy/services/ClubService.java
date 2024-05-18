package opv.proyecto.opvproy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Club;
import opv.proyecto.opvproy.repositories.ClubRespository;

@Service
public class ClubService {
    @Autowired
    private ClubRespository clubRespository;

    public Club añadir(Club club) {
        return clubRespository.save(club);
    }

    public List<Club> obtenerTodos() {
        return clubRespository.findAll();
    }

    public Club obtenerPorCodigo(Integer codigo) throws Exception {
        return clubRespository.findById(codigo).orElseThrow(Exception::new);
    }

    public Club editar(Club club) {
        return clubRespository.save(club);
    }

    public void borrar(Integer codigo) throws Exception {
        clubRespository.deleteById(codigo);
    }

    public ArrayList<String> clubBuscados() {
        ArrayList<String> clubs = new ArrayList<>();
        clubs.add("Futbol Club Barcelona");
        clubs.add("Real Club Deportivo de La Coruña");
        clubs.add("Real Sociedad de Futbol");
        return clubs;
    }
}