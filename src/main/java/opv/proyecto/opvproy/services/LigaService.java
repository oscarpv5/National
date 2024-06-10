package opv.proyecto.opvproy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Liga;
import opv.proyecto.opvproy.repositories.LigaRespository;

@Service
public class LigaService {
    @Autowired
    private LigaRespository ligaRespository;

    public Liga añadir(Liga liga) {
        return ligaRespository.save(liga);
    }

    public List<Liga> obtenerTodos() {
        return ligaRespository.findAll();
    }

    public Liga obtenerPorCodigo(Integer codigo) throws Exception {
        return ligaRespository.findById(codigo).orElseThrow(Exception::new);
    }

    public Liga editar(Liga liga) {
        return ligaRespository.save(liga);
    }

    public void borrar(Integer codigo) throws Exception {
        ligaRespository.deleteById(codigo);
    }
}