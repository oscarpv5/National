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
import opv.proyecto.opvproy.domain.Rol;
import opv.proyecto.opvproy.domain.Usuario;
import opv.proyecto.opvproy.dto.NuevoUsuario;
import opv.proyecto.opvproy.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String vistaUsuario(Model model) {
        model.addAttribute("roles", Rol.values());
        model.addAttribute("lista", usuarioService.obtenerTodos());
        model.addAttribute("usuarioForm", new Usuario());
        return "Usuario/ListUsuario";
    }

    @GetMapping("/perfil")
    public String editarPerfil (Model model) {
        model.addAttribute("usuarioForm", usuarioService.obtenerUsuarioActual().get());
        return "Usuario/EditPerfil";
    }

    @GetMapping("/borrar/{dni}")
    public String showDelete(@PathVariable String dni) {
        try {
            usuarioService.borrar(dni);
        } catch (Exception e) {
            return "error/404";
        }
        return "redirect:/usuario";
    }

    @GetMapping("/editar/{dni}")
    public String showEdit(@PathVariable String dni, Model model) {
        Usuario usuarioForm;
        try {
            usuarioForm = usuarioService.obtenerPorDni(dni);
        } catch (Exception e) {
            return "error/404";
        }
        model.addAttribute("usuarioForm", usuarioForm);
        return "Usuario/EditUsuarioView";
    }

    @PostMapping("/editar")
    public String showEditSubmit(@Valid Usuario usuarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        usuarioService.editar(usuarioForm);
        return "redirect:/usuario";
    }

    @PostMapping("/nuevo")
    public String showNewUsuarioSubmit(@Valid Usuario usuarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "error/500";
        usuarioService.añadir(usuarioForm);
        return "redirect:/usuario";
    }

    @GetMapping("/registro")
    public String showRegistro(Model model) {
        model.addAttribute("usuarioForm", new NuevoUsuario());
        return "registroView";
    }

    @PostMapping("/registro")
    public String showRegistroSubmit(NuevoUsuario nuevoUsuario, Model model) {
        usuarioService.nuevoUsuario(nuevoUsuario);
        return "redirect:/";
    }
}