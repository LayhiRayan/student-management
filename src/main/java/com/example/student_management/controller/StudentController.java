package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Étudiants", description = "Gestion des étudiants")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    @Operation(summary = "Lister tous les étudiants")
    public List<Student> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un étudiant par son identifiant")
    public Student findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un étudiant")
    public Student create(@Valid @RequestBody Student student) { return service.create(student); }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un étudiant")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un étudiant")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
