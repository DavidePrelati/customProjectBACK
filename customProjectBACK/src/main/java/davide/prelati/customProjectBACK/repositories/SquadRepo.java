package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SquadRepo extends JpaRepository<Squad, Long> {
    @Query("SELECT sq FROM Squad sq WHERE sq.nation.id = :nationId")
    List<Squad> findByNationId(@Param("nationId") Long nationId);
}
