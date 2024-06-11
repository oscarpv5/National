package opv.proyecto.opvproy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Formulario {
    @NotBlank
    private String nombre;

    @NotBlank
    private String mail;

    @NotBlank
    private String asunto;

    @NotBlank
    private String mensaje;
}