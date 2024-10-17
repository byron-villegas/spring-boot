package cl.villegas.exception;

import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import cl.villegas.interceptor.UserInterceptor;
import cl.villegas.dto.ApiErrorDTO;

@RestControllerAdvice(basePackages = { "cl.villegas.controller" })
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	ApiErrorDTO handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error(ex.getStackTrace().toString());
		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(DateTime.now().toLocalDateTime().toString(), "400", HttpStatus.BAD_REQUEST,
				"Ha ocurrido un problema al procesar la peticion", ex.getMessage());
		return apiErrorDTO;
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(LoginException.class)
	@ResponseBody
	ApiErrorDTO handleUnauthorized(HttpServletRequest req, Exception ex) {
		logger.error(ex.getStackTrace().toString());
		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(DateTime.now().toLocalDateTime().toString(), "401", HttpStatus.UNAUTHORIZED,
				"No Autorizado", ex.getLocalizedMessage());
		return apiErrorDTO;
	}
}