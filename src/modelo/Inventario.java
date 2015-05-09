
package modelo;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventario {
    
    int identificador;
    String nombre;
    String autor;
    String estilo;
    String fecha_realizacion;
    String procedencia;
    Double valoracion_economica;
    String mejora;
    String restauracion;
    int cantidad;
    String observaciones;
    String adquisicion;
    FileInputStream imagen; // almacenamiento de la imagen
    String fecha_baja;
    int longitudBytes;

    public Inventario(int identificador, String nombre, String autor, String estilo, String fecha_realizacion, String procedencia, Double valoracion_economica, String mejora, String restauracion, int cantidad, String observaciones, String adquisicion, FileInputStream imagen, String fecha_baja, int longitudBytes) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.autor = autor;
        this.estilo = estilo;
        this.fecha_realizacion = fecha_realizacion;
        this.procedencia = procedencia;
        this.valoracion_economica = valoracion_economica;
        this.mejora = mejora;
        this.restauracion = restauracion;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.adquisicion = adquisicion;
        this.imagen = imagen;
        this.fecha_baja = fecha_baja;
        this.longitudBytes = longitudBytes;
    }

    public Inventario(String nombre, String autor, String estilo, String fecha_realizacion, String procedencia, Double valoracion_economica, String mejora, String restauracion, int cantidad, String observaciones, String adquisicion, FileInputStream imagen, String fecha_baja, int longitudBytes) {
        this.nombre = nombre;
        this.autor = autor;
        this.estilo = estilo;
        this.fecha_realizacion = fecha_realizacion;
        this.procedencia = procedencia;
        this.valoracion_economica = valoracion_economica;
        this.mejora = mejora;
        this.restauracion = restauracion;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.adquisicion = adquisicion;
        this.imagen = imagen;
        this.fecha_baja = fecha_baja;
        this.longitudBytes = longitudBytes;
    }

    public Inventario(int identificador) {
        this.identificador = identificador;
    }
    
    public Inventario() {
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

    public String getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(String fecha_realizacion) {
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

    public String getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(String adquisicion) {
        this.adquisicion = adquisicion;
    }

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen) {
        this.imagen = imagen;
    }

    public String getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public int getLongitudBytes() {
        return longitudBytes;
    }

    public void setLongitudBytes(int longitudBytes) {
        this.longitudBytes = longitudBytes;
    }

    @Override
    public String toString() {
        return "Inventario{" + "identificador=" + identificador + ", nombre=" + nombre + ", autor=" + autor + ", estilo=" + estilo + ", fecha_realizacion=" + fecha_realizacion + ", procedencia=" + procedencia + ", valoracion_economica=" + valoracion_economica + ", mejora=" + mejora + ", restauracion=" + restauracion + ", cantidad=" + cantidad + ", observaciones=" + observaciones + ", adquisicion=" + adquisicion + ", imagen=" + imagen + ", fecha_baja=" + fecha_baja + ", longitudBytes=" + longitudBytes + '}';
    }
    
    
    
    /*Instanciar metodos creados en Inventario*/
    /*Esta clase me permite instanciar la clase InventarioBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);        
        i.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);        
        i.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla Inventario*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);        
        i.leer();
    }
    
    /*Este metodo me permite instanciar la clase InventarioBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);        
        i.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla Inventario*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);        
        return i.leerTodos();
    }
    
    /*Este metodo busca un Inventario indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <Inventario> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        InventarioBD i = new InventarioBD(this);      
        return i.buscar(filtro, campo);
    }

    
    
}
