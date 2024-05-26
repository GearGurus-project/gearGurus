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
@Table(name="hardware")
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Getter
    @Column(name="barcode")
    private String barCode;
    @Getter
    @Setter
    @Column(name="model")
    private String model;
    @Getter
    @Setter
    @Column(name="datePurchase")
    private String datePurchase;
    @Getter
    @Setter
    @Column(name="price")
    private String price;
    @Getter
    @Setter
    @Column(name="provider")
    private String provider;
    @Getter
    @Setter
    @Column(name="category")
    private String category;

    @OneToMany(mappedBy = "id.idHardware")
    private Set<Borrowed> borrowedRecords;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

}
