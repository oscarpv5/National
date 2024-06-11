package opv.proyecto.opvproy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import opv.proyecto.opvproy.domain.Jugador;
import opv.proyecto.opvproy.services.ClubService;
import opv.proyecto.opvproy.services.JugadorService;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    @Autowired
    ClubService clubService;

    @GetMapping
    public String vistaJugadores(Model model) {
        model.addAttribute("pagina", "/jugadores");
        model.addAttribute("jugadorForm", new Jugador());
        model.addAttribute("clubs", clubService.obtenerTodos());
        return "Jugadores/ListJugadores";
    }

    @GetMapping("/borrar/{dni}")
    public String showDelete(@PathVariable String dni) {
        try {
            jugadorService.borrar(dni);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{dni}")
    public String showEdit(@PathVariable String dni, Model model) {
        Jugador jugadorForm;
        try {
            jugadorForm = jugadorService.obtenerPorDni(dni);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("jugadorForm", jugadorForm);
        return "Jugadores/EditJugadorView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Jugador jugadorForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        try {
            jugadorService.editar(jugadorForm);
        } catch (Exception e) {
            return "error/500";
        }
        return "redirect:/jugadores";
    }

    @PostMapping("/nuevo")
    public String showNewJugadorSubmit(@Valid Jugador jugadorForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        try {
            jugadorService.añadir(jugadorForm);
        } catch (Exception e) {
            return "error/500";
        }
        return "redirect:/jugadores";
    }
}