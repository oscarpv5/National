package opv.proyecto.opvproy.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormInfo {
    @NotNull
    private String contacto;

    @NotNull
    private String CV;
}