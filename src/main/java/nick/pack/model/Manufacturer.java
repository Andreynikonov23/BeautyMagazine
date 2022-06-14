package nick.pack.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;
    @OneToMany(mappedBy = "manufacturer")
    Set<Product> set;

    public Manufacturer(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }
    public Manufacturer(){

    }


    @Override
    public String toString(){
        return name;
    }
}
