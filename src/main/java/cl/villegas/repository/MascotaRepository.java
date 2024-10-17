package cl.villegas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.villegas.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findAll();
    List<Mascota> findAllByIdPersona(long idPersona);
}
