package org.ilozano.proyecto_dam_daw_2.configuration;

import org.ilozano.proyecto_dam_daw_2.component.AdminAuthInterceptor;
import org.ilozano.proyecto_dam_daw_2.component.AuthInterceptor;
import org.ilozano.proyecto_dam_daw_2.component.PaginasVisitadasInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PaginasVisitadasInterceptor paginasVisitadasInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AdminAuthInterceptor adminAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paginasVisitadasInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/resources/**", "/static/**", "/error/**", "/404", "/acceso_denegado");

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/login/login_paso2", "/login/datos_personales", "/login/datos_de_contacto", "/login/datos_de_cliente", "/login/sumario_datos_cliente");

        registry.addInterceptor(adminAuthInterceptor)
                .addPathPatterns("/login/paginaAdministracion", "/login/Usuario", "/login/Cliente", "/login/productos", "/login/cliente/**");
    }
}