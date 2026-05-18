package com.unicartagena.actividad2_desarrollosoftwareweb.service.impl;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.User;
import com.unicartagena.actividad2_desarrollosoftwareweb.repository.UserRepository;
import com.unicartagena.actividad2_desarrollosoftwareweb.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Le dice a Spring que esta clase es un servicio
@Service
public class UserServiceImpl implements UserService {

    // Inyeccion del repositorio para acceder a la BD
    private final UserRepository userRepository;

    // Constructor — Spring inyecta el repositorio automaticamente
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        // Trae todos los usuarios de la BD
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        // Busca un usuario por su ID
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // Busca un usuario por su email
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // Guarda o actualiza un usuario en la BD
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        // Elimina un usuario por su ID
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        // Verifica si existe un usuario con ese ID
        return userRepository.existsById(id);
    }
}