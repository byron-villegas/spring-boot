package cl.villegas.service;

import java.util.List;
import cl.villegas.model.Persona;

public interface PersonaService {
    void delete(Persona persona);

    List<Persona> findAll();

    Persona findById(long id);

    void save(Persona persona);
}