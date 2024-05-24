package net.backendApplication.Entities;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="hardware")
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="barcode")
    private String barCode;
    @Column(name="model")
    private String model;
    @Column(name="datePurchase")
    private String datePurchase;
    @Column(name="price")
    private String price;
    @Column(name="provider")
    private String provider;
    @Column(name="category")
    private String category;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
