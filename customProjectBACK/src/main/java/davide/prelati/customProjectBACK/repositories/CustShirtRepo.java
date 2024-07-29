package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Customer_Shirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustShirtRepo extends JpaRepository<Customer_Shirt, Long> {
}
