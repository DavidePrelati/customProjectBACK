package davide.prelati.customProjectBACK.controllers;

import davide.prelati.customProjectBACK.entities.Customer;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.payloads.CustomerDTO;
import davide.prelati.customProjectBACK.payloads.CustomerResponseDTO;
import davide.prelati.customProjectBACK.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDTO createCustomer(@RequestBody @Validated CustomerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new CustomerResponseDTO(customerService.saveCustomer(body).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Customer> getAllCustomer() {

        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Customer findCustomerById(@PathVariable Long id) {

        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Customer updateCustomer(@PathVariable Long id, @Validated @RequestBody CustomerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return customerService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderByName(page, size);
    }

    @GetMapping("/surname")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findBySurname(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderBySurname(page, size);
    }

    @GetMapping("/username")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByUsername(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderByUsername(page, size);
    }

    @GetMapping("/email")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByEmail(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderByEmail(page, size);
    }

    @GetMapping("/filtername/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String name) {
        return customerService.filterByName(page, size, name);
    }

    @GetMapping("/filtersurname/{surname}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterBySurname(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String surname) {
        return customerService.filterBySurname(page, size, surname);
    }

    @GetMapping("/filterusername/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByUsername(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String username) {
        return customerService.filterByUsername(page, size, username);
    }

    @GetMapping("/filteremail/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByEmail(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String email) {
        return customerService.filterByEmail(page, size, email);
    }


}
