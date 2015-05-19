
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalidaProcesional {
    private int identificador;
    private String fecha;
    private String descripcion;

    public SalidaProcesional() {
    }
    
    public SalidaProcesional(int identificador){
        this.identificador = identificador;
    }

    public SalidaProcesional(int identificador, String fecha, String descripcion) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getAnio() {
        return fecha;
    }

    public void setAnio(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        h.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        h.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase proveedor*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        h.leer();
    }
    
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        h.borrar();
    }
    /*Este metodo lee todos los datos de la tabla Hermandad*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        return h.leerTodos();
    }
    
    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList <SalidaProcesional> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        SalidaProcesionalBD h = new SalidaProcesionalBD(this);        
        return h.buscar(filtro, campo);
    }
}
