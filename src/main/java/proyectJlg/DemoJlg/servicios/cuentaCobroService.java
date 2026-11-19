package proyectJlg.DemoJlg.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import proyectJlg.DemoJlg.modelo.CuentaCobro;
import proyectJlg.DemoJlg.repositorio.CuentaCobroRepository;

@Service
public class CuentaCobroService {

    private final CuentaCobroRepository cuentaCobroRepository;

    public CuentaCobroService(CuentaCobroRepository cuentaCobroRepository) {
        this.cuentaCobroRepository = cuentaCobroRepository;
    }

    public CuentaCobro guardar(CuentaCobro cuentaCobro) {
        return cuentaCobroRepository.save(cuentaCobro);
    }

    public List<CuentaCobro> listarTodas() {
        return cuentaCobroRepository.findAll();
    }

    public List<CuentaCobro> listarPorEstado(String estado) {
        return cuentaCobroRepository.findByEstado(estado);
    }

    public Optional<CuentaCobro> obtenerPorId(String id) {
        return cuentaCobroRepository.findById(id);
    }
}