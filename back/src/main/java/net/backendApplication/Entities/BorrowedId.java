package net.backendApplication.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class BorrowedId implements Serializable {

    @Column(name = "id_hardware")
    private int idHardware;

    @Column(name = "id_student")
    private int idStudent;

    // Getters and setters, equals and hashCode methods

    public int getIdHardware() {
        return idHardware;
    }

    public void setIdHardware(int idHardware) {
        this.idHardware = idHardware;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedId that = (BorrowedId) o;
        return idHardware == that.idHardware && idStudent == that.idStudent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHardware, idStudent);
    }
}