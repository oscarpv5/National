package opv.proyecto.opvproy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opv.proyecto.opvproy.services.CampoService;
import opv.proyecto.opvproy.services.ClubService;
import opv.proyecto.opvproy.services.JugadorService;
import opv.proyecto.opvproy.services.LigaService;
import opv.proyecto.opvproy.services.PartidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class APIController {

    @Autowired
    LigaService ligaService;

    @Autowired
    ClubService clubService;

    @Autowired
    CampoService campoService;

    @Autowired
    JugadorService jugadorService;

    @Autowired
    PartidoService partidoService;

    @GetMapping("/auth/admin") 
    public ResponseEntity<?> comprobarAdmin() {
        return ResponseEntity
            .ok()
            .build();
    }

    @GetMapping ("/liga")
    public ResponseEntity<?> obtenerLigas() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(ligaService.obtenerTodos());
    }

    @GetMapping ("/club")
    public ResponseEntity<?> obtenerClub() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(clubService.obtenerTodos());
    }

    @GetMapping ("/campo")
    public ResponseEntity<?> obtenerCampo() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(campoService.obtenerTodos());
    }

    @GetMapping ("/jugador")
    public ResponseEntity<?> obtenerJugador() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(jugadorService.obtenerTodos());
    }

    @GetMapping ("/partido")
    public ResponseEntity<?> obtenerPartido() {
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(partidoService.obtenerTodos());
    }
    
}