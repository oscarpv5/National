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
import opv.proyecto.opvproy.domain.Club;
import opv.proyecto.opvproy.services.ClubService;
import opv.proyecto.opvproy.services.LigaService;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @Autowired
    LigaService ligaService;

    @GetMapping
    public String vistaClub(Model model) {
        model.addAttribute("pagina", "/club");
        model.addAttribute("clubForm", new Club());
        model.addAttribute("ligas", ligaService.obtenerTodos());
        return "Club/ListClub";
    }

    @GetMapping("/borrar/{codigo}")
    public String showDelete(@PathVariable Integer codigo) {
        try {
            clubService.borrar(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/club";
    }

    @GetMapping("/editar/{codigo}")
    public String showEdit(@PathVariable Integer codigo, Model model) {
        Club clubForm;
        try {
            clubForm = clubService.obtenerPorCodigo(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("clubForm", clubForm);
        return "Club/EditClubView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Club clubForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        try {
            clubService.editar(clubForm);
        } catch (NullPointerException e) {
            return "error/500";
        }
        return "redirect:/club";
    }

    @PostMapping("/nuevo")
    public String showNewClubSubmit(@Valid Club ClubForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        clubService.añadir(ClubForm);
        return "redirect:/club";
    }
}