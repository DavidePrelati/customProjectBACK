package davide.prelati.customProjectBACK.services;

import davide.prelati.customProjectBACK.entities.Nation;
import davide.prelati.customProjectBACK.repositories.NationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {

    @Autowired
    private NationRepo nationRepo;

    public List<Nation> findAll() {
        return nationRepo.findAll();
    }


}
