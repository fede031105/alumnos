package com.upiiz.alumnos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Regla de negocio: Boleta única
    @Column(unique = true, nullable = false)
    private String boleta;

    private String nombre;

    // Regla de negocio: Correo único
    @Column(unique = true, nullable = false)
    private String correo;

    private int edad;

    // Construotores, Geters y Setters (si usas Lombok, solo pon @Data arriba de la clase)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBoleta() { return boleta; }
    public void setBoleta(String boleta) { this.boleta = boleta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}