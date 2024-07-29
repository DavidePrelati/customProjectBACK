package davide.prelati.customProjectBACK.controllers;


import davide.prelati.customProjectBACK.entities.Shirt;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.ShirtDTO;
import davide.prelati.customProjectBACK.payloads.ShirtResponseDTO;
import davide.prelati.customProjectBACK.services.ShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shirts")
@CrossOrigin(origins = "http://localhost:5173")
public class ShirtController {

    @Autowired
    private ShirtService shirtService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    ShirtResponseDTO createShirt(@RequestBody @Validated ShirtDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new ShirtResponseDTO(shirtService.saveShirt(body).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Shirt> getAllShirt() {

        return shirtService.getAllShirt();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Shirt findShirtById(@PathVariable Long id) {

        return shirtService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShirt(@PathVariable Long id) {
        shirtService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Shirt updateShirt(@PathVariable Long id, @Validated @RequestBody ShirtDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return shirtService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> findByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return shirtService.orderByName(page, size);
    }

    @GetMapping("/size")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> findBySize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return shirtService.orderBySize(page, size);
    }

    @GetMapping("/price")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> findByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return shirtService.orderByPrice(page, size);
    }

    @GetMapping("/filtername/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String name) {
        return shirtService.filterByName(page, size, name);
    }

    @GetMapping("/filtertaglia/{taglia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> filterBySize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String taglia) {
        return shirtService.filterBySize(page, size, taglia);
    }

    @GetMapping("/filterprice/{price}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Shirt> filterByPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable double price) {
        return shirtService.filterByPrice(page, size, String.valueOf(price));
    }
}
