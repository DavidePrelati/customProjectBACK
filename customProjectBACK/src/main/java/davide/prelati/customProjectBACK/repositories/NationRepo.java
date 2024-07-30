package davide.prelati.customProjectBACK.repositories;

import davide.prelati.customProjectBACK.entities.Nation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NationRepo extends JpaRepository<Nation, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    @Query("SELECT n FROM Nation n ORDER BY name")
    Page<Nation> orderByName(Pageable pageable);

    @Query("SELECT n FROM Nation n WHERE n.name = :name")
    Page<Nation> filterByName(@Param("name") String name, Pageable pageable);

    void delete(Nation found);
}
