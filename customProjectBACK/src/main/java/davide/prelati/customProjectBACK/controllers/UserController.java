package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.UserRequiredDTO;
import davide.prelati.customProjectBACK.payloads.UserRoleRequiredDTO;
import davide.prelati.customProjectBACK.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User updateMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.userService.getUsers(page, size);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable long userId) {
        return this.userService.findById(userId);
    }

    @PatchMapping("/{userId}/roles/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public User addRole(@PathVariable long userId, @RequestBody @Validated UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userService.addRole(userId, body);
    }

    @PatchMapping("/{userId}/roles/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User removeRole(@PathVariable long userId, @RequestBody @Validated UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userService.removeRole(userId, body);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable long userId, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId) {
        this.userService.findByIdAndDelete(userId);
    }
}

