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
import cl.villegas.security.Crypto;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RequestMapping(path = "/aes")
public class AESController {
    private final static Logger logger = LoggerFactory.getLogger(AESController.class);

    @ApiOperation(value = "Retorna un texto encriptado")
    @GetMapping(path = "encrypt/{text}")
    @ResponseStatus(HttpStatus.OK)
    public String encryptValue(@PathVariable(value = "text") String text) {
        logger.info(String.format("Texto a encriptar: %s", text));
        return Crypto.encrypt(text);
    }

    @ApiOperation(value = "Retorna la desencriptacion de un texto encriptado")
    @GetMapping(path = "decrypt/{text}")
    @ResponseStatus(HttpStatus.OK)
    public String decryptValue(@PathVariable(value = "text") String text) {
        logger.info(String.format("Texto a desencriptar: %s AA", text));
        return Crypto.decrypt(text);
    }
}