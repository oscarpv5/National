package opv.proyecto.opvproy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NuevoUsuario {
    private String dni;

    private String nombre;

    private String contrasena;

    private String email;
}