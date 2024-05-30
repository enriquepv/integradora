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
        if (session != null && session.getAttribute("usuario") != null) {
            Integer paginasVisitadas = (Integer) session.getAttribute("paginasVisitadas");
            if (paginasVisitadas == null) {
                paginasVisitadas = 0;
            }
            paginasVisitadas++;
            session.setAttribute("paginasVisitadas", paginasVisitadas);
        }
        return true;
    }
}

