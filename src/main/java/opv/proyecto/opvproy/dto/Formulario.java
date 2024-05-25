package opv.proyecto.opvproy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Formulario {
    private String nombre;

    private String mail;

    private String asunto;

    private String mensaje;
}
