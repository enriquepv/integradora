package org.ilozano.proyecto_dam_daw_2.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.AdministradorService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ClienteService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class AdministradorLoginController {

    private final AdministradorService administradorService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    public AdministradorLoginController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping("/administrador")
    public String administrador(Model model) {
        model.addAttribute("usuarios", administradorService.obtenerNombresDeUsuarios());
        return "LoginAdministrador";
    }

    @PostMapping("/administrador")
    public String procesarAdministrador(HttpSession session, Model model, String username, String password) {
        boolean autenticado = administradorService.autenticarAdministrador(username, password);

        if (autenticado) {
            session.setAttribute("admin", username);
            session.setAttribute("paginasVisitadasAdmin", 0); // Iniciar el contador en 0
            return "redirect:/login/paginaAdministracion";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            model.addAttribute("usuarios", administradorService.obtenerNombresDeUsuarios());
            return "LoginAdministrador";
        }
    }

    @GetMapping("/paginaAdministracion")
    public String paginaAdministracion(HttpSession session, Model model, HttpServletRequest request) {
        addPaginasVisitadasToModel(session, model);

        // Obtener el número de visitas anterior si paginasVisitadasAdmin es 1
        Integer paginasVisitadasAdmin = (Integer) session.getAttribute("paginasVisitadasAdmin");
        if (paginasVisitadasAdmin != null && paginasVisitadasAdmin == 1) {
            Cookie[] cookies = request.getCookies();
            Cookie adminStatsCookie = findOrCreateAdminStatsCookie(cookies);
            String cookieValue = adminStatsCookie.getValue();
            Map<String, Integer> adminStatsMap = parseAdminStatsFromCookie(cookieValue);
            String adminUsername = (String) session.getAttribute("admin");
            Integer paginasVisitadasAdminAnterior = adminStatsMap.getOrDefault(adminUsername, 0);
            model.addAttribute("paginasVisitadasAdminAnterior", paginasVisitadasAdminAnterior);
        }

        return "PaginaAdministracion";
    }

    @GetMapping("/Usuario")
    public String bloqueoDesbloqueo(HttpSession session, Model model) {
        addPaginasVisitadasToModel(session, model);
        return "Usuarios";
    }

    @GetMapping("/Cliente")
    public String bajaLogica(HttpSession session, Model model) {
        addPaginasVisitadasToModel(session, model);
        return "Cliente";
    }

    @GetMapping("/productos")
    public String productos(HttpSession session, Model model) {
        addPaginasVisitadasToModel(session, model);
        return "Productos";
    }

    @GetMapping("/cliente/{idCliente}")
    public String detalleCliente(@PathVariable UUID idCliente, HttpSession session, Model model) {
        addPaginasVisitadasToModel(session, model);
        Cliente cliente = clienteService.obtenerClientePorId(idCliente);
        model.addAttribute("cliente", cliente);
        return "modificarCliente";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // Obtener el nombre de usuario administrador y páginas visitadas
        String adminUsername = (String) session.getAttribute("admin");
        Integer paginasVisitadas = (Integer) session.getAttribute("paginasVisitadasAdmin");

        // Crear o actualizar la cookie existente
        Cookie[] cookies = request.getCookies();
        Cookie adminStatsCookie = findOrCreateAdminStatsCookie(cookies);

        // Obtener el valor actual de la cookie
        String cookieValue = adminStatsCookie.getValue();
        Map<String, Integer> adminStatsMap = parseAdminStatsFromCookie(cookieValue);

        // Actualizar las estadísticas para el administrador actual
        adminStatsMap.put(adminUsername, paginasVisitadas);

        // Construir el nuevo valor de la cookie
        StringBuilder newValue = new StringBuilder();
        adminStatsMap.forEach((key, value) -> newValue.append(key).append(":").append(value).append("#"));

        // Establecer el nuevo valor de la cookie
        adminStatsCookie.setValue(newValue.toString());
        adminStatsCookie.setMaxAge(24 * 60 * 60); // Duración de 1 día (en segundos)
        adminStatsCookie.setPath("/"); // Cookie accesible desde todo el contexto de la aplicación
        response.addCookie(adminStatsCookie);

        // Eliminar atributos de la sesión
        session.removeAttribute("admin");
        session.removeAttribute("paginasVisitadasAdmin");

        return "redirect:/login/administrador"; // Redirigir al login de administrador
    }

    // Método auxiliar para buscar o crear la cookie de estadísticas de administradores
    private Cookie findOrCreateAdminStatsCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("adminStats")) {
                    return cookie;
                }
            }
        }
        // Si no existe, crear una nueva cookie
        return new Cookie("adminStats", "");
    }

    // Método auxiliar para parsear las estadísticas de administradores desde la cookie
    private Map<String, Integer> parseAdminStatsFromCookie(String cookieValue) {
        Map<String, Integer> adminStatsMap = new HashMap<>();
        if (!cookieValue.isEmpty()) {
            String[] adminStats = cookieValue.split("#");
            for (String adminStat : adminStats) {
                String[] parts = adminStat.split(":");
                if (parts.length == 2) {
                    adminStatsMap.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        }
        return adminStatsMap;
    }

    private void addPaginasVisitadasToModel(HttpSession session, Model model) {
        Integer paginasVisitadas = (Integer) session.getAttribute("paginasVisitadasAdmin");
        if (paginasVisitadas == null) {
            paginasVisitadas = 0;
        }
        model.addAttribute("paginasVisitadasAdmin", paginasVisitadas);
    }
}




//    @GetMapping("/Recuperacion")
//    public String recuperacion() {
//        return "Recuperacion";
//    }
//
//    @GetMapping("/consulta_parametrizada")
//    public String consultaParametrizada() {
//        return "redirect:/cliente/listado";
//    }
