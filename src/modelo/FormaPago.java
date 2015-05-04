
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class FormaPago {
    
    int identificador;
    String forma_pago;

    public FormaPago(int identificador, String forma_pago) {
        this.identificador = identificador;
        this.forma_pago = forma_pago;
    }

    public FormaPago(int identificador) {
        this.identificador = identificador;
    }   

    public FormaPago() {
    }   

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    @Override
    public String toString() {
        return "FormaPago{" + "identificador=" + identificador + ", forma_pago=" + forma_pago + '}';
    }
    
        
    /*Este metodo me permite instanciar la clase InventarioBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPagoBD i = new FormaPagoBD(this);        
        i.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla Inventario*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPagoBD i = new FormaPagoBD(this);        
        return i.leerTodos();
    }
    
   public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPagoBD i = new FormaPagoBD(this);        
        i.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPagoBD i = new FormaPagoBD(this);        
        i.modificar();
    }
    
    
}
