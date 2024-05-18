package opv.proyecto.opvproy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "dni")

@Entity
public class Usuario {
    @Id
    @NotNull
    private String dni;

    @Column(unique = true)
    @NotNull
    private String nombre;

    @NotNull
    private String contrasena;

    @Email
    @NotNull
    private String email;

    private Rol rol;
}