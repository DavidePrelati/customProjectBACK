package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Customer_Shirt;
import davide.prelati.customProjectBACK.repositories.CustShirtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustShirtService {

    @Autowired
    private CustShirtRepo custShirtRepo;

    public List<Customer_Shirt> findAll() {
        return custShirtRepo.findAll();
    }

    public Optional<Customer_Shirt> findAllById(Long id) {
        return custShirtRepo.findById(id);
    }

    public Customer_Shirt saveCustShirt(Customer_Shirt customerShirt) {
        return custShirtRepo.save(customerShirt);
    }

    public void deleteById(Long id) {
        custShirtRepo.deleteById(id);
    }
}
