package org.ilozano.proyecto_dam_daw_2.controller;

import jakarta.servlet.http.HttpSession;
import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.AdministradorService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ClienteService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import java.util.List;
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
            return "redirect:/login/paginaAdministracion";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            model.addAttribute("usuarios", administradorService.obtenerNombresDeUsuarios());
            return "LoginAdministrador";
        }
    }

    @GetMapping("/paginaAdministracion")
    public String paginaAdministracion() {
        return "PaginaAdministracion";
    }

    @GetMapping("/Usuario")
    public String bloqueoDesbloqueo(Model model) {
        return "Usuarios";
    }

    @GetMapping("/Cliente")
    public String bajaLogica(Model model) {
        List<Cliente> listaClientes = clienteService.devuelveClientes();
        model.addAttribute("listaClientes", listaClientes);
        return "Cliente";
    }

    @GetMapping("/productos")
    public String productos() {
        return "Productos";
    }

    @GetMapping("/cliente/{idCliente}")
    public String detalleCliente(@PathVariable UUID idCliente, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(idCliente);
        model.addAttribute("cliente", cliente);
        return "modificarCliente";
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
