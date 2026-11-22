package proyectJlg.DemoJlg.modelo;

public class ResumenDTO {
    private Long totalCotizaciones;
    private Double valorTotalCotizado;
    private Double ticketPromedio;
    private Long cotizacionesAprobadas;

    public Long getTotalCotizaciones() { return totalCotizaciones; }
    public void setTotalCotizaciones(Long v) { this.totalCotizaciones = v; }
    public Double getValorTotalCotizado() { return valorTotalCotizado; }
    public void setValorTotalCotizado(Double v) { this.valorTotalCotizado = v; }
    public Double getTicketPromedio() { return ticketPromedio; }
    public void setTicketPromedio(Double v) { this.ticketPromedio = v; }
    public Long getCotizacionesAprobadas() { return cotizacionesAprobadas; }
    public void setCotizacionesAprobadas(Long v) { this.cotizacionesAprobadas = v; }
}