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
import opv.proyecto.opvproy.domain.Partido;
import opv.proyecto.opvproy.services.PartidoService;

@Controller
@RequestMapping("/partido")
public class PartidoController {
    @Autowired
    PartidoService partidoService;

    @GetMapping
    public String vistaPartido(Model model) {
        model.addAttribute("pagina", "/partido");
        model.addAttribute("lista", partidoService.obtenerTodos());
        return "Partido/listPartido";
    }

    @GetMapping("/borrar/{codigo}")
    public String showDelete(@PathVariable Integer codigo) {
        try {
            partidoService.borrar(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/partido";
    }

    @GetMapping("/editar/{codigo}")
    public String showEdit(@PathVariable Integer codigo, Model model) {
        Partido partidoForm;
        try {
            partidoForm = partidoService.obtenerPorCodigo(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("partidoForm", partidoForm);
        return "Partido/EditPartidoView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Partido partidoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
            try {
                partidoService.editar(partidoForm);
            } catch (NullPointerException e) {
                return "error/500";
            }
        return "redirect:/partido";
    }

    @GetMapping("/nuevo")
    public String showNewPartido(Model model) {
        model.addAttribute("partidoForm", new Partido());
        return "Partido/NewPartidoView";
    }

    @PostMapping("/nuevo")
    public String showNewPartidoSubmit(@Valid Partido partidoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
            try {
                partidoService.añadir(partidoForm);
            } catch (NullPointerException e) {
                return "error/500";
            }
        return "redirect:/partido";
    }
}