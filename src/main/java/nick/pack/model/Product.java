package nick.pack.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int cost;
    private String image;
    @Column (name = "is_active")
    private int isActive;
    @OneToMany (mappedBy = "product")
    Set<ProductSale> set;
    @ManyToOne
    @JoinColumn (name = "manufacturer_id")
    private Manufacturer manufacturerId;

    @Override
    public String toString(){
        return name;
    }
}
