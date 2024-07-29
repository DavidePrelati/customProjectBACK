package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.UserRole;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.UserRoleRequiredDTO;
import davide.prelati.customProjectBACK.repositories.UserRoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    public UserRole findByName(String name) {
        return this.userRoleRepo.findByName(name).orElseThrow(() -> new BadRequestException("Il ruolo " + name + " non esiste"));
    }

    public UserRole saveRole(UserRoleRequiredDTO body) {
        this.userRoleRepo.findByName(body.name()).ifPresent(userRole ->
        {
            throw new BadRequestException("Esiste già un ruolo con questo nome: " + body.name());
        });

        UserRole newRole = new UserRole(body.name());
        return userRoleRepo.save(newRole);
    }

    public Page<UserRole> getRoles(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRoleRepo.findAll(pageable);
    }

    public UserRole getRole(long id) {
        Optional<UserRole> optionalUser = userRoleRepo.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("Ruolo con questo id non trovato");
        }
    }

    public void findByIdAndDelete(long roleId) {
        UserRole found = findById(roleId);
        this.userRoleRepo.delete(found);
    }

    public UserRole findById(long roleId) {
        return this.userRoleRepo.findById(roleId).orElseThrow(() -> new NotFoundException(roleId));
    }

    public UserRole findByIdAndUpdate(long roleId, UserRoleRequiredDTO body) {
        UserRole found = findById(roleId);
        found.setName(body.name());
        return userRoleRepo.save(found);
    }
}

