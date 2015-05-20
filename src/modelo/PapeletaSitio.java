
package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PapeletaSitio {
    private int identificador;
    private int numero_hermano_id;
    private String fecha;
    private String sale;
    private int donativo;
    private int salida_procesional_id;
    private int numero_papeleta;

    public PapeletaSitio() {
    }
    
    public PapeletaSitio(int identificador){
        this.identificador = identificador;
    }

    public PapeletaSitio(int numero_hermano_id, String fecha, String sale, int donativo, int numero_papeleta) {
        this.numero_hermano_id = numero_hermano_id;
        this.fecha = fecha;
        this.sale = sale;
        this.donativo = donativo;
        this.numero_papeleta = numero_papeleta;
    }    
    

    public PapeletaSitio(int identificador, int numero_hermano_id, String fecha, String sale, int donativo,
            int numero_papeleta) {
        this.identificador = identificador;
        this.numero_hermano_id = numero_hermano_id;
        this.fecha = fecha;
        this.sale = sale;
        this.donativo = donativo;
        this.numero_papeleta = numero_papeleta;
    }

    public PapeletaSitio(int identificador, int numero_hermano_id, String fecha, String sale, Double donativo, int numero_papeleta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PapeletaSitio(int identificador, int numero_hermano_id, String fecha, String sale, int donativo, int salida_procesional_id, int numero_papeleta) {
        this.identificador = identificador;
        this.numero_hermano_id = numero_hermano_id;
        this.fecha = fecha;
        this.sale = sale;
        this.donativo = donativo;
        this.salida_procesional_id = salida_procesional_id;
        this.numero_papeleta = numero_papeleta;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public int getDonativo() {
        return donativo;
    }

    public void setDonativo(int donativo) {
        this.donativo = donativo;
    }

    public int getSalida_procesional_id() {
        return salida_procesional_id;
    }

    public void setSalida_procesional_id(int salida_procesional_id) {
        this.salida_procesional_id = salida_procesional_id;
    }

    public int getNumero_papeleta() {
        return numero_papeleta;
    }

    public void setNumero_papeleta(int numero_papeleta) {
        this.numero_papeleta = numero_papeleta;
    }
    
    
    /*Instanciar metodos creados en HermandadBD*/
    /*Esta clase me permite instanciar la clase HermandadBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        h.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        h.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase proveedor*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        h.leer();
    }
    
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        h.borrar();
    }
    /*Este metodo lee todos los datos de la tabla Hermandad*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        return h.leerTodos();
    }
    
    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList <PapeletaSitio> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitioBD h = new PapeletaSitioBD(this);        
        return h.buscar(filtro, campo);
    }
}