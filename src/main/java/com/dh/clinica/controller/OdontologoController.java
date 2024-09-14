package com.dh.clinica.controller;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.impl.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@Valid @RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@Valid @RequestBody Odontologo odontologo){

           odontologoService.modificarOdontologo(odontologo);
            return ResponseEntity.ok("{\"mensaje\": \"El Odontologo fue modificado\"}");
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarodontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El Odontologo fue eliminado\"}");
    }

   @GetMapping("/buscarNombreApellido")
    public ResponseEntity<List<Odontologo>> buscarLikeNombreApellido(@RequestParam String nombre, @RequestParam String apellido){
        return ResponseEntity.ok(odontologoService.buscarNombreApellidoLike(nombre, apellido));
    }
   @GetMapping("/buscarNombre/{nombre}")
   public ResponseEntity<List<Odontologo>> buscarNombreLike(@PathVariable String nombre){
       return ResponseEntity.ok(odontologoService.findByNombreLike(nombre));
   }
}
