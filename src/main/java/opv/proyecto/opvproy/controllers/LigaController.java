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
import opv.proyecto.opvproy.domain.Liga;
import opv.proyecto.opvproy.services.LigaService;

@Controller
@RequestMapping("/liga")
public class LigaController {
    @Autowired
    LigaService ligaService;

    @GetMapping
    public String vistaLiga(Model model) {
        model.addAttribute("pagina", "/liga");
        model.addAttribute("lista", ligaService.obtenerTodos());
        model.addAttribute("ligaBuscados", ligaService.ligaBuscados());
        return "Liga/ListLiga";
    }

    @GetMapping("/borrar/{codigo}")
    public String showDelete(@PathVariable Integer codigo) {
        try {
            ligaService.borrar(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/liga";
    }

    @GetMapping("/editar/{codigo}")
    public String showEdit(@PathVariable Integer codigo, Model model) {
        Liga ligaForm;
        try {
            ligaForm = ligaService.obtenerPorCodigo(codigo);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("ligaForm", ligaForm);
        return "Liga/EditLigaView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Liga ligaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        ligaService.editar(ligaForm);
        return "redirect:/liga";
    }

    @GetMapping("/nuevo")
    public String showNewLiga(Model model) {
        model.addAttribute("ligaForm", new Liga());
        return "Liga/NewLigaView";
    }

    @PostMapping("/nuevo")
    public String showNewLigaSubmit(@Valid Liga ligaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        ligaService.añadir(ligaForm);
        return "redirect:/liga";
    }
}
