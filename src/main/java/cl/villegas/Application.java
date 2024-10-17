package cl.villegas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import cl.villegas.model.Usuario;
import cl.villegas.security.Crypto;
import cl.villegas.service.UsuarioServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = { "cl.villegas" })
@EntityScan(basePackages = { "cl.villegas" })
@EnableJpaRepositories(basePackages = { "cl.villegas" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner innit(UsuarioServiceImpl usuarioService) {
        return (args) -> {
            if (usuarioService.findById(1) == null)
                usuarioService.save(new Usuario(0, "11.111.111-1", Crypto.encrypt("admin123")));
        };
    }
}