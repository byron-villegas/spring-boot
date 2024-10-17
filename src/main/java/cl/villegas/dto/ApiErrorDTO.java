package cl.villegas.dto;

import org.springframework.http.HttpStatus;

public class ApiErrorDTO {
	private String timestamp;
	private String errorCode;
	private HttpStatus status;
	private String message;
	private String errors;

	public ApiErrorDTO() {
		this.timestamp = null;
		this.errorCode = null;
		this.status = null;
		this.message = null;
		this.errors = null;
	}

	public ApiErrorDTO(String timestamp, String errorCode, HttpStatus status, String message, String errors) {
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
}