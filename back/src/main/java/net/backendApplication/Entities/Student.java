package net.backendApplication.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="group_student")
    private String group_student;
    @Column(name="email")
    private String email;
    @Column(name="student_number")
    private String student_number;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
