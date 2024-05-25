package opv.proyecto.opvproy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import opv.proyecto.opvproy.dto.Formulario;
import opv.proyecto.opvproy.services.Servicio;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

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

    @GetMapping("/cookies")
    public String getCookies() {
        return "legal/cookies";
    }

    @GetMapping("/copyright")
    public String getCopyright() {
        return "legal/copyright";
    }

    @GetMapping("/quienessomos")
    public String getQuienesSomos() {
        return "legal/quienessomos";
    }

    @GetMapping("/contacto")
    public String getContacto(Model model) {
        model.addAttribute("formulario", new Formulario());
        return "legal/contacto";
    }

    @PostMapping("/contacto/submit")
    public String postContacto(@Valid Formulario formulario) {
        return "legal/enviado";
    }
}