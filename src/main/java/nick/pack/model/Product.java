package nick.pack.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int cost;
    private String image;
    @Column(name = "is_active")
    private int isActive;


    @OneToMany (mappedBy = "product")
    Set<ProductSale> productSaleSet;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "manufacturer_id")
    private Manufacturer manufacturer;


    public Product(String name, int cost, String image, Manufacturer manufacturer, int isActive){
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.manufacturer = manufacturer;
        this.isActive = isActive;
    }
    public Product(){}

    @Override
    public String toString(){
        return name;
    }
}
