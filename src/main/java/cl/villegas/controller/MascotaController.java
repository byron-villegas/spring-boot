package cl.villegas.controller;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cl.villegas.model.Mascota;
import cl.villegas.dto.RespuestaDTO;
import cl.villegas.service.MascotaServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping(path = "/mascotas")
public class MascotaController {
    private final static Logger logger = LoggerFactory.getLogger(MascotaController.class);

    @Autowired
    private MascotaServiceImpl service;

    @ApiOperation(value = "Obtiene la lista de mascotas", response = List.class)
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Mascota> findAll() {
        logger.info("Api lista ejecutada");
        return service.findAll();
    }

    @ApiOperation(value = "Agrega una mascota mediante un form")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaDTO add(@RequestParam(value = "idPersona") int idPersona,
                            @RequestParam(value = "nombre") String nombre, @RequestParam(value = "color") String color,
                            @RequestParam(value = "edad") byte edad, @RequestParam(value = "file") MultipartFile file) {
        Mascota mascota = new Mascota(0, idPersona, nombre, color, edad, null, file.getOriginalFilename(),
                file.getContentType());
        try {
            mascota.setImg(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger.info(mascota.toString());
        service.save(mascota);
        return new RespuestaDTO((byte) 0, "Mascota registrada exitosamente");
    }
}
