package opv.proyecto.opvproy.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorDni(String dni) throws Exception {
        return usuarioRepository.findById(dni).orElseThrow(Exception::new);
    }

    public Usuario editar(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public void borrar(String dni) throws Exception {
        usuarioRepository.deleteById(dni);
    }

    public void nuevoUsuario(NuevoUsuario nuevoUsuario) {
        Usuario usuario = modelMapper.map(nuevoUsuario, Usuario.class);
        usuario.setRol(Rol.USER);
        añadir(usuario);
    }
}