
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class EntidadesConocidas {
    
    private int identificador;
    private String nombre;
    private String localidad;
    private String domicilio;
    private int telf1;
    private int telf2;
    private int cp;
    private String provincia;
    private String email;
    //
    
    public EntidadesConocidas(int identificador) {
        this.identificador = identificador;        
    }

    public EntidadesConocidas(int identificador, String nombre, String localidad, String domicilio, int telf1, int telf2, int cp, String provincia, String email) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telf1 = telf1;
        this.telf2 = telf2;
        this.cp=cp;
        this.provincia=provincia;
        this.email=email;
    }

    public EntidadesConocidas(int identificador, String nombre, String localidad, String domicilio, int telf1, int cp, String provincia, String email) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telf1 = telf1;
        this.cp = cp;
        this.provincia = provincia;
        this.email = email;
    }
    
    
    
    public EntidadesConocidas(String nombre, String localidad, String domicilio, int telf1, int telf2, int cp, String provincia, String email) {
        
        this.nombre = nombre;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telf1 = telf1;
        this.telf2 = telf2;
        this.cp=cp;
        this.provincia=provincia;
        this.email=email;
    }

    public EntidadesConocidas() {
        
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getTelf1() {
        return telf1;
    }

    public void setTelf1(int telf1) {
        this.telf1 = telf1;
    }

    public int getTelf2() {
        return telf2;
    }

    public void setTelf2(int telf2) {
        this.telf2 = telf2;
    }
    
    public int getCP() {
        return cp;
    }
    
    public void setCP(int cp){
        this.cp=cp;
    }
    
    public String getProvincia() {
        return provincia;
    }
    
    public void setProvincia(String provincia){
        this.provincia=provincia;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString() {
        return "EntidadesConocidas{" + "identificador=" + identificador + ", nombre=" + nombre + ", localidad=" + localidad + ", domicilio=" + domicilio + ", telf1=" + telf1 + ", telf2=" + telf2 + ", cp=" + cp + ", provincia=" + provincia + ", email=" + email + '}';
    }   
    
    /*Instanciar metodos creados en EntidadesConocidasBD*/
    /*Esta clase me permite instanciar la clase EntidadesConocidasBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        ec.grabar();
    }
    
    public void grabarSintelf2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        ec.grabarSintelf2();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        ec.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla entidadesconocidas*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        ec.leer();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        ec.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla entidadesconocidas*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        return ec.leerTodos();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <EntidadesConocidas> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        EntidadesConocidasBD ec = new EntidadesConocidasBD(this);        
        return ec.buscar(filtro, campo);
    }
       
}