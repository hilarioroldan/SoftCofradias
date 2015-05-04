/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alex Dopino
 */
public class hermanito extends persona {
    
    private int numero_hermano;

    public hermanito( int numero_hermano,String nombre, String nif, String apellido, String municipio, String provincia, String pais, String tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id, int id_hermandad) {
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria, tipo_pago_id, forma_pago_id, id_hermandad);
        this.numero_hermano=numero_hermano;
        
    }

     public hermanito() {
        
      
        
         }

    public hermanito(int numero_hermano) {
        
        this.numero_hermano = numero_hermano;
        
         }
    
    
    

    public int getNumero_hermano() {
        return numero_hermano;
    }

    public void setNumero_hermano(int numero_hermano) {
        this.numero_hermano = numero_hermano;
    }

    @Override
    public String toString() {
        return super.toString()+"hermanito{" + "numero_hermano=" + numero_hermano + '}';
    }
     
    
    
    
     public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
          
          hermanitoBD h2 = new hermanitoBD(this);         
          h2.grabar();
        
    }

   
    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);        
        h.modificar();
    }
  
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
   public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h1 = new hermanitoBD(this);        
        h1.borrar();
    }
    
        public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);        
        return h.leerTodos();
        }
    
    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList <hermanito> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);    
        return h.buscar(filtro, campo);
    }
   
}
