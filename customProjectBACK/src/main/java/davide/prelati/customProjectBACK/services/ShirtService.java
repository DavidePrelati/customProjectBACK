package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Shirt;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.ShirtDTO;
import davide.prelati.customProjectBACK.repositories.ShirtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShirtService {

    @Autowired
    private ShirtRepo shirtRepo;

    public Shirt saveShirt(ShirtDTO body) {
        if (this.shirtRepo.existsByName(body.name())) {
            throw new BadRequestException("Esiste già un cliente con questo username!");
        }

        Shirt shirt = new Shirt(body.name(), body.size(), body.number(), body.price(), body.urlImage());

        return shirtRepo.save(shirt);
    }

    public List<Shirt> getAllShirt() {
        return shirtRepo.findAll();
    }


    public Shirt findById(long shirtId) {
        return shirtRepo.findById(shirtId).orElseThrow(() -> new NotFoundException(shirtId));
    }

    public Shirt findByIdAndUpdate(long shirtId, ShirtDTO body) {
        Shirt found = findById(shirtId);


        found.setName(body.name());
        found.setSize(body.size());
        found.setNumber(body.number());
        found.setPrice(body.price());
        found.setUrlImage(body.urlImage());

        return shirtRepo.save(found);
    }

    public void findByIdAndDelete(long userId) {
        Shirt found = findById(userId);
        this.shirtRepo.delete(found);
    }

    public Page<Shirt> orderByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.orderByName(pageable);
    }

    public Page<Shirt> orderBySize(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.orderBySize(pageable);
    }

    public Page<Shirt> orderByPrice(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.orderByPrice(pageable);
    }

    public Page<Shirt> filterByName(int page, int size, String username) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.filterByName(username, pageable);
    }

    public Page<Shirt> filterBySize(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.filterBySize(name, pageable);
    }

    public Page<Shirt> filterByPrice(int page, int size, String surname) {
        Pageable pageable = PageRequest.of(page, size);
        return shirtRepo.filterByPrice(surname, pageable);
    }

    public List<Shirt> findAll() {
        return shirtRepo.findAll();
    }

    public List<Shirt> findByIdSquad(Long squad_id) {
        return shirtRepo.findBySquadId(squad_id);
    }
}
