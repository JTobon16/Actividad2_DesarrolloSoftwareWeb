package com.unicartagena.actividad2_desarrollosoftwareweb.repository;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.EntradaCine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Le dice a Spring que esta interfaz es un repositorio
@Repository
public interface EntradaCineRepository extends JpaRepository<EntradaCine, String> {
    // EntradaCine = modelo que maneja, String = tipo del ID

    // Spring genera automaticamente el SQL:
    // SELECT * FROM entrada_cine WHERE pelicula = ?
    // (metodos personalizados se agregan aqui si se necesitan)
}