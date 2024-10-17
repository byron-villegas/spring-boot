package cl.villegas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cl.villegas.security.JWTAuth;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RequestMapping(path = "/jwt")
public class JWTController {
    private final static Logger logger = LoggerFactory.getLogger(AESController.class);

    @ApiOperation(value = "Retorna el token generado")
    @GetMapping(path = "auth/{subject}")
    @ResponseStatus(HttpStatus.OK)
    public String encryptValue(@PathVariable(value = "subject") String subject) {
        logger.info(String.format("Subject para el token: %s", subject));
        return JWTAuth.create(subject);
    }
}