//
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class CargosJunta {
    
    int identificador;
    String descripcion;

    public CargosJunta(int identificador, String descripcion) {
        this.identificador = identificador;
        this.descripcion = descripcion;
    }

    public CargosJunta(int identificador) {
        this.identificador = identificador;
    }

    public CargosJunta() {
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

    @Override
    public String toString() {
        return "CargosJunta{" + "identificador=" + identificador + ", descripcion=" + descripcion + '}';
    }
    
     /*Instanciar metodos creados*/
    /*Esta clase me permite instanciar la clasey llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CargosJuntaBD cc = new CargosJuntaBD(this);        
        cc.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CargosJuntaBD cc = new CargosJuntaBD(this);        
        cc.modificar();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CargosJuntaBD cc = new CargosJuntaBD(this);        
        cc.borrar();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <CargosJunta> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CargosJuntaBD cc = new CargosJuntaBD(this);         
        return cc.buscar(filtro, campo);
    }
    
}
