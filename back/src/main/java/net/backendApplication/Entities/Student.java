package net.backendApplication.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="student")
public class Student {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name="firstName")
    private String firstName;
    @Getter
    @Column(name="lastName")
    private String lastName;
    @Getter
    @Column(name="group_student")
    private String group_student;
    @Getter
    @Column(name="email")
    private String email;
    @Getter
    @Column(name="student_number")
    private String student_number;
    @Setter
    @OneToMany(mappedBy = "id.idStudent")
    private Set<Borrowed> borrowedRecords;

    public Set<Borrowed> getBorrowedRecords() {
        return borrowedRecords;
    }
}
