package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.Direccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, Especialidad especialidad, Direccion direccion) {

    public DatosRespuestaMedico(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEmail(),medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad(), medico.getDireccion());
    }

}
