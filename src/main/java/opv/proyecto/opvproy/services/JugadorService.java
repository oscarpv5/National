package opv.proyecto.opvproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Jugador;
import opv.proyecto.opvproy.repositories.JugadorRespository;

@Service
public class JugadorService {
    @Autowired
    private JugadorRespository jugadorRespository;

    public Jugador añadir(Jugador jugador) {
        return jugadorRespository.save(jugador);
    }

    public List<Jugador> obtenerTodos() {
        return jugadorRespository.findAll();
    }

    public Jugador obtenerPorDni(String dni) throws Exception {
        return jugadorRespository.findById(dni).orElseThrow(Exception::new);
    }

    public Jugador editar(Jugador jugador) {
        return jugadorRespository.save(jugador);
    }

    public void borrar(String dni) throws Exception {
        jugadorRespository.deleteById(dni);
    }
}