package med.voll.api.domain.direccion;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;


public record DatosDireccion(

        @NotBlank
        String calle,

        @NotBlank
        String distrito,

        @NotBlank
        String ciudad,

        @NotBlank
        String numero,

        @NotBlank
        String complemento) {
}
