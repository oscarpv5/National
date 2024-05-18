package opv.proyecto.opvproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Campo;
import opv.proyecto.opvproy.repositories.CampoRespository;

@Service
public class CampoService {
    @Autowired
    private CampoRespository campoRespository;

    public Campo añadir(Campo campo) {
        return campoRespository.save(campo);
    }

    public List<Campo> obtenerTodos() {
        return campoRespository.findAll();
    }

    public Campo obtenerPorNombre(String nombre) throws Exception {
        return campoRespository.findById(nombre).orElseThrow(Exception::new);
    }

    public Campo editar(Campo campo) {
        return campoRespository.save(campo);
    }

    public void borrar(String nombre) throws Exception {
        campoRespository.deleteById(nombre);
    }
}