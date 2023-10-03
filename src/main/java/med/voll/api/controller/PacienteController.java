package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity<DatosDetallesPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(datosRegistroPaciente);
        pacienteRepository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesPaciente(paciente));

    }

    @GetMapping
    @Operation(summary = "Obtiene el listado para los pacientes")
    public ResponseEntity<Page<DatosDetallesPaciente>> listadoPaciente(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        var page = pacienteRepository.findAllByActivoTrue(paginacion).map(DatosDetallesPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarPaciente datos) {
        var paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }


    // DELETE logico
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un paciente a partir del ID")
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

// DELETE en base de datos
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "obtiene los detalles para el paciente con el ID indicado")
    public ResponseEntity detallar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }

}
