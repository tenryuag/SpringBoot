package med.voll.api.domain.direccion;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;


public record DatosDireccion(
        @OneToMany
        @NotBlank
        String calle,
        @OneToMany
        @NotBlank
        String distrito,
        @OneToMany
        @NotBlank
        String ciudad,
        @OneToMany
        @NotBlank
        String numero,
        @OneToMany
        @NotBlank
        String complemento) {
}
