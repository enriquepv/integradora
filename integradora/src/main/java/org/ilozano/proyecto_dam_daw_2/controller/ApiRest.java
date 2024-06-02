package org.ilozano.proyecto_dam_daw_2.controller;

import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.BloqueoUsuarioRequest;
import org.ilozano.proyecto_dam_daw_2.model.auxiliares.DarBajaUsuarioRequest;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.ClienteService;
import org.ilozano.proyecto_dam_daw_2.servicioLigero.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class ApiRest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/bloquear-usuario")
    public ResponseEntity<?> bloquearDesbloquearUsuario(@RequestBody BloqueoUsuarioRequest request) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(request.getEmail());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            usuario.setBloqueado(request.isBloqueado());
            usuario.setMotivoBloqueo(request.getMotivoBloqueo());
            usuarioService.guardarUsuario(usuario);

            return ResponseEntity.ok("Operación exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/dar-baja-usuario")
    public ResponseEntity<?> darBajaUsuario(@RequestBody DarBajaUsuarioRequest request) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(request.getEmail());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            usuario.setBajaLogica(true);
            usuarioService.guardarUsuario(usuario);

            return ResponseEntity.ok("Usuario dado de baja con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/quitar-baja-usuario")
    public ResponseEntity<?> quitarBajaUsuario(@RequestBody DarBajaUsuarioRequest request) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(request.getEmail());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            usuario.setBajaLogica(false);
            usuarioService.guardarUsuario(usuario);

            return ResponseEntity.ok("Baja lógica quitada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }



    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> obtenerListaClientes() {
        try {
            List<Cliente> clientes = clienteService.devuelveClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/clientes/filtrados")
    public ResponseEntity<List<Cliente>> obtenerClientesFiltrados(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimientoDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimientoHasta,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Boolean bajaLogica,
            @RequestParam(required = false) Boolean bloqueado,
            @RequestParam(required = false) BigDecimal salarioDesde,
            @RequestParam(required = false) BigDecimal salarioHasta) {
        try {
            List<Cliente> clientes = clienteService.buscarClientes(
                    fechaNacimientoDesde, fechaNacimientoHasta, apellido, bajaLogica, bloqueado, salarioDesde, salarioHasta);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable UUID idCliente) {
        try {
            boolean eliminado = clienteService.eliminarCliente(idCliente);
            if (eliminado) {
                return ResponseEntity.ok("Cliente eliminado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable UUID idCliente) {
        Cliente cliente = clienteService.obtenerClientePorId(idCliente);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/clientes/{idCliente}")
    public ResponseEntity<?> actualizarCliente(@PathVariable UUID idCliente, @RequestBody Cliente clienteActualizado) {
        try {
            Cliente clienteExistente = clienteService.obtenerClientePorId(idCliente);
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setApellidos(clienteActualizado.getApellidos());
            clienteExistente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            clienteExistente.setGenero(clienteActualizado.getGenero());
            clienteExistente.setPaisNacimiento(clienteActualizado.getPaisNacimiento());
            clienteExistente.setTipoDocumentoCliente(clienteActualizado.getTipoDocumentoCliente());
            clienteExistente.setDocumento(clienteActualizado.getDocumento());
            clienteExistente.setTelefonoMovil(clienteActualizado.getTelefonoMovil());
            clienteExistente.setComentarios(clienteActualizado.getComentarios());
            clienteExistente.setAceptaLicencia(clienteActualizado.isAceptaLicencia());
            clienteExistente.setDireccion(clienteActualizado.getDireccion());
            clienteExistente.setDireccionEnvio(clienteActualizado.getDireccionEnvio());
            clienteExistente.getUsuario().setBajaLogica(clienteActualizado.getUsuario().isBajaLogica());
            clienteExistente.getUsuario().setBloqueado(clienteActualizado.getUsuario().isBloqueado());
            clienteService.guardarCliente(clienteExistente);
            return ResponseEntity.ok("Cliente actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el cliente: " + e.getMessage());
        }
    }



    // Endpoint para obtener la lista de usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerListaUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.devuelveUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}