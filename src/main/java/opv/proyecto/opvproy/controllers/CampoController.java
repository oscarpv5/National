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
import opv.proyecto.opvproy.domain.Campo;
import opv.proyecto.opvproy.services.CampoService;
import opv.proyecto.opvproy.services.ClubService;

@Controller
@RequestMapping("/campo")
public class CampoController {

    @Autowired
    CampoService campoService;
    
    @Autowired
    ClubService clubService;

    @GetMapping
    public String vistaCampo(Model model) {
        model.addAttribute("pagina", "/campo");
        model.addAttribute("campoForm", new Campo());
        model.addAttribute("clubs", clubService.obtenerTodos());
        return "Campo/ListCampo";
    }

    @GetMapping("/borrar/{nombre}")
    public String showDelete(@PathVariable String nombre) {
        try {
            campoService.borrar(nombre);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/campo";
    }

    @GetMapping("/editar/{nombre}")
    public String showEdit(@PathVariable String nombre, Model model) {
        Campo campoForm;
        try {
            campoForm = campoService.obtenerPorNombre(nombre);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("campoForm", campoForm);
        return "Campo/EditCampoView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Campo campoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        try {
            campoService.editar(campoForm);
        } catch (NullPointerException e) {
            return "error/500";
        }
        return "redirect:/campo";
    }

    @PostMapping("/nuevo")
    public String showNewCampoSubmit(@Valid Campo campoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        try {
            campoService.añadir(campoForm);
        } catch (NullPointerException e) {
            return "error/500";
        }
        return "redirect:/campo";
    }
}