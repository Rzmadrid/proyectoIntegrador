package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exception.NewBadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }
    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo){
        if(odontologo.getNombre().isEmpty()){
            throw new NewBadRequestException("Debe Ingresar Nombre del odontologo");
        }
        logger.info("Odontologo guardado "+ odontologo);
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> buscarTodos(){
        return iOdontologoRepository.findAll();
    }
    @Override
    public Optional<Odontologo> buscarPorId(Integer id){

        return iOdontologoRepository.findById(id);
    }
    @Override
    public void modificarOdontologo(Odontologo odontologo){
        Optional<Odontologo> odontoloEncontrado = iOdontologoRepository.findById(odontologo.getId());
        if(odontoloEncontrado.isPresent()) {
            logger.info("Odontologo Modificado " + odontologo);
            iOdontologoRepository.save(odontologo);
        }else{
            throw new ResourceNotFoundException("El Odontologo "+ odontologo.getId() +" no fue encontrado");
        }
    }
    @Override
    public void eliminarOdontologo(Integer id){
//        if(id == 0){
//            throw new NewBadRequestException("Debe Ingresar un numero mayor a 0");
//        }
        Optional<Odontologo> odontoloEncontrado = iOdontologoRepository.findById(id);
        if(odontoloEncontrado.isPresent()){
            iOdontologoRepository.deleteById(id);
            logger.info("El Odontologo "+ id +" fue Eliminado");
        } else {
            logger.info("El Odontologo "+ id +" no fue encontrado");
            throw new ResourceNotFoundException("El Odontologo "+ id +" no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> findByNombreLike(String parteNombre) {
        return iOdontologoRepository.findByNombreLike(parteNombre);
    }

    @Override
    public List<Odontologo> buscarNombreApellidoLike(String parteNombre, String parteApellido) {
        return iOdontologoRepository.findByNombreApellidoLike(parteNombre, parteApellido);
    }


}
