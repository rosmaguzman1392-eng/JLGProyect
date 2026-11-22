package proyectJlg.DemoJlg.modelo;

public class TopClienteDTO {
    private String cliente;
    private Long numCotizaciones;
    private Double totalCotizado;

    public String getCliente() { return cliente; }
    public void setCliente(String c) { this.cliente = c; }
    public Long getNumCotizaciones() { return numCotizaciones; }
    public void setNumCotizaciones(Long v) { this.numCotizaciones = v; }
    public Double getTotalCotizado() { return totalCotizado; }
    public void setTotalCotizado(Double v) { this.totalCotizado = v; }
}