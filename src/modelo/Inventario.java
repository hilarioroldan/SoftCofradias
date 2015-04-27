
package modelo;

import java.sql.Date;

public class Inventario {
    
    int identificador;
    String nombre;
    String descripcion;
    String autor;
    String estilo;
    Date fecha_realizacion;
    String procedencia;
    Double valoracion_economica;
    String mejora;
    String restauracion;
    int cantidad;
    String observaciones;

    public Inventario(int identificador, String nombre, String descripcion, String autor, String estilo, Date fecha_realizacion, String procedencia, Double valoracion_economica, String mejora, String restauracion, int cantidad, String observaciones) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.estilo = estilo;
        this.fecha_realizacion = fecha_realizacion;
        this.procedencia = procedencia;
        this.valoracion_economica = valoracion_economica;
        this.mejora = mejora;
        this.restauracion = restauracion;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    Inventario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Date getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(Date fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public Double getValoracion_economica() {
        return valoracion_economica;
    }

    public void setValoracion_economica(Double valoracion_economica) {
        this.valoracion_economica = valoracion_economica;
    }

    public String getMejora() {
        return mejora;
    }

    public void setMejora(String mejora) {
        this.mejora = mejora;
    }

    public String getRestauracion() {
        return restauracion;
    }

    public void setRestauracion(String restauracion) {
        this.restauracion = restauracion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Inventario{" + "identificador=" + identificador + ", nombre=" + nombre + ", descripcion=" + descripcion + ", autor=" + autor + ", estilo=" + estilo + ", fecha_realizacion=" + fecha_realizacion + ", procedencia=" + procedencia + ", valoracion_economica=" + valoracion_economica + ", mejora=" + mejora + ", restauracion=" + restauracion + ", cantidad=" + cantidad + ", observaciones=" + observaciones + '}';
    }   
    
}
