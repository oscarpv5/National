package opv.proyecto.opvproy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")

@Entity
public class Liga {
    @Id
    @NotNull
    private Integer codigo;

    @NotBlank
    private String comunidadAutonoma;

    @NotBlank
    private String region;
}