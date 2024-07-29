package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.entities.UserRole;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.UserRequiredDTO;
import davide.prelati.customProjectBACK.payloads.UserRoleRequiredDTO;
import davide.prelati.customProjectBACK.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User saveUser(UserRequiredDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("Esiste già un utente con questa email: " + body.email());
        });

        this.userRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());
        });

        User newUser = new User(body.username(), body.email(), passwordEncoder.encode(body.password()), body.name(), body.surname());

        newUser.getUserRoles().add(userRoleService.findByName("USER"));

        User saved = userRepository.save(newUser);

        return saved;

    }

    public User addRole(long userId, UserRoleRequiredDTO body) {

        UserRole found = this.userRoleService.findByName(body.name());
        User user = this.findById(userId);

        user.addRole(found);
        return this.userRepository.save(user);
    }

    public User removeRole(long userId, UserRoleRequiredDTO body) {
        User found = this.findById(userId);
        UserRole role = this.userRoleService.findByName(body.name());
        found.removeRole(role);

        return this.userRepository.save(found);
    }

    public Page<User> getUsers(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("Utente con questo id non trovato");
        }
    }

    public User findByIdAndUpdate(long userId, UserRequiredDTO body) {
        User found = findById(userId);
        found.setEmail(body.email());
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setPassword(passwordEncoder.encode(body.password()));
        return userRepository.save(found);
    }

    public void findByIdAndDelete(long userId) {
        User found = findById(userId);
        this.userRepository.delete(found);
    }

    public User findById(long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

}

