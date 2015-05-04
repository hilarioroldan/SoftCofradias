
package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaningSecretaria {
    
    private String hora;
    private String descripcion;
    private Date fecha;
    private int id;
    
    public PlaningSecretaria(int id,String hora, String descripcion, Date fecha) {
        this.id = id;
        this.hora = hora;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public PlaningSecretaria() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public PlaningSecretaria(int id){
        this.id = id;
    }

    public PlaningSecretaria(int id, String hora, Date fecha, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }
    
    public PlaningSecretaria(String hora, Date fecha, String descripcion){
        this.hora = hora;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    public int getId(){
        return id;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        pSBD.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        pSBD.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla entidadesconocidas*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        pSBD.leer();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        pSBD.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla entidadesconocidas*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        return pSBD.leerTodos();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <PlaningSecretaria> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningSecretariaBD pSBD = new PlaningSecretariaBD(this);        
        return pSBD.buscar(filtro, campo);
    }
}
