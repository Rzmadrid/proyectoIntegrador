package com.dh.clinica;


import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class TurnoServiceTest {

    @Autowired
    PacienteService pacienteService;
    @Autowired
    TurnoService turnoService;
    @Autowired
    OdontologoService odontologoService;

    Paciente paciente;
    TurnoRequestDto turno;
    Odontologo odontologo;
    Paciente pacienteDesdeDb;
    TurnoResponseDto turnoDesdeDb;
    Odontologo odontologoDesdeDb;
    @BeforeEach
    void crearPaciente(){
        Domicilio domicilio = new Domicilio(null,"Falsa", 456, "Cipolleti", "Rio Negro");
        paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655123");
        paciente.setFechaIngreso(LocalDate.of(2024, 7, 16));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
    }
    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setApellido("Zúñiga");
        odontologo.setNombre("Ignacio");
        odontologo.setNroMatricula(123456);

        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
    }
    @BeforeEach
    void crearTurno(){
        turno = new TurnoRequestDto();
        turno.setPaciente_id(1);
        turno.setOdontologo_id(1);
        turno.setFecha("2024-10-01");

        turnoDesdeDb = turnoService.guardarTurno(turno);
    }
    @Test
    @DisplayName("Testear que un Turno se guarde en la base de datos")
    void caso1(){
        //dado
        // cuando
        // entonces
        assertNotNull(turnoDesdeDb.getId());
    }




}