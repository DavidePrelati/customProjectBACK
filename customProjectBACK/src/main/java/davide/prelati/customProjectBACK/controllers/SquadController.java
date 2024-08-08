package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.Squad;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.SquadDTO;
import davide.prelati.customProjectBACK.payloads.SquadResponseDTO;
import davide.prelati.customProjectBACK.services.NationService;
import davide.prelati.customProjectBACK.services.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/squads")
@CrossOrigin(origins = "http://localhost:5173")
public class SquadController {

    @Autowired
    private SquadService squadService;

    @Autowired
    private NationService nationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    SquadResponseDTO createSquad(@RequestBody @Validated SquadDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new SquadResponseDTO(squadService.saveSquad(body).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Squad> getAllSquad() {

        return squadService.getAllSquad();
    }

    @GetMapping("/{name}/squads")
    public List<Squad> getSquadsByNation(@PathVariable String name) {
        return nationService.findSquadsByNation(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Squad findSquadById(@PathVariable Long id) {
        return squadService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSquad(@PathVariable Long id) {
        squadService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Squad updateSquad(@PathVariable Long id, @Validated @RequestBody SquadDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return squadService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Squad> findByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return squadService.orderByName(page, size);
    }

    @GetMapping("/filtername/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Squad> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String name) {
        return squadService.filterByName(page, size, name);
    }
}