package cl.villegas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Mascota {
    @ApiModelProperty(notes = "ID generado por la base de datos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ApiModelProperty(notes = "ID de la persona")
    private long idPersona;
    @ApiModelProperty(notes = "Nombre de la mascota")
    private String nombre;
    @ApiModelProperty(notes = "Color de la mascota")
    private String color;
    @ApiModelProperty(notes = "Edad de la mascota")
    private byte edad;
    @ApiModelProperty(notes = "Imagen de la mascota")
    @Column(columnDefinition = "BLOB") // Para indicar la definicion de la columna
    private byte[] img;
    @ApiModelProperty(notes = "Nombre de la imagen de la mascota")
    private String fileName;
    @ApiModelProperty(notes = "ContentType de la imagen de la mascota")
    private String contentType;

    public Mascota() {
        this.id = 0;
        this.idPersona = 0;
        this.nombre = null;
        this.color = null;
        this.edad = 0;
        this.img = null;
        this.fileName = null;
        this.contentType = null;
    }

    public Mascota(long id, long idPersona, String nombre, String color, byte edad, byte[] img, String fileName,
            String contentType) {
        this.id = id;
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.color = color;
        this.edad = edad;
        this.img = img;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return String.format(
                "Mascota[id='%d', idPersona='%d', nombre='%s', color='%s', edad='%d', fileName='%s', contentType='%s']",
                this.id, this.idPersona, this.nombre, this.color, this.edad, this.fileName, this.contentType);
    }
}