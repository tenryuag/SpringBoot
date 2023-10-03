package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;


    public void validar(DatosAgendaConsulta datos){
        if (datos.idMedico() == null){
            return;
        }

        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());

        if (!medicoActivo){
            throw new ValidacionDeIntegridad("No se permiten agendar citas con medicos inactivos");
        }
    }
}
