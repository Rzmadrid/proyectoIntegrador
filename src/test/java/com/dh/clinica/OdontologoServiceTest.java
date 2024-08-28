package com.dh.clinica;

import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OdontologoServiceTest {
    static Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void cargarTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Testear el registro de un odontologo en la base de datos")
    void caso1(){
        //dado
        Odontologo odontologo = new Odontologo(3,"ra","zu");
        // cuando
        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
        // entonces
        assertNotNull(odontologoDesdeDB.getId());
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