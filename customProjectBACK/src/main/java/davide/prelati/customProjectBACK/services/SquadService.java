package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Squad;
import davide.prelati.customProjectBACK.repositories.SquadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadService {

    @Autowired
    private SquadRepo squadRepo;

    public List<Squad> findAll() {
        return squadRepo.findAll();
    }

    public Optional<Squad> findAllById(Long id) {
        return squadRepo.findById(id);
    }

    public Squad saveSquad(Squad squad) {
        return squadRepo.save(squad);
    }

    public void deleteById(Long id) {
        squadRepo.deleteById(id);
    }

    public List<Squad> findByIdNation(Long nationId) {
        return squadRepo.findByNationId(nationId);
    }


}
