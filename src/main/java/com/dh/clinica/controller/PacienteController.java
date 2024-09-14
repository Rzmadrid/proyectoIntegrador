package com.dh.clinica.controller;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.impl.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService)   {
        this.pacienteService = pacienteService;
    }
    // ingresa -> JSON -> jackson -> Objeto Paciente
    // salga -> Objeto Paciente -> jackson -> JSON
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPaciente(@Valid @RequestBody Paciente paciente){
        pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok( "{\"mensaje\": \"Paciente Registrado\"}");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@Valid @RequestBody Paciente paciente){
            pacienteService.modificarPaciente(paciente);
            return ResponseEntity.ok( "{\"mensaje\": \"El paciente fue modificado\"}");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"}");
    }

//    @GetMapping("/buscarApellidoNombre")
//    public ResponseEntity<List<Paciente>> buscarApellidoYNombre(@RequestParam String apellido,
//                                                                @RequestParam String nombre){
//        return ResponseEntity.ok(pacienteService.buscarPorApellidoyNombre(apellido, nombre));
//    }

    @GetMapping("/buscarNombre/{nombre}")
    public ResponseEntity<List<Paciente>> buscarNombreLike(@PathVariable String nombre){
        return ResponseEntity.ok(pacienteService.buscarLikeNombre(nombre));
    }

    @GetMapping("/buscarNombreApellido")
    public ResponseEntity<List<Paciente>> buscarNombreApellidoLikeP(@RequestParam String nombre,
                                                                @RequestParam String apellido){
        return ResponseEntity.ok(pacienteService.buscarNombreApellidoLikeP(nombre, apellido));
    }
}

