package nick.pack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "product_sale")
public class ProductSale {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "sale_date")
    private LocalDate saleDate;
    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product productId;

    @Override
    public String toString(){
        return productId + " - " + saleDate;
    }
}
