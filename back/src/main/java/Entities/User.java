package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    private long id;
    @Column(name="userName")
    private String userName;
    @Column(name="group")
    private String group;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
