package com.unicartagena.actividad2_desarrollosoftwareweb.service.impl;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.EntradaCine;
import com.unicartagena.actividad2_desarrollosoftwareweb.repository.EntradaCineRepository;
import com.unicartagena.actividad2_desarrollosoftwareweb.service.EntradaCineService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Le dice a Spring que esta clase es un servicio
@Service
public class EntradaCineServiceImpl implements EntradaCineService {

    // Inyeccion del repositorio para acceder a la BD
    private final EntradaCineRepository entradaCineRepository;

    // Constructor — Spring inyecta el repositorio automaticamente
    public EntradaCineServiceImpl(EntradaCineRepository entradaCineRepository) {
        this.entradaCineRepository = entradaCineRepository;
    }

    @Override
    public List<EntradaCine> findAll() {
        // Trae todas las entradas de la BD
        return entradaCineRepository.findAll();
    }

    @Override
    public Optional<EntradaCine> findById(String id) {
        // Busca una entrada por su ID
        return entradaCineRepository.findById(id);
    }

    @Override
    public EntradaCine save(EntradaCine entradaCine) {
        // Guarda o actualiza una entrada en la BD
        return entradaCineRepository.save(entradaCine);
    }

    @Override
    public void deleteById(String id) {
        // Elimina una entrada por su ID
        entradaCineRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        // Verifica si existe una entrada con ese ID
        return entradaCineRepository.existsById(id);
    }
}