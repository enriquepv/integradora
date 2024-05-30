package org.ilozano.proyecto_dam_daw_2.servicioLigero;

import jakarta.persistence.EntityNotFoundException;
import org.ilozano.proyecto_dam_daw_2.model.Cliente;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> devuelveClientes() {
        return clienteRepository.findAll();
    }
    public boolean eliminarCliente(UUID idCliente) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isPresent()) {
            clienteRepository.delete(clienteOpt.get());
            return true;
        } else {
            return false;
        }
    }
    public Cliente obtenerClientePorId(UUID idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }
    public Cliente findClienteByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    // Nuevo método para buscar clientes
    public List<Cliente> buscarClientes(
                                        LocalDate fechaNacimientoDesde, LocalDate fechaNacimientoHasta,
                                        String apellido, Boolean bajaLogica, Boolean bloqueado) {
        return clienteRepository.buscarClientes( fechaNacimientoDesde, fechaNacimientoHasta,
                apellido, bajaLogica, bloqueado);
    }
}
