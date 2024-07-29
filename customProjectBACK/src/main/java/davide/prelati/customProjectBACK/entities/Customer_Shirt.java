package davide.prelati.customProjectBACK.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer_Shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAcquired;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shirt_id")
    private Shirt shirt;

    public Customer_Shirt(LocalDate dateAcquired, int amount, Customer customer, Shirt shirt) {
        this.dateAcquired = dateAcquired;
        this.amount = amount;
        this.customer = customer;
        this.shirt = shirt;
    }
}
