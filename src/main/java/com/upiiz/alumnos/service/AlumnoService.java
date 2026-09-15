package com.upiiz.alumnos.service;

import com.upiiz.alumnos.model.Alumno;
import com.upiiz.alumnos.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Solo la acción de agregar un Alumno (con validación de reglas de negocio)
    public Alumno agregarAlumno(Alumno alumno) {
        if (alumnoRepository.existsByBoleta(alumno.getBoleta())) {
            throw new IllegalArgumentException("Error: La boleta ya está registrada.");
        }
        if (alumnoRepository.existsByCorreo(alumno.getCorreo())) {
            throw new IllegalArgumentException("Error: El correo ya está registrado.");
        }
        return alumnoRepository.save(alumno);
    }

    // Reporte de alumnos agrupados por edad
    public List<Map<String, Object>> obtenerReportePorEdad() {
        return alumnoRepository.countAlumnosByEdad();
    }
}