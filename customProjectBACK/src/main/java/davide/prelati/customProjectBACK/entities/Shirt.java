package davide.prelati.customProjectBACK.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int number;
    private double price;
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "squad_id")
    @JsonIgnore
    private Squad squad;

    @OneToMany(mappedBy = "shirt")
    private Set<Customer_Shirt> customerShirts;

    public Shirt(String name, int number, double price, String urlImage, Squad squad) {
        this.name = name;

        this.number = number;
        this.price = price;
        this.urlImage = urlImage;
        this.squad = squad;
    }
}
