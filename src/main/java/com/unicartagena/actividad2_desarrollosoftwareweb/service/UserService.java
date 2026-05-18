package com.unicartagena.actividad2_desarrollosoftwareweb.service;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.User;
import java.util.List;
import java.util.Optional;

// Contrato que define las operaciones disponibles para User
public interface UserService {

    // Obtener todos los usuarios
    List<User> findAll();

    // Buscar usuario por ID
    Optional<User> findById(String id);

    // Buscar usuario por email
    Optional<User> findByEmail(String email);

    // Crear o actualizar usuario
    User save(User user);

    // Eliminar usuario por ID
    void deleteById(String id);

    // Verificar si existe un usuario por ID
    boolean existsById(String id);
}