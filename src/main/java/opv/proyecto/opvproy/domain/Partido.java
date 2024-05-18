package opv.proyecto.opvproy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
    private Integer codigo;

    @NotNull
    private Integer jornada;

    @ManyToOne
    @NonNull
    @NotNull
    private Club local;

    @ManyToOne
    @NonNull
    @NotNull
    private Club visitante;
}