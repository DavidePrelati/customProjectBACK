package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    boolean existsById(Long id);

    boolean existsByUsername(String username);

    @Query("SELECT c FROM Customer c ORDER BY username")
    Page<Customer> orderByUsername(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY email")
    Page<Customer> orderByEmail(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY name")
    Page<Customer> orderByName(Pageable pageable);

    @Query("SELECT c FROM Customer c ORDER BY surname")
    Page<Customer> orderBySurname(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    Page<Customer> filterByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    Page<Customer> filterByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.surname = :surname")
    Page<Customer> filterBySurname(@Param("surname") String surname, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Page<Customer> filterByEmail(@Param("email") String email, Pageable pageable);

    void delete(Customer found);
}
