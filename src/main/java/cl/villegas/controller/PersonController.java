package cl.villegas.controller;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cl.villegas.model.Mascota;
import cl.villegas.model.Persona;
import cl.villegas.dto.RespuestaDTO;
import cl.villegas.service.MascotaServiceImpl;
import cl.villegas.service.PersonaServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(path = "/personas")
public class PersonController {
    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonaServiceImpl service;
    @Autowired
    private MascotaServiceImpl MascotaService;

    @ApiOperation(value = "Obtiene la lista de personas", response = List.class)
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Persona> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Agrega una persona mediante un json")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaDTO save(@RequestBody Persona person) {
        logger.info(person.toString());
        service.save(person);
        return new RespuestaDTO((byte) 0, "Persona registrada exitosamente");
    }

    @ApiOperation(value = "Obtiene una persona mediante el id")
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Persona get(@PathVariable(value = "id") long id) {
        Persona person = service.findById(id);
        return person;
    }

    @ApiOperation(value = "Actualiza una persona mediante json")
    @PutMapping(path = "{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public RespuestaDTO update(@RequestBody Persona person) {
        logger.info(person.toString());
        service.save(person);
        return new RespuestaDTO((byte) 0, "Persona modificada exitosamente");
    }

    @ApiOperation(value = "Elimina una persona mediante el id")
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaDTO delete(@PathVariable(value = "id") long id) {
        Persona person = service.findById(id);
        logger.info(person.toString());
        service.delete(person);
        return new RespuestaDTO((byte) 0, "Persona eliminada exitosamente");
    }

    @ApiOperation(value = "Obtiene la lista de mascotas de una persona mediante el id")
    @GetMapping(path = "/{id}/mascotas", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Mascota> findAllPetsByIdPersona(@PathVariable(value = "id") long id) {
        return MascotaService.findAllByIdPersona(id);
    }

    @ApiOperation(value = "Agrega una persona mediante un form")
    @PostMapping(path = "add")
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestParam(value = "rut") String rut, @RequestParam(value = "nombres") String nombres,
            @RequestParam(value = "apellidos") String apellidos, @RequestParam(value = "edad") byte edad,
            @RequestParam(value = "fechaNacimiento") LocalDate fechaNacimiento) {
        Persona person = new Persona(0, rut, nombres, apellidos, edad, fechaNacimiento, null);
        logger.info(person.toString());
        service.save(person);
        return "¡Persona registrada!";
    }

    @ApiOperation(value = "Actualiza una persona mediante un form")
    @PutMapping(path = "{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable(value = "id") long id, @RequestParam(value = "rut") String rut,
            @RequestParam(value = "names") String names, @RequestParam(value = "surnames") String surnames,
            @RequestParam(value = "age") byte age) {
        Persona person = service.findById(id);
        logger.info(person.toString());
        person.setNombres(names);
        person.setApellidos(surnames);
        person.setEdad(age);
        logger.info(person.toString());
        service.save(person);
        return "¡Persona modificada!";
    }
}