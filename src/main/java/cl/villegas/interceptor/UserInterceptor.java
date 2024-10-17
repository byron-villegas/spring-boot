package cl.villegas.interceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cl.villegas.enums.TokenState;
import cl.villegas.exception.LoginException;
import cl.villegas.security.JWTAuth;

public class UserInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		HttpServletRequest req = request;
		try {
			if (!req.getMethod().equals("OPTIONS")) {
				String token = request.getHeader("X-Auth-Token");
				if (token == null)
					throw new LoginException("Token inexistente");
				String subject = JWTAuth.get(token);

				if (subject == null) {
					HttpServletResponse httpResponse = this.getAsHttpResponse(response);
					httpResponse.setHeader("Access-Control-Allow-Origin", "*");
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}

				TokenState estado = JWTAuth.validate(token);
				if (estado != TokenState.INVALIDO) {
					if (estado == TokenState.REFRESH)
						token = JWTAuth.refreshToken(token);
					HttpServletResponse httpServletResponse = response;
					httpServletResponse.setHeader("X-Auth-Refresh", token);
					return true;
				}
			}
		} catch (Exception ex) {
			HttpServletResponse httpResponse = this.getAsHttpResponse(response);
			httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			logger.error(ex.getMessage());
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}

	private HttpServletResponse getAsHttpResponse(ServletResponse response) throws LoginException {
		if (!(response instanceof HttpServletResponse))
			throw new LoginException("Expecting an HTTP response");
		return (HttpServletResponse) response;
	}
}