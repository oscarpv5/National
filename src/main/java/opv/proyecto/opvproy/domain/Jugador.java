package opv.proyecto.opvproy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "dni")

@Entity
public class Jugador {
    @Id
    @NotBlank
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;

    @NotBlank
    private String nombre;

    @NotBlank
    private String ubicacion;

    @ManyToOne
    @NonNull
    @NotNull
    private Club club;
}