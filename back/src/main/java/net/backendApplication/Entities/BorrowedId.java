package net.backendApplication.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BorrowedId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_hardware")
    private Long idHardware;

    @Column(name = "id_student")
    private Long idStudent;

    // Getters and setters, equals and hashCode methods

    public Long getIdHardware() {
        return idHardware;
    }

    public void setIdHardware(Long idHardware) {
        this.idHardware = idHardware;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedId that = (BorrowedId) o;
        return Objects.equals(idHardware, that.idHardware) && Objects.equals(idStudent, that.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHardware, idStudent);
    }
}