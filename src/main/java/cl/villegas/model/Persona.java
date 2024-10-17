package cl.villegas.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Persona {
    @ApiModelProperty(notes = "ID generado por la base de datos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ApiModelProperty(notes = "Rut de la persona")
    private String rut;
    @ApiModelProperty(notes = "Nombres de la persona")
    private String nombres;
    @ApiModelProperty(notes = "Apellidos de la persona")
    private String apellidos;
    @ApiModelProperty(notes = "Edad de la persona")
    private byte edad;
    @ApiModelProperty(notes = "Fecha de nacimiento de la persona")
    private LocalDate fechaNacimiento;
    @ApiModelProperty(notes = "Lista de mascotas de la persona")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, nullable = false, updatable = false)
    private List<Mascota> mascotas;

    public Persona() {
        this.id = 0;
        this.rut = null;
        this.nombres = null;
        this.apellidos = null;
        this.edad = 0;
        this.fechaNacimiento = null;
        this.mascotas = null;
    }

    public Persona(long id, String rut, String nombres, String apellidos, byte edad, LocalDate fechaNacimiento,
            List<Mascota> mascotas) {
        this.id = id;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.mascotas = mascotas;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return String.format("Person[id='%d', nombres='%s', apellidos='%s', edad='%d', fechaNacimiento='%s']", this.id, this.nombres,
                this.apellidos, this.edad, this.fechaNacimiento);
    }
}