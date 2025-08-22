package examenprogra2thomasycarlos;

import java.util.Calendar;
import java.util.Date;

public class Movie extends RentItem {

    private Date fechaPublicacion;

    public Movie(String codigo, String nombre, double precioBase) {
        super(codigo, nombre, precioBase);
    }

    public void setFechaPublicacion(Date fecha) {
        this.fechaPublicacion = fecha;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEstado() {
        if (fechaPublicacion == null) return "SIN FECHA";

        long ahora = new Date().getTime();
        long publicada = fechaPublicacion.getTime();
        long diferenciaDias = (ahora - publicada) / (1000L * 60 * 60 * 24);

        return diferenciaDias <= 90 ? "ESTRENO" : "NORMAL";
    }

    @Override
    public double pagoRenta(int dias) {
        String estado = getEstado();
        double total = getPrecioBase();

        if (estado.equals("ESTRENO") && dias > 2) {
            total += (dias - 2) * 50;
        } else if (estado.equals("NORMAL") && dias > 5) {
            total += (dias - 5) * 30;
        }

        return total;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nFecha de publicación: " + (fechaPublicacion != null ? fechaPublicacion.toString() : "No definida")
                + "\nEstado: " + getEstado()
                + "\n– Película";
    }
}

