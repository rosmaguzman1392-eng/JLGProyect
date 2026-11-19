package proyectJlg.DemoJlg.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import proyectJlg.DemoJlg.modelo.CuentaCobro;

public interface CuentaCobroRepository extends MongoRepository<CuentaCobro, String> {
    List<CuentaCobro> findByEstado(String estado);
    List<CuentaCobro> findByCotizacionId(String cotizacionId);
}