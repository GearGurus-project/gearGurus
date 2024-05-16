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
@Table(name="hardware")
public class Hardware {
    @Id
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="barcode")
    private String barCode;
    @Column(name="description")
    private String description;
    @Column(name="datePurchase")
    private String datePurchase;
    @Column(name="price")
    private String price;
    @Column(name="provider")
    private String provider;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
