package proyectJlg.DemoJlg.modelo;

public class TopProductoDTO {
    private String producto;
    private Double cantidadTotal;
    private Double valorTotal;

    public String getProducto() { return producto; }
    public void setProducto(String v) { this.producto = v; }
    public Double getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(Double v) { this.cantidadTotal = v; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double v) { this.valorTotal = v; }
}