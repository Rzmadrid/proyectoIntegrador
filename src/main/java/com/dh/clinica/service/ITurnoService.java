package com.dh.clinica.service;

import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<Turno> buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModificarDto turnoModificarDto);
    void eliminarTurno(Integer id);
    List<Turno> buscarTurnoPaciente(String apellidoPaciente);

    List<Turno> buscarTurnofechaIniFin(LocalDate fechaIni, LocalDate FechaFin);

    @Query("Select t from Turno t join t.paciente p where p.nombre LIKE %:parteNombre% and p.apellido LIKE %:parteApellido%")
    List<Turno> buscarTurnoPorNomApePacienteLike(String parteNombre, String parteApellido);
    //List<TurnoResponseDto> buscarTurnofechaIniFinDTO(LocalDate fechaIni, LocalDate FechaFin);
}
