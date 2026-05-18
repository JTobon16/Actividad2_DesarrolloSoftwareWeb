package com.unicartagena.actividad2_desarrollosoftwareweb.service;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.EntradaCine;
import java.util.List;
import java.util.Optional;

// Contrato que define las operaciones disponibles para EntradaCine
public interface EntradaCineService {

    // Obtener todas las entradas
    List<EntradaCine> findAll();

    // Buscar entrada por ID
    Optional<EntradaCine> findById(String id);

    // Crear o actualizar entrada
    EntradaCine save(EntradaCine entradaCine);

    // Eliminar entrada por ID
    void deleteById(String id);

    // Verificar si existe una entrada por ID
    boolean existsById(String id);
}