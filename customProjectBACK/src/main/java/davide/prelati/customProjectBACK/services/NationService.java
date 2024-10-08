package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Nation;
import davide.prelati.customProjectBACK.entities.Squad;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.NationDTO;
import davide.prelati.customProjectBACK.repositories.NationRepo;
import davide.prelati.customProjectBACK.repositories.SquadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationService {

    @Autowired
    private NationRepo nationRepo;

    @Autowired
    private SquadRepo squadRepo;

    public Optional<Nation> findNationByName(String name) {
        return nationRepo.findByName(name);
    }

    public List<Squad> findSquadsByNation(String nationName) {
        return squadRepo.findByNationName(nationName);
    }

    public List<Nation> getAllNation() {
        return nationRepo.findAll();
    }

    public Nation findByName(String name) {
        return this.nationRepo.findByName(name).orElseThrow(() -> new BadRequestException("Il ruolo " + name + " non esiste"));
    }

    public Nation saveNation(NationDTO body) {
        if (this.nationRepo.existsByName(body.name())) {
            throw new BadRequestException("Esiste già una nazione con questo nome!");
        }
        Nation customer = new Nation(body.name(), body.url());

        return nationRepo.save(customer);
    }

    public Nation findById(long customerId) {
        return nationRepo.findById(customerId).orElseThrow(() -> new NotFoundException(customerId));
    }


    public Nation findByIdAndUpdate(long customerId, NationDTO body) {
        Nation found = findById(customerId);

        found.setName(body.name());

        return nationRepo.save(found);
    }


    public Page<Nation> orderByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return nationRepo.orderByName(pageable);
    }


    public Page<Nation> filterByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return nationRepo.filterByName(name, pageable);
    }

    public void findByIdAndDelete(long nationId) {
        Nation found = findById(nationId);
        this.nationRepo.delete(found);
    }
}
