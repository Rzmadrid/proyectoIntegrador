package com.dh.clinica;

import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    Odontologo odontologo;
    Odontologo odontologoDesdeDb;

    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setApellido("Zúñiga");
        odontologo.setNombre("Ignacio");
        odontologo.setNroMatricula(123456);

        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
    }

    @Test
    @DisplayName("Testear el registro de un odontologo en la base de datos")
    void caso1(){
        //dado
       // Odontologo odontologo = new Odontologo(3,"ra","zu");
        // cuando
       // Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
        // entonces
       assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que liste todos los odontologos")
    void caso2(){
        //dado
        List<Odontologo> odontologos = new ArrayList<>();
        //cuando
        odontologos = odontologoService.buscarTodos();
        //entonces
        assertTrue(odontologos.size()!= 0);
    }

}