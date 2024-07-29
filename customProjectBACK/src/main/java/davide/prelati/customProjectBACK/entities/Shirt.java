package davide.prelati.customProjectBACK.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private int number;
    private double price;
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @OneToMany(mappedBy = "shirt")
    private Set<Customer_Shirt> customerShirts;

    public Shirt(String name, String size, int number, double price, String urlImage) {
        this.name = name;
        this.size = size;
        this.number = number;
        this.price = price;
        this.urlImage = urlImage;
        this.squad = squad;
    }
}
