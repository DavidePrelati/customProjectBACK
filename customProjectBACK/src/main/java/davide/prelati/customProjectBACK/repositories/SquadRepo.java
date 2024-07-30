package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Squad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SquadRepo extends JpaRepository<Squad, Long> {

    boolean existsById(Long id);

    boolean existsByName(String username);

    @Query("SELECT sq FROM Squad sq WHERE sq.nation.id = :nationId")
    Page<Squad> filterByNationId(@Param("nationId") Long nationId, Pageable pageable);

    @Query("SELECT sq FROM Squad sq ORDER BY nationId")
    Page<Squad> orderByNationId(Pageable pageable);

    @Query("SELECT sq FROM Squad sq ORDER BY name")
    Page<Squad> orderByName(Pageable pageable);

    @Query("SELECT sq FROM Squad sq WHERE sq.name = :name")
    Page<Squad> filterByName(@Param("name") String name, Pageable pageable);

}
