
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class HermandadesHermanadas {
    private int id;
    private String nombre;
    private String parroquia;
    private String localidad;
    private String domicilio;
    private int telf1;
    private int telf2;

    public HermandadesHermanadas(int id, String nombre, String parroquia, String localidad, String domicilio, int telf1, int telf2) {
        this.id = id;
        this.nombre = nombre;
        this.parroquia = parroquia;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telf1 = telf1;
        this.telf2 = telf2;
    }

    public HermandadesHermanadas(String nombre, String parroquia, String localidad, String domicilio, int telf1, int telf2) {
        this.nombre = nombre;
        this.parroquia = parroquia;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telf1 = telf1;
        this.telf2 = telf2;
    }

    public HermandadesHermanadas(int id) {
        this.id = id;
    }

    public HermandadesHermanadas() {
    }      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
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

    @Override
    public String toString() {
        return "HermandadesHermanadas{" + "id=" + id + ", nombre=" + nombre + ", parroquia=" + parroquia + ", localidad=" + localidad + ", domicilio=" + domicilio + ", telf1=" + telf1 + ", telf2=" + telf2 + '}';
    }
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        ec.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        ec.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla entidadesconocidas*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        ec.leer();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        ec.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla entidadesconocidas*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        return ec.leerTodos();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <HermandadesHermanadas> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        HermandadesHermanadasBD ec = new HermandadesHermanadasBD(this);        
        return ec.buscar(filtro, campo);
    }
}
