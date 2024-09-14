package com.dh.clinica.repository;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("Select t from Turno t join t.paciente p where p.apellido = :apellidoPaciente")
    List<Turno> buscarTurnoPorApellidoPaciente(String apellidoPaciente);

    List<Turno> findByFechaBetween(LocalDate fechaIni, LocalDate fechaFin);

    @Query("Select t from Turno t join t.paciente p where p.nombre LIKE %:parteNombre% and p.apellido LIKE %:parteApellido%")
    List<Turno> buscarTurnoPorNomApePaciente(String parteNombre, String parteApellido);
}
