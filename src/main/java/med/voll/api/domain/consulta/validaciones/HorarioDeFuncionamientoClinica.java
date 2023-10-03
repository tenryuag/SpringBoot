package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{
    public void validar(DatosAgendaConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour()<7;
        var despuesDeCierre = datos.fecha().getHour()>19;
        if (domingo || antesDeApertura || despuesDeCierre){
            throw new ValidacionDeIntegridad("El horario de atencion de la clinica es de lunes a sabado, de 7:00 a 19:00 horas");
        }
    }
}
