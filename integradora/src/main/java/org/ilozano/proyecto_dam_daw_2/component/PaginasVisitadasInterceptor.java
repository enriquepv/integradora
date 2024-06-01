package org.ilozano.proyecto_dam_daw_2.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PaginasVisitadasInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            Integer paginasVisitadas = (Integer) session.getAttribute("paginasVisitadasAdmin");
            if (paginasVisitadas == null) {
                paginasVisitadas = 0;
            }
            String uri = request.getRequestURI();
            if (uri.startsWith("/login/paginaAdministracion") ||
                    uri.startsWith("/login/Usuario") ||
                    uri.startsWith("/login/Cliente") ||
                    uri.startsWith("/login/productos")) {
                paginasVisitadas++;
                session.setAttribute("paginasVisitadasAdmin", paginasVisitadas);
            }
        }
        return true;
    }
}


