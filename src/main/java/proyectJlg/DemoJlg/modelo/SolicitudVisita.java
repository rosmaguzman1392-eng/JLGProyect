package proyectJlg.DemoJlg.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "solicitudes_visita")
public class SolicitudVisita {
    
    @Id
    private String id;
    private String nombreCliente;
    private String empresa;
    private String tipoServicio; // Aire Acondicionado, Cuarto Frío, Tuberías
    private String descripcion;
    private LocalDate fechaPreferida;
    private String estado; // PENDIENTE, APROBADA, EN_PROCESO, COMPLETADA
    private String correoUsuario; // Para vincular con el usuario que la creó
    private LocalDate fechaCreacion;
    
    // Constructor vacío (requerido por MongoDB)
    public SolicitudVisita() {
        this.estado = "PENDIENTE";
        this.fechaCreacion = LocalDate.now();
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    
    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDate getFechaPreferida() { return fechaPreferida; }
    public void setFechaPreferida(LocalDate fechaPreferida) { this.fechaPreferida = fechaPreferida; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }
    
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}