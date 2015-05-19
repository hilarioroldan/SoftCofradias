/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepartoLoterias {
    private int identificador;
    private int num_hermano_id;
    private int num_desde;
    private int num_hasta;
    private int cantidad;
    private int loterias_id;

    public RepartoLoterias(int identificador, int num_hermano_id, int num_desde, int num_hasta, int cantidad, int loterias_id) {
        this.identificador = identificador;
        this.num_hermano_id = num_hermano_id;
        this.num_desde = num_desde;
        this.num_hasta = num_hasta;
        this.cantidad = cantidad;
        this.loterias_id = loterias_id;
    }
    
    public RepartoLoterias(int identificador){
        this.identificador = identificador;
    }
    public RepartoLoterias(){
        
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getNum_hermano_id() {
        return num_hermano_id;
    }

    public void setNum_hermano_id(int num_hermano_id) {
        this.num_hermano_id = num_hermano_id;
    }

    public int getNum_desde() {
        return num_desde;
    }

    public void setNum_desde(int num_desde) {
        this.num_desde = num_desde;
    }

    public int getNum_hasta() {
        return num_hasta;
    }

    public void setNum_hasta(int num_hasta) {
        this.num_hasta = num_hasta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getLoterias_id() {
        return loterias_id;
    }

    public void setLoterias_id(int loterias_id) {
        this.loterias_id = loterias_id;
    }
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        h.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        h.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la clase proveedor*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        h.leer();
    }
    
    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        h.borrar();
    }
    /*Este metodo lee todos los datos de la tabla Hermandad*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        return h.leerTodos();
    }
    
    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList <RepartoLoterias> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        RepartoLoteriasBD h = new RepartoLoteriasBD(this);        
        return h.buscar(filtro, campo);
    }
}
