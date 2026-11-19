package proyectJlg.DemoJlg.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "cuentas_cobro")
public class CuentaCobro {

    @Id
    private String id;
    private String cotizacionId;   // vínculo con la cotización aprobada
    private String cliente;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEmision;

    private Double valorTotal;
    private String estado;         // PENDIENTE_DE_PAGO, PAGADO, EN_MORA
    private String descripcion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaPago;

    public CuentaCobro() {
        this.estado = "PENDIENTE_DE_PAGO";
        this.fechaEmision = LocalDate.now();
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCotizacionId() { return cotizacionId; }
    public void setCotizacionId(String cotizacionId) { this.cotizacionId = cotizacionId; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
}