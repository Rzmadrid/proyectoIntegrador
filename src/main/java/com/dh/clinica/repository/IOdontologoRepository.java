package com.dh.clinica.repository;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer>{

   @Query("Select o from Odontologo o where o.nombre LIKE %:parteNombre% and o.apellido LIKE %:parteApellido%")
    List<Odontologo> findByNombreApellidoLike(String parteNombre, String parteApellido);

    @Query("Select o from Odontologo o where o.nombre LIKE %:parteNombre% ")
    List<Odontologo> findByNombreLike(String parteNombre);
}
