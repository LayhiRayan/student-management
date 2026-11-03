package com.example.student_management;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.DuplicateEmailException;
import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTests {
    @Mock StudentRepository repository;
    @InjectMocks StudentService service;

    @Test
    void createsStudentWhenEmailIsAvailable() {
        Student student = new Student("Rayan", "Layhi", "rayan@example.com");
        when(repository.existsByEmail(student.getEmail())).thenReturn(false);
        when(repository.save(student)).thenReturn(student);

        assertEquals(student, service.create(student));
        verify(repository).save(student);
    }

    @Test
    void rejectsDuplicateEmail() {
        Student student = new Student("Rayan", "Layhi", "rayan@example.com");
        when(repository.existsByEmail(student.getEmail())).thenReturn(true);

        assertThrows(DuplicateEmailException.class, () -> service.create(student));
        verify(repository, never()).save(student);
    }

    @Test
    void reportsMissingStudent() {
        when(repository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(42L));
    }
}
