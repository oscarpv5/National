package opv.proyecto.opvproy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import opv.proyecto.opvproy.services.Servicio;


@Controller
public class Controlador {
    @Autowired
    Servicio servicio;

    @GetMapping("/")
    public String vistaInicio(Model model) {
        model.addAttribute("pagina", "/index");
        return "index";
    }

    @GetMapping("/error/403")
    public String showError() {
        return "error/403";
    }
}