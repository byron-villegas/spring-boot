package cl.villegas.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cl.villegas.interceptor.UserInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/abc"); // Aqui van las rutas a bloquear
	}
}