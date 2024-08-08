package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Squad;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.SquadDTO;
import davide.prelati.customProjectBACK.repositories.SquadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquadService {

    @Autowired
    private SquadRepo squadRepo;

    @Autowired
    private NationService nationService;


    public Squad saveSquad(SquadDTO body) {
        if (this.squadRepo.existsByName(body.name())) {
            throw new BadRequestException("Esiste già un cliente con questo username!");
        }

        Squad squad = new Squad(body.name(), body.sponsor(), body.urlImage(), body.nation());

        return squadRepo.save(squad);
    }


    public List<Squad> getAllSquad() {
        return squadRepo.findAll();
    }

    public Squad findById(long squadId) {
        return squadRepo.findById(squadId).orElseThrow(() -> new NotFoundException(squadId));
    }


    public Squad findByIdAndUpdate(long squadId, SquadDTO body) {
        Squad found = findById(squadId);


        found.setName(body.name());
        found.setSponsor(body.sponsor());

        return squadRepo.save(found);
    }


    public Page<Squad> orderByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return squadRepo.orderByName(pageable);
    }


    public Page<Squad> filterByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return squadRepo.filterByName(name, pageable);
    }

    public void findByIdAndDelete(long userId) {
        Squad found = findById(userId);
        this.squadRepo.delete(found);
    }
}
