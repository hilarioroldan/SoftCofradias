
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class JuntaGobierno extends persona {

    private int identificador;
    private String cargo;
    private String observaciones;
    private int numero_hermano_id;

    public JuntaGobierno(){
    
    }

     public JuntaGobierno(int identificador){
    
         this.identificador=identificador;
    }
    
    public JuntaGobierno(int identificador,String cargo, String observaciones, String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id, int id_hermandad,int numero_hermano_id) {
        
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria, tipo_pago_id, forma_pago_id, id_hermandad);
        this.cargo = cargo;
        this.observaciones = observaciones;
        this.identificador=identificador;        
        this.numero_hermano_id=numero_hermano_id;
        
    }

  public JuntaGobierno(int identificador,String nombre,String apellido,String cargo,String observaciones,int numero_hermano_id){
      
      super(nombre, apellido);
      
      this.cargo = cargo;
        this.observaciones = observaciones;
        this.identificador=identificador;        
        this.numero_hermano_id=numero_hermano_id;
    }


    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getNumero_hermano_id() {
        return numero_hermano_id;
    }

    public void setNumero_hermano_id(int numero_hermano_id) {
        this.numero_hermano_id = numero_hermano_id;
    }

    @Override
    public String toString() {
        return super.toString()+"JuntaGobierno{" + "identificador=" + identificador + ", cargo=" + cargo + ", observaciones=" + observaciones + ", numero_hermano_id=" + numero_hermano_id + '}';
    }

  /*metodos principales*/

    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    
         JuntaGobiernoBD j = new JuntaGobiernoBD(this);         
         j.grabar();
         
    }

      public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        JuntaGobiernoBD j = new JuntaGobiernoBD(this);      
        j.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase proveedor*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
         JuntaGobiernoBD j = new JuntaGobiernoBD(this);            
        j.leer();
    }
    
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
         JuntaGobiernoBD j = new JuntaGobiernoBD(this);       
        j.borrar();
    }
    /*Este metodo lee todos los datos de la tabla Hermandad*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
         JuntaGobiernoBD j = new JuntaGobiernoBD(this);          
        return j.leerTodos();
    }
    
    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList <JuntaGobierno> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
         JuntaGobiernoBD j = new JuntaGobiernoBD(this);           
        return j.buscar(filtro, campo);
    }
    
    
    
   
    
    
    
    
    
}
