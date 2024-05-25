package net.backendApplication.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role; // Celui qui met supprime, ou celui qui attribue aux students

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
