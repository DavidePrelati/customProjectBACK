package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.UserRole;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.UserRoleRegistrationDTO;
import davide.prelati.customProjectBACK.payloads.UserRoleRequiredDTO;
import davide.prelati.customProjectBACK.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles/")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserRole> getAllRoles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.userRoleService.getRoles(page, size);
    }

    @GetMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserRole findById(@PathVariable long roleId) {

        return this.userRoleService.findById(roleId);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    UserRoleRegistrationDTO createInvoice(@RequestBody @Validated UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new UserRoleRegistrationDTO(userRoleService.saveRole(body).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long id) {
        this.userRoleService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    UserRole updateRole(@PathVariable Long id, @Validated @RequestBody UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return userRoleService.findByIdAndUpdate(id, body);
    }

}
