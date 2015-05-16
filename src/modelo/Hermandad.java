
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class Hermandad {
    private int identificador;
    private String nombre;
    private String año_fundacion;
    private String domicilio;
    private String municipio;
    private String provincia;
    private int telf1;
    private int telf2;
    private int fax;
    private String descripcion;
    
        
    public Hermandad(int identificador) {
        this.identificador=identificador;
    }
    
    public Hermandad() {
        
    }   
    
    public Hermandad(int identificador, String nombre, String año_fundacion, String domicilio, String municipio, String provincia, int telf1, int telf2, int fax, String descripcion) {
        
        this.identificador=identificador;
        this.nombre = nombre;
        this.año_fundacion = año_fundacion;
        this.domicilio = domicilio;
        this.municipio = municipio;
        this.provincia = provincia;
        this.telf1 = telf1;
        this.telf2 = telf2;
        this.fax = fax;
        this.descripcion = descripcion;
    }
    
    public Hermandad(String nombre, String año_fundacion, String domicilio, String municipio, String provincia, int telf1, int telf2, int fax, String descripcion) {
        
        this.nombre = nombre;
        this.año_fundacion = año_fundacion;
        this.domicilio = domicilio;
        this.municipio = municipio;
        this.provincia = provincia;
        this.telf1 = telf1;
        this.telf2 = telf2;
        this.fax = fax;
        this.descripcion = descripcion;
    }
    
    public int getIdentificador() {
        return identificador;
    }
    
    public void setIdentificador(int identificador){
        this.identificador=identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAño_fundacion() {
        return año_fundacion;
    }

    public void setAño_fundacion(String año_fundacion) {
        this.año_fundacion = año_fundacion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 

    @Override
    public String toString() {
        return "hermandad{" + "identificador=" + identificador + ", nombre=" + nombre + ", a\u00f1o_fundacion=" + año_fundacion + ", domicilio=" + domicilio + ", municipio=" + municipio + ", provincia=" + provincia + ", telf1=" + telf1 + ", telf2=" + telf2 + ", fax=" + fax + "," + ", descripcion=" + descripcion + '}';
    }     
    
    /*Instanciar metodos creados en HermandadBD*/
    /*Esta clase me permite instanciar la clase HermandadBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        h.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        h.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase proveedor*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        h.leer();
    }
    
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        h.borrar();
    }
    /*Este metodo lee todos los datos de la tabla Hermandad*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        return h.leerTodos();
    }
    
    /*Este metodo busca un proveedor indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <Hermandad> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadBD h = new HermandadBD(this);        
        return h.buscar(filtro, campo);
    }
}