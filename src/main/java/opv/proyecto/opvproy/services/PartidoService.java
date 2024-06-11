package opv.proyecto.opvproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Partido;
import opv.proyecto.opvproy.repositories.PartidoRespository;

@Service
public class PartidoService {
    
    @Autowired
    private PartidoRespository partidoRespository;

    public Partido añadir(Partido partido) {
        return partidoRespository.save(partido);
    }

    public List<Partido> obtenerTodos() {
        return partidoRespository.findAll();
    }

    public Partido obtenerPorCodigo(Integer codigo) throws Exception {
        return partidoRespository.findById(codigo).orElseThrow(Exception::new);
    }

    public Partido editar(Partido partido) {
        return partidoRespository.save(partido);
    }

    public void borrar(Integer codigo) throws Exception {
        partidoRespository.deleteById(codigo);
    }
}