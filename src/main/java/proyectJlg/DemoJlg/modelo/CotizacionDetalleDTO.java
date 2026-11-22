package proyectJlg.DemoJlg.modelo;

import java.util.Date;

public class CotizacionDetalleDTO {
    private String id;
    private String numeroCotizacion;
    private String cliente;
    private Date fechaCotizacion;   // ISODate de Atlas
    private Double valorTotal;
    private String estado;
    private String tipoCliente;
    private String direccion;

    public String getId() { return id; }
    public void setId(String v) { this.id = v; }
    public String getNumeroCotizacion() { return numeroCotizacion; }
    public void setNumeroCotizacion(String v) { this.numeroCotizacion = v; }
    public String getCliente() { return cliente; }
    public void setCliente(String v) { this.cliente = v; }
    public Date getFechaCotizacion() { return fechaCotizacion; }
    public void setFechaCotizacion(Date v) { this.fechaCotizacion = v; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double v) { this.valorTotal = v; }
    public String getEstado() { return estado; }
    public void setEstado(String v) { this.estado = v; }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String v) { this.tipoCliente = v; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String v) { this.direccion = v; }
}