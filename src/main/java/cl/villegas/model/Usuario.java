package cl.villegas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuario {
    @ApiModelProperty(notes = "ID generado por la base de datos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ApiModelProperty(notes = "Rut del usuario")
    private String rut;
    @ApiModelProperty(notes = "Password del usuario encriptada")
    private String password;

    public Usuario() {
        this.id = 0;
        this.rut = null;
        this.password = null;
    }

    public Usuario(long id, String rut, String password) {
        this.id = id;
        this.rut = rut;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
