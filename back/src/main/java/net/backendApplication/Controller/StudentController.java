package net.backendApplication.Controller;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Entities.Student;
import net.backendApplication.Repository.StudentRepository;
import net.backendApplication.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentServices studentService;
    @GetMapping()
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    @PostMapping
    public Student createstudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
    @PutMapping("/{id}")
    public Student updatestudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingstudent = studentService.getStudentById(id);
        if (existingstudent != null) {
            existingstudent.setEmail(existingstudent.getEmail());
            existingstudent.setGroup_student(existingstudent.getGroup_student());
            existingstudent.setLastName(existingstudent.getLastName());
            existingstudent.setFirstName(existingstudent.getFirstName());
            existingstudent.setStudentNumber(existingstudent.getStudentNumber());
            return studentService.saveStudent(existingstudent);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deletestudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
    @GetMapping("/number/{studentNumber}")
    public ResponseEntity<Student> getStudentByStudentNumber(@PathVariable String studentNumber) {
        return studentService.getStudentByStudentNumber(studentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
