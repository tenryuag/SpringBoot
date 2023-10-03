package med.voll.api.domain.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosListaPaciente(Long id, String nombre, String email, String documento) {

        public DatosListaPaciente(Paciente paciente){
                this(paciente.getId(),paciente.getNombre(),paciente.getEmail(),paciente.getDocumento());
        }

}
