package cl.villegas.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cl.villegas.dto.LoginDTO;
import cl.villegas.dto.UserDTO;
import cl.villegas.model.Usuario;
import cl.villegas.security.Crypto;
import cl.villegas.security.JWTAuth;
import cl.villegas.service.UsuarioServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
@RequestMapping(path = "/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(AESController.class);

    @Autowired
    private UsuarioServiceImpl service;

    @ApiOperation(value = "Se loguea mediante rut y clave")
    @PostMapping(path = "auth", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public UserDTO save(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        UserDTO userDTO = new UserDTO("", "false");
        Usuario usuario = service.findByRutAndPassword(loginDTO.getRut(), Crypto.encrypt(loginDTO.getPassword()));
        if (usuario == null)
            return userDTO;

        logger.info("Logueado");

        userDTO.setRut(loginDTO.getRut());
        userDTO.setToken(JWTAuth.create(request.getScheme() + "//" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath()));
        return userDTO;
    }
}