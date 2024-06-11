package opv.proyecto.opvproy.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import opv.proyecto.opvproy.domain.Rol;
import opv.proyecto.opvproy.domain.Usuario;
import opv.proyecto.opvproy.dto.NuevoUsuario;
import opv.proyecto.opvproy.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    public Usuario añadir(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena())); // codifica la contraseña antes de guardarla
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioActual() {
        var contexto = SecurityContextHolder.getContext(); // obtencion del contexto actual
        return contexto instanceof AnonymousAuthenticationToken
                ? Optional.empty()
                : usuarioRepository.findByNombre(contexto.getAuthentication().getName()); // si hay un usuario autenticado, se busca es usuario por su nombre
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorDni(String dni) throws Exception {
        return usuarioRepository.findById(dni).orElseThrow(Exception::new);
    }

    public Usuario editar(Usuario usuario) {
        Usuario usuarioActual = this.obtenerUsuarioActual()
                .orElseThrow(RuntimeException::new); // obtiene el usuario actual autenticado
        Usuario usuarioGuardado = usuarioRepository.findById(usuario.getDni())
                .orElseThrow(RuntimeException::new); // obtiene el usuario a editar por el dni

        if (!usuario.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena())); // si la contraseña no esta vacia se coficia, de lo contrario se mantiene la actual
        } else { 
            usuario.setContrasena(usuarioGuardado.getContrasena()); // si no es ADMIN se mantiene el usuario guardado
        }
        if (usuarioActual.getRol() != Rol.ADMIN) {
            usuario.setRol(usuarioGuardado.getRol()); // guarda el usuario actualizado
        }
        return usuarioRepository.save(usuario);
    }

    public void borrar(String dni) throws Exception {
        usuarioRepository.deleteById(dni);
    }

    public void nuevoUsuario(NuevoUsuario nuevoUsuario) {
        Usuario usuario = modelMapper.map(nuevoUsuario, Usuario.class);
        usuario.setRol(Rol.USER); // asignacion de USER al nuevo usuario
        añadir(usuario);
    }
}