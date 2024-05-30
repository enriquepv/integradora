package org.ilozano.proyecto_dam_daw_2.controller;

import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.UsuarioService;
import org.ilozano.proyecto_dam_daw_2.validaciones.registro.ValidacionRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class UsuarioRegistroController {

    @Autowired
    private UsuarioService usuarioServiceIMPL;

    @GetMapping("/registro_paso1")
    public String datosDeUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "RegistroUsuario1";
    }

    @PostMapping("/registro_paso1")
    public String procesarDatosDeUsuario(@Validated(ValidacionRegistro.class)
                                         @ModelAttribute("usuario") Usuario usuario,
                                         BindingResult result,
                                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "RegistroUsuario1";
        }
        usuarioServiceIMPL.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("message", "Usuario registrado con éxito.");
        return "redirect:/inicio/paginaPrincipal";
    }
}

