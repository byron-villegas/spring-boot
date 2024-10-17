package cl.villegas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.villegas.model.Usuario;
import cl.villegas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public void delete(Usuario usuario) {
        if (usuario != null)
            repository.delete(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        else
            return null;
    }

    @Override
    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public Usuario findByRutAndPassword(String rut, String password) {
        return repository.findByRutAndPassword(rut, password);
    }
}