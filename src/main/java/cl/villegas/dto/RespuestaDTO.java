package cl.villegas.dto;

public class RespuestaDTO {
    private byte id;
    private String message;

    public RespuestaDTO() {
        this.id = 0;
        this.message = null;
    }

    public RespuestaDTO(byte id, String message) {
        this.id = id;
        this.message = message;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}