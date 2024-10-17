package cl.villegas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.villegas.model.Persona;
import cl.villegas.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository repository;

    @Override
    public void delete(Persona persona) {
        if (persona != null)
            repository.delete(persona);
    }

    @Override
    public List<Persona> findAll() {
        return repository.findAll();
    }

    @Override
    public Persona findById(long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        else
            return null;
    }

    @Override
    public void save(Persona persona) {
        repository.save(persona);
    }
}