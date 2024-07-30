package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.Nation;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.NationDTO;
import davide.prelati.customProjectBACK.payloads.NationResponseDTO;
import davide.prelati.customProjectBACK.services.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/nations")
@CrossOrigin(origins = "http://localhost:5173")
public class NationController {

    @Autowired
    private NationService nationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    NationResponseDTO createNation(@RequestBody @Validated NationDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NationResponseDTO(nationService.saveNation(body).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Nation> getAllNation() {

        return nationService.getAllNation();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Nation findNationById(@PathVariable Long id) {

        return nationService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNation(@PathVariable Long id) {
        nationService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Nation updateNation(@PathVariable Long id, @Validated @RequestBody NationDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new davide.prelati.customProjectBACK.exceptions.BadRequestException(bindingResult.getAllErrors());
        }
        return nationService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Nation> findByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return nationService.orderByName(page, size);
    }


    @GetMapping("/filtername/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Nation> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String name) {
        return nationService.filterByName(page, size, name);
    }

}

