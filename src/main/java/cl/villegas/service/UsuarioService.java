package cl.villegas.service;

import java.util.List;
import cl.villegas.model.Usuario;

public interface UsuarioService {
    void delete(Usuario usuario);

    List<Usuario> findAll();

    Usuario findById(long id);

    void save(Usuario usuario);

    Usuario findByRutAndPassword(String rut, String password);
}