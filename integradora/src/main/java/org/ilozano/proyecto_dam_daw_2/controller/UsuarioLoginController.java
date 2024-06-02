package org.ilozano.proyecto_dam_daw_2.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.Genero;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.enums.TipoDocumento;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ClienteService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.GeneroService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.TipoDocumentoService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.UsuarioService;
import org.ilozano.proyecto_dam_daw_2.validaciones.login.ValidacionLogin1;
import org.ilozano.proyecto_dam_daw_2.validaciones.login.ValidacionLogin2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class UsuarioLoginController {
    String EMAIL;
    int intentosClave = 1;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/login_paso1")
    public String login_paso1(HttpSession session, HttpServletRequest request, Model model) {
        model.addAttribute("usuario", new Usuario());

        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }

        String paginasVisitadas = obtenerPaginasVisitadasDeCookie(request);
        model.addAttribute("paginasVisitadas", paginasVisitadas);

        return "LoginPaso1";
    }
    @GetMapping("/acceso_denegado")
    public String accesoDenegado() {
        return "accesoDenegado";
    }
    @PostMapping("/login_paso1")
    public String procesarLoginPaso1(HttpSession session,
                                     @Validated(ValidacionLogin1.class)
                                     @ModelAttribute("usuario") Usuario usuario,
                                     BindingResult result,
                                     Model model) {

        if (result.hasErrors()) {
            return "LoginPaso1";
        }

        EMAIL = usuario.getEmail();
        Usuario usuarioRegistrado = usuarioService.buscarUsuarioPorEmail(EMAIL);

        if (usuarioRegistrado == null || usuarioRegistrado.isBajaLogica()) {
            model.addAttribute("error", "El usuario no existe.");
            return "LoginPaso1";
        }

        if (usuarioRegistrado.isBloqueado()) {
            model.addAttribute("error", "Su cuenta está bloqueada. Motivo: " + usuarioRegistrado.getMotivoBloqueo());
            return "LoginPaso1";
        }

        session.setAttribute("usuario", usuarioRegistrado);
        session.setAttribute("paginasVisitadas", 0); // Iniciar contador de páginas visitadas
        return "redirect:/login/login_paso2";
    }


    private String obtenerPaginasVisitadasDeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("paginasVisitadas".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "0";
    }


    private void guardarPaginasVisitadasEnCookie(HttpServletResponse response, int paginasVisitadas) {
        Cookie cookie = new Cookie("paginasVisitadas", String.valueOf(paginasVisitadas));
        cookie.setMaxAge(60 * 60 * 24 * 30); // Expira en 30 días
        response.addCookie(cookie);
    }
    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperacion(Model model) {
        model.addAttribute("email", ""); // Inicializa el modelo con un campo para el email

        // Obtener la pregunta de recuperación del usuario por email
        String preguntaRecuperacion = usuarioService.obtenerPreguntaRecuperacion(EMAIL);
        model.addAttribute("preguntaRecuperacion", preguntaRecuperacion);

        return "RecuperarPregunta";
    }

    @PostMapping("/recuperar")
    @ResponseBody
    public ResponseEntity<String> procesarRecuperacionContrasena(@RequestParam("respuesta") String respuesta) {
        // Verificar si la respuesta de recuperación es correcta
        if (usuarioService.verificarRespuestaRecuperacion(EMAIL, respuesta)) {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(EMAIL);
            if (usuario != null) {
                return ResponseEntity.ok("La contraseña es: " + usuario.getClave());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Respuesta incorrecta.");
        }
    }

    //-----------------------------------LÓGICA DE COOKIES-------------------------------------


    private String serializeMap(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining("#"));
    }

    private Map<String, Integer> deserializeMap(String value) {
        return Arrays.stream(value.split("#"))
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        entry -> entry[0],
                        entry -> Integer.parseInt(entry[1])
                ));
    }

    private Map<String, Integer> obtenerContadorDeInicios(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("numeroDeInicios".equals(cookie.getName())) {
                    return deserializeMap(cookie.getValue());
                }
            }
        }
        return new HashMap<>();
    }

    private void introducirMapaEnCookie(HttpServletResponse response, Map<String, Integer> numeroDeInicios) {
        String serializedMap = serializeMap(numeroDeInicios);
        Cookie cookie = new Cookie("numeroDeInicios", serializedMap);
        cookie.setMaxAge(60 * 60 * 24 * 30); // Cookie expira en 30 días
        response.addCookie(cookie);
    }


    @GetMapping("/login_paso2")
    public String login_paso2(HttpSession session,
                              @ModelAttribute("usuario") Usuario usuario,
                              Model model) {

        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }


        return "LoginPaso2";
    }

    @PostMapping("/login_paso2")
    public String procesarLoginPaso2(HttpSession session,
                                     @Validated(ValidacionLogin2.class)
                                     @ModelAttribute("usuario") Usuario usuario,
                                     BindingResult result,
                                     Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

        // Si no hay un usuario en la sesión te redirige al primer paso.
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login/login_paso1";
        }

        // Obtener el usuario temporal de la sesión
        Usuario usuarioTemporal = (Usuario) session.getAttribute("usuario");

        // Obtener el email del usuario temporal
        String email = usuarioTemporal.getEmail();
        Usuario usuarioRegistrado = usuarioService.buscarUsuarioPorEmail(email);

        // Verificar si el usuario registrado es nulo
        if (usuarioRegistrado == null) {
            model.addAttribute("error", "El usuario no fue encontrado.");
            return "LoginPaso1";
        }

        // Obtener o inicializar el contador de intentos fallidos de la sesión
        Integer intentosClave = (Integer) session.getAttribute("intentosClave");
        if (intentosClave == null) {
            intentosClave = 0;
        }

        // Incrementar el contador de intentos fallidos si hay errores en la validación
        if (result.hasErrors()) {
            intentosClave++;
            session.setAttribute("intentosClave", intentosClave);
            if (intentosClave == 3) {
                usuarioRegistrado.setBloqueado(true);
                usuarioRegistrado.setMotivoBloqueo("Ha puesto mal la clave 3 veces.");
                usuarioService.guardarUsuario(usuarioRegistrado);
                model.addAttribute("error", "Ha puesto mal la clave 3 veces. Su cuenta ha sido bloqueada.");
                return "LoginPaso1";
            }
            return "LoginPaso2";
        }

        // Restablecer el contador de intentos fallidos si la clave es correcta
        session.setAttribute("intentosClave", 0);

        usuarioRegistrado.setClave(usuario.getClave());
        usuarioService.guardarUsuario(usuarioRegistrado);

        Map<String, Integer> numeroDeInicios = obtenerContadorDeInicios(request);
        numeroDeInicios.put(email, numeroDeInicios.getOrDefault(email, 0) + 1);
        introducirMapaEnCookie(response, numeroDeInicios);

        // Comprobar si el usuario tiene un cliente asociado
        Cliente clienteAsociado = clienteService.obtenerClientePorUsuario(usuarioRegistrado.getIdUsuario());
        if (clienteAsociado != null) {
            session.setAttribute("cliente", clienteAsociado);
            return "redirect:/inicio/paginaPrincipal";
        }

        // Si no tiene cliente asociado, redirige a datos personales para completar información
        return "redirect:/login/datos_personales";
    }
    //---------------------------------------------REGISTRO DE CLIENTE--------------------------------------------

    @GetMapping("/datos_personales")
    public String datosPersonales(HttpSession session, Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        // Obtener todos los géneros del servicio
        List<Genero> generos = generoService.obtenerTodosLosGeneros();
        model.addAttribute("generos", generos);

        // Obtener todos los tipos de documento del servicio
        List<TipoDocumento> tiposDocumento = tipoDocumentoService.obtenerTodosLosTiposDocumento();
        model.addAttribute("tiposDocumento", tiposDocumento);

        if (session.getAttribute("cliente") != null) {
            model.addAttribute("cliente", (Cliente) session.getAttribute("cliente"));
            // En caso de que haya un usuario en la sesión, se añade al modelo para recuperar los datos introducidos.
        }

        return "datosPersonalesRC";
    }

    @PostMapping("/datos_personales")
    public String procesarDatosPersonales(HttpSession session,
                                          @ModelAttribute("cliente") @Valid Cliente cliente,
                                          BindingResult bindingResult,
                                          @RequestParam Long genero,
                                          @RequestParam Long tipoDocumentoCliente) {

        // Verifica si hay errores de validación
        if (bindingResult.hasErrors()) {
            // Si hay errores, devuelve a la vista de datos personales con los errores
            return "datosPersonalesRC";
        }

        // Aquí debes obtener los objetos Genero y TipoDocumento utilizando sus IDs
        Genero generoSeleccionado = generoService.obtenerGeneroPorId(genero);
        cliente.setGenero(generoSeleccionado);

        TipoDocumento tipoDocumentoSeleccionado = tipoDocumentoService.obtenerTipoDocumentoPorId(tipoDocumentoCliente);
        cliente.setTipoDocumentoCliente(tipoDocumentoSeleccionado);

        cliente.setFechaAlta(LocalDate.now());
        session.setAttribute("cliente", cliente);
        return "redirect:/login/datos_de_contacto";
    }


    @GetMapping("/datos_de_contacto")
    public String datosDeContacto(HttpSession session,
                                  @ModelAttribute("cliente") Cliente cliente,
                                  Model model) {
        if (session.getAttribute("cliente") != null) { //Para recuperar los datos del usuario al cambiar de paso
            model.addAttribute("cliente", (Cliente) session.getAttribute("cliente"));
        }

        return "DatosDeContactoRC";
    }

    @PostMapping("/datos_de_contacto")
    public String procesarDatosDeContacto(HttpSession session,
                                          @ModelAttribute("cliente") Cliente cliente) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login/datos_personales";
        }

        Cliente clienteTemporal = (Cliente) session.getAttribute("cliente");

        clienteTemporal.setTelefonoMovil(cliente.getTelefonoMovil());
        clienteTemporal.setDireccion(cliente.getDireccion());

        return "redirect:/login/datos_de_cliente";
    }

    @GetMapping("/datos_de_cliente")
    public String datosDeCliente(HttpSession session,
                                 @ModelAttribute("cliente") Cliente cliente,
                                 Model model) {
        if (session.getAttribute("cliente") != null) { //Para recuperar los datos del usuario al cambiar de paso
            model.addAttribute("cliente", (Cliente) session.getAttribute("cliente"));
        }
        return "DatosDeClienteRC";
    }

    @PostMapping("/datos_de_cliente")
    public String procesarDatosDeCliente(HttpSession session,
                                         @ModelAttribute("cliente") Cliente cliente) {

        if (session.getAttribute("cliente") == null) {
            return "redirect:/login/datos_personales";
        }

        Cliente clienteTemporal = (Cliente) session.getAttribute("cliente");

        clienteTemporal.setTarjetaCredito(cliente.getTarjetaCredito());
        clienteTemporal.setDireccionEnvio(cliente.getDireccionEnvio());

        return "redirect:/login/sumario_datos_cliente";
    }

    @GetMapping("/sumario_datos_cliente")
    public String sumarioDatosCliente(HttpSession session, Model model) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login/datos_personales";
        }
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "SumarioDatosClienteRC";
    }

    @PostMapping("/sumario_datos_cliente")
    public String procesarSumarioDatosCliente(HttpSession session,
                                              @ModelAttribute("cliente") Cliente cliente,
                                              RedirectAttributes redirectAttributes) {
        // Verifica si hay un cliente en la sesión
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login/datos_personales";
        }

        // Obtén el usuario que inició sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Maneja el caso en que el usuario es null
            return "redirect:/login/login_paso1";
        }

        // Busca el usuario por idUsuario
        Usuario usuarioRegistrado = usuarioService.devuelveUsuario(usuario.getIdUsuario());
        if (usuarioRegistrado == null) {
            // Maneja el caso en que el usuario no se encuentra
            return "redirect:/login/login_paso1";
        }

        Cliente clienteTemporal = (Cliente) session.getAttribute("cliente");

        // Verifica si el cliente ya está asociado a un usuario
        if (clienteTemporal.getUsuario() != null) {
            // Maneja el caso en que el cliente ya está asociado a un usuario
            redirectAttributes.addFlashAttribute("error", "El cliente ya está asociado a un usuario.");
            return "redirect:/login/datos_personales";
        }

        // Genera un salario aleatorio entre 1000 y 5000
        BigDecimal salarioAleatorio = BigDecimal.valueOf(Math.random() * (5000 - 1000) + 1000);
        clienteTemporal.setSalario(salarioAleatorio.setScale(2, RoundingMode.HALF_UP)); // Ajusta a dos decimales

        // Asocia el usuario registrado al cliente y guarda el cliente
        clienteTemporal.setUsuario(usuarioRegistrado);
        clienteService.guardarCliente(clienteTemporal);

        return "redirect:http://localhost:8080/inicio/paginaPrincipal";
    }

}

