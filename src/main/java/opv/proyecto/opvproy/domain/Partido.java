package opv.proyecto.opvproy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
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
@EqualsAndHashCode(of = "codigo")

@Entity
public class Partido {
    @Id
    @Min(1)
    private Integer codigo;

    @NotNull
    @Pattern(regexp = "[0-9]{1,}-[0-9]{1,}")
    private String resultado;

    @ManyToOne
    @NonNull
    @NotNull
    private Club local;

    @ManyToOne
    @NonNull
    @NotNull
    private Club visitante;
}