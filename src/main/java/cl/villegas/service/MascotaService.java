package cl.villegas.service;

import java.util.List;
import cl.villegas.model.Mascota;

public interface MascotaService {
    void delete(Mascota mascota);

    List<Mascota> findAll();

    List<Mascota> findAllByIdPersona(long idPersona);

    Mascota findById(long id);

    void save(Mascota mascota);
}
