package com.dh.clinica.service;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(Integer id);
    List<Odontologo> buscarTodos();
    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);

   @Query("Select o from Odontologo o where o.nombre LIKE %:parteNombre% and o.apellido LIKE %:parteApellido%")
    List<Odontologo> buscarNombreApellidoLike(String parteNombre, String parteApellido);
   @Query("Select o from Odontologo o where o.nombre LIKE %:parteNombre% ")
   List<Odontologo> findByNombreLike(String parteNombre);
}
