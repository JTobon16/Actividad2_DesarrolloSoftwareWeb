package com.unicartagena.actividad2_desarrollosoftwareweb.repository;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Le dice a Spring que esta interfaz es un repositorio
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // User = modelo que maneja, String = tipo del ID

    // Spring genera automaticamente el SQL:
    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}