package cl.villegas.application;

import static org.junit.Assert.assertFalse;
import java.time.LocalDate;

import cl.villegas.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cl.villegas.model.Persona;
import cl.villegas.service.PersonaServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AppTest {
    static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Autowired
    PersonaServiceImpl service;

    @Test
    public void isPersonListNoEmpty() {
        logger.info("Cantidad de Personas: " + service.findAll().size());
        if (service.findAll().size() == 0) // Crea una persona para pasar el test
            service.save(new Persona(0, "11.111.111-1", "Abc Def", "Ghi Jkl", (byte) 25, LocalDate.now(), null));
        assertFalse(service.findAll().isEmpty());
    }
}