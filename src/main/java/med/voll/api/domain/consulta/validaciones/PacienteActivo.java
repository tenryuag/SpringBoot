package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendaConsulta datos){
        if (datos.idPaciente() == null){
            return;
        }

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if (!pacienteActivo){
            throw new ValidacionDeIntegridad("No se permiten agendar citas con pacientes inactivos");
        }
    }
}
