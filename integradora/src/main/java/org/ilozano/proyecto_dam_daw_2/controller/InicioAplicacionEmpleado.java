package org.ilozano.proyecto_dam_daw_2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioAplicacionEmpleado {
    @GetMapping("/conexion")
    public String conexion() {

        return "InicioAplicacion";
    }
    @GetMapping("/paginaPrincipal")
    public String paginaPrincipal(Model model, @ModelAttribute("message") String message, HttpSession session) {
        model.addAttribute("message", message);
        model.addAttribute("session", session.getAttribute("usuario"));
        return "paginaPrincipal";
    }
}

