package com.upiiz.alumnos.controller;

import com.upiiz.alumnos.model.Alumno;
import com.upiiz.alumnos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // Endpoint para agregar alumno
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarAlumno(@RequestBody Alumno alumno) {
        try {
            Alumno nuevoAlumno = alumnoService.agregarAlumno(alumno);
            return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para el reporte
    @GetMapping("/reporte-edades")
    public ResponseEntity<?> reporteEdades() {
        return new ResponseEntity<>(alumnoService.obtenerReportePorEdad(), HttpStatus.OK);
    }
}