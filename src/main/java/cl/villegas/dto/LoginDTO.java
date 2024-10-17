package cl.villegas.dto;

public class LoginDTO {
    private String rut;
    private String password;

    public LoginDTO() {
        this.rut = null;
        this.password = null;
    }

    public LoginDTO(String rut, String password) {
        this.rut = rut;
        this.password = password;
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