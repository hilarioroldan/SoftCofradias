
package modelo;

import java.sql.Date;
import java.sql.Time;

public class PlaningMayordomia {
    
    private int identificador;
    private Time hora;
    private Date fecha;
    private String labor;

    public PlaningMayordomia(int identificador, Time hora, Date fecha, String labor) {
        this.identificador = identificador;
        this.hora = hora;
        this.fecha = fecha;
        this.labor = labor;
    }

    public PlaningMayordomia(int identificador) {
        this.identificador = identificador;
    }

    public PlaningMayordomia() {
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
        this.labor = labor;
    }

    @Override
    public String toString() {
        return "PlaningMayordomia{" + "identificador=" + identificador + ", hora=" + hora + ", fecha=" + fecha + ", labor=" + labor + '}';
    }   
    
}
