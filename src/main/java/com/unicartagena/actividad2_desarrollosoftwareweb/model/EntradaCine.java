package com.unicartagena.actividad2_desarrollosoftwareweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrada_cine")
public class EntradaCine {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "fechaCompra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "fechaEntrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "pelicula", nullable = false, length = 150)
    private String pelicula;

    @Column(name = "puesto", nullable = false, length = 10)
    private String puesto;

    @Column(name = "sala", nullable = false, length = 50)
    private String sala;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "cine", nullable = false, length = 100)
    private String cine;

    @Column(name = "pais", nullable = false, length = 100)
    private String pais;

    @Column(name = "departamento", nullable = false, length = 100)
    private String departamento;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "centroComercial", nullable = false, length = 150)
    private String centroComercial;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}