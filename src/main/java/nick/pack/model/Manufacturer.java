package nick.pack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;
    @OneToMany (mappedBy = "manufacturer")
    Set<Product> set;

    @Override
    public String toString(){
        return name;
    }
}
