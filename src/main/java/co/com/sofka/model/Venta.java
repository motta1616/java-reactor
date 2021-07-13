package co.com.sofka.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Venta {

    private Integer idVenta;
    private LocalDateTime fecha;

    public Venta(Integer idVenta) {
        this.idVenta = idVenta;
        this.fecha = LocalDateTime.now();
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                '}';
    }
}
