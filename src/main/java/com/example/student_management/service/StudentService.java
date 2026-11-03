package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.DuplicateEmailException;
import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Student> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'identifiant " + id));
    }

    public Student create(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException("Cette adresse e-mail est déjà utilisée.");
        }
        return repository.save(student);
    }

    public Student update(Long id, Student input) {
        Student student = findById(id);
        repository.findByEmail(input.getEmail()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new DuplicateEmailException("Cette adresse e-mail est déjà utilisée.");
            }
        });
        student.setFirstName(input.getFirstName());
        student.setLastName(input.getLastName());
        student.setEmail(input.getEmail());
        return repository.save(student);
    }

    public void delete(Long id) { repository.delete(findById(id)); }
}
