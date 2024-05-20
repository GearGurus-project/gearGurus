package net.backendApplication.Controller;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Entities.Student;
import net.backendApplication.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
