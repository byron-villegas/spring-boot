package cl.villegas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.villegas.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findAll();
}