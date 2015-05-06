
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class TipoPago {
    
    private int identificador;
    private String descripcion;
    private Double precio;

    public TipoPago(int identificador, String descripcion, Double precio) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public TipoPago(int identificador) {
        this.identificador = identificador;
    }

    public TipoPago() {
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "TipoPago{" + "identificador=" + identificador + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
    
     /*Este metodo me permite instanciar la clase InventarioBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        TipoPagoBD i = new TipoPagoBD(this);        
        i.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla Inventario*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        TipoPagoBD i = new TipoPagoBD(this);        
        return i.leerTodos();
    }
    
   public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        TipoPagoBD i = new TipoPagoBD(this);        
        i.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        TipoPagoBD i = new TipoPagoBD(this);        
        i.modificar();
    }
    
}
