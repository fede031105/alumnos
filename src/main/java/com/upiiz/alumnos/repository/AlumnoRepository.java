package com.upiiz.alumnos.repository;

import com.upiiz.alumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Métodos para validar las reglas de negocio
    boolean existsByBoleta(String boleta);
    boolean existsByCorreo(String correo);

    // Consulta personalizada con JPA para reporte agrupado por edad
    @Query("SELECT a.edad as anos, COUNT(a) as cantidad FROM Alumno a GROUP BY a.edad")
    List<Map<String, Object>> countAlumnosByEdad();
}