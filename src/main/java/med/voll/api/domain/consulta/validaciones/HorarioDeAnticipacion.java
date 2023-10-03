package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{

    public void validar(DatosAgendaConsulta datos){
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();

        var diferenciaDe30min = Duration.between(ahora, horaDeConsulta).toMinutes()<30;

        if (diferenciaDe30min){
            throw new ValidacionDeIntegridad("Las consultas deben programarse con al menos 30min antes de la hora");
        }
    }

}
