package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendaConsulta datos){

        if (datos.idMedico() == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if (medicoConConsulta){
            throw new ValidacionDeIntegridad("Este medico ya tiene una consulta en ese horario");
        }

    }

}
