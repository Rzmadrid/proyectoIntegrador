package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("Paciente Registrado "+ paciente);
        return pacienteRepository.save(paciente);

    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();

    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente.getId());
        if(pacienteEncontrado.isPresent()) {
            pacienteRepository.save(paciente);
            logger.info("Paciente Modificado "+ paciente);
        }else {
            throw new ResourceNotFoundException("El paciente "+ paciente.getId() +" no fue encontrado");
        }
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            pacienteRepository.deleteById(id);
            logger.info("Paciente id "+ id +" Eliminado ");
        } else {
            logger.info("El paciente "+ id +" no fue encontrado");
            throw new ResourceNotFoundException("El paciente "+ id +" no fue encontrado");
        }

    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Paciente> buscarLikeNombre(String nombre) {
        return pacienteRepository.findByNombreLike(nombre);
    }

    @Override
    public List<Paciente> buscarNombreApellidoLikeP(String parteNombre, String parteApellido) {
        return pacienteRepository.findByNombreApellidoLikeP(parteNombre,parteApellido);
    }


}
