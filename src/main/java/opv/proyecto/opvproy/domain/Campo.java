package opv.proyecto.opvproy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "nombre")

@Entity
public class Campo {
    @Id
    @NotBlank
    private String nombre;

    @NotBlank
    private String ubicacion;

    @ManyToOne
    @NonNull
    @NotNull
    private Club club;
}