package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Customer;
import davide.prelati.customProjectBACK.exceptions.BadRequestException;
import davide.prelati.customProjectBACK.exceptions.NotFoundException;
import davide.prelati.customProjectBACK.payloads.CustomerDTO;
import davide.prelati.customProjectBACK.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(CustomerDTO body) {
        if (this.customerRepo.existsByUsername(body.username())) {
            throw new BadRequestException("Esiste già un cliente con questo username!");
        }

        Customer customer = new Customer(body.username(), body.name(), body.surname(), body.email());

        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public Customer findById(long customerId) {
        return customerRepo.findById(customerId).orElseThrow(() -> new NotFoundException(customerId));
    }


    public Customer findByIdAndUpdate(long customerId, CustomerDTO body) {
        Customer found = findById(customerId);


        found.setEmail(body.email());
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());


        return customerRepo.save(found);
    }


    public Page<Customer> orderByUsername(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.orderByUsername(pageable);
    }

    public Page<Customer> orderByEmail(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.orderByEmail(pageable);
    }

    public Page<Customer> orderByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.orderByName(pageable);
    }

    public Page<Customer> orderBySurname(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.orderBySurname(pageable);
    }

    public Page<Customer> filterByUsername(int page, int size, String username) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.filterByUsername(username, pageable);
    }

    public Page<Customer> filterByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.filterByName(name, pageable);
    }

    public Page<Customer> filterBySurname(int page, int size, String surname) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.filterBySurname(surname, pageable);
    }

    public Page<Customer> filterByEmail(int page, int size, String email) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepo.filterByEmail(email, pageable);
    }

    public void findByIdAndDelete(long userId) {
        Customer found = findById(userId);
        this.customerRepo.delete(found);
    }

}
