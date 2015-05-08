
package modelo;

import java.sql.SQLException;

import java.util.ArrayList;

public class PlaningMayordomia {
    
    private int identificador;
    private String hora;
    private String fecha;
    private String labor;

    public PlaningMayordomia(int identificador, String hora, String fecha, String labor) {
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
    
    /*Instanciar metodos creados en HermandadBD*/
    /*Esta clase me permite instanciar la clase PlaningMayordomiaBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        h.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        h.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase PlaningMayordomia*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        h.leer();
    }
    
    /*Este metodo me permite instanciar la clase PlaningMayordomiaBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        h.borrar();
    }
    /*Este metodo lee todos los datos de la tabla PlaningMayordomia*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        return h.leerTodos();
    }
    
    /*Este metodo busca un PlaningMayordomia indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <PlaningMayordomia> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomiaBD h = new PlaningMayordomiaBD(this);        
        return h.buscar(filtro, campo);
    }
    
}
