package nick.pack.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "product_sale")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sale_date")
    private LocalDate saleDate;
    private int quantity;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "product_id")
    private Product product;

    @Override
    public String toString(){
        return product + " - " + saleDate;
    }
}
