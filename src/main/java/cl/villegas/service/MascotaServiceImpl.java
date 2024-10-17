package cl.villegas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.villegas.model.Mascota;
import cl.villegas.repository.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    private MascotaRepository repository;

    @Override
    public void delete(Mascota mascota) {
        repository.delete(mascota);
    }

    @Override
    public List<Mascota> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Mascota> findAllByIdPersona(long idPersona) {
        return repository.findAllByIdPersona(idPersona);
    }

    @Override
    public Mascota findById(long id) {
        if(!repository.findById(id).isPresent())
            return null;
        return repository.findById(id).get();
    }

    @Override
    public void save(Mascota mascota) {
        repository.save(mascota);
    }
}