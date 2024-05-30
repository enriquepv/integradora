package org.ilozano.proyecto_dam_daw_2.servicioLigero;

import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> devuelveUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario devuelveUsuario(UUID idUsuario) {
        return usuarioRepository.getReferenceById(idUsuario);
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) { return usuarioRepository.findByEmail(email); }

    public Optional<Usuario> bloquearUsuario(UUID idUsuario, LocalDate fechaDesbloqueo, String motivoBloqueo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByIdUsuario(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setBloqueado(true);
            usuario.setFechaDesbloqueo(fechaDesbloqueo);
            usuario.setMotivoBloqueo(motivoBloqueo);
            usuarioRepository.save(usuario);
        }
        return usuarioOptional;
    }
    public String obtenerPreguntaRecuperacion(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return usuario.getPreguntaRecuperacion();
        }
        return null;
    }

    public boolean verificarRespuestaRecuperacion(String email, String respuesta) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null && respuesta.equals(usuario.getRespuestaRecuperacion());
    }
    public Optional<Usuario> desbloquearUsuario(UUID idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByIdUsuario(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setBloqueado(false);
            usuario.setFechaDesbloqueo(null);
            usuario.setMotivoBloqueo(null);
            usuarioRepository.save(usuario);
        }
        return usuarioOptional;
    }
    public boolean isUsuarioBloqueado(Usuario usuario) {
        return usuario.isBloqueado() && usuario.getFechaDesbloqueo().isAfter(LocalDate.now());
    }
}
