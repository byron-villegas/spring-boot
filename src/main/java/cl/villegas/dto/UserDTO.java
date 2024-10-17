package cl.villegas.dto;

public class UserDTO {
    private String rut;
    private String token;
    
    public UserDTO() {
        this.rut = null;
        this.token = null;
    }

    public UserDTO(String rut, String token) {
        this.rut = rut;
        this.token = token;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}