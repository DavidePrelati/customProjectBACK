package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Shirt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShirtRepo extends JpaRepository<Shirt, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);

    List<Shirt> findBySquadName(String squadName);

    @Query("SELECT s FROM Shirt s WHERE s.squad.id = :squadId")
    List<Shirt> findBySquadId(@Param("squadId") Long squadId);

    @Query("SELECT s FROM Shirt s ORDER BY name")
    Page<Shirt> orderByName(Pageable pageable);


    @Query("SELECT s FROM Shirt s ORDER BY price")
    Page<Shirt> orderByPrice(Pageable pageable);

    @Query("SELECT s FROM Shirt s WHERE s.name = :name")
    Page<Shirt> filterByName(@Param("name") String name, Pageable pageable);

   
    @Query("SELECT s FROM Shirt s WHERE s.price = :price")
    Page<Shirt> filterByPrice(@Param("price") String price, Pageable pageable);

    void delete(Shirt found);
}
