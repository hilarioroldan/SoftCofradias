
package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoteriasYSorteos {
    
    private int identificador;
    private String sorteo;
    private String fecha_devolucion;
    private double cantidad;
    private double precio_unidad;
    private double ganancia_unidad;
    
    public LoteriasYSorteos(int identificador) {
        this.identificador = identificador;        
    }

    public LoteriasYSorteos(int identificador, String sorteo, String fecha_devolucion, double cantidad, double precio_unidad, double ganancia_unidad) {
        this.identificador = identificador;
        this.sorteo = sorteo;
        this.fecha_devolucion = fecha_devolucion;
        this.cantidad = cantidad;
        this.precio_unidad = precio_unidad;
        this.ganancia_unidad = ganancia_unidad;    
    }

    public LoteriasYSorteos() {
        
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getSorteo() {
        return sorteo;
    }

    public void setSorteo(String sorteo) {
        this.sorteo = sorteo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(double precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    public double getGanancia_unidad() {
        return ganancia_unidad;
    }

    public void setGanancia_unidad(double ganancia_unidad) {
        this.ganancia_unidad = ganancia_unidad;
    }

    @Override
    public String toString() {
        return "LoteriasYSorteos{" + "identificador=" + identificador + ", sorteo=" + sorteo + ", fecha_devolucion=" + fecha_devolucion + ", cantidad=" + cantidad + ", precio_unidad=" + precio_unidad + ", ganancia_unidad=" + ganancia_unidad + '}';
    }
    
    /*Instanciar metodos creados en EntidadesConocidasBD*/
    /*Esta clase me permite instanciar la clase EntidadesConocidasBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        ec.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        ec.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla entidadesconocidas*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        ec.leer();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        ec.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla entidadesconocidas*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        return ec.leerTodos();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <LoteriasYSorteos> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        LoteriasYSorteosDB ec = new LoteriasYSorteosDB(this);        
        return ec.buscar(filtro, campo);
    }
       
}