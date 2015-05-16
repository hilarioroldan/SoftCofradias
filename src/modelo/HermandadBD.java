
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class HermandadBD {
    
    private Hermandad h;

    public HermandadBD(Hermandad h) {
        this.h = h;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM hermandad WHERE identificador=" + h.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO hermandad (identificador, nombre, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion) VALUES ('"+h.getIdentificador()+"', '"+h.getNombre()+"', '"+h.getAño_fundacion()+"', '"+h.getDomicilio()+"', '"+h.getMunicipio()+"', '"+h.getProvincia()+"', '"+h.getTelf1()+"', '"+h.getTelf2()+"', '"+h.getFax()+"', '"+h.getDescripcion()+"');";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una hermandad", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM hermandad WHERE identificador=" + h.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE hermandad SET nombre='"+h.getNombre()+"', año_fundacion='"+h.getAño_fundacion()+"', domicilio='"+h.getDomicilio()+"', municipio='"+h.getMunicipio()+"', provincia='"+h.getProvincia()+"', telf1='"+h.getTelf1()+"', telf2='"+h.getTelf2()+"', fax='"+h.getFax()+"', descripcion='"+h.getDescripcion()+"' WHERE identificador="+h.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + h.getIdentificador() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public Hermandad leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM hermandad WHERE identificador=" + h.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                h.setIdentificador(cbd.resultado.getInt("identificador"));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setAño_fundacion(cbd.resultado.getString("año_fundacion"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setMunicipio(cbd.resultado.getString("municipio"));
                h.setProvincia(cbd.resultado.getString("provincia"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
                h.setFax(cbd.resultado.getString("fax"));
                h.setDescripcion(cbd.resultado.getString("descripcion"));  
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + h.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + h.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return h;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from hermandad WHERE identificador="+h.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <Hermandad> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <Hermandad> listaHermandad = new ArrayList <Hermandad>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM hermandad";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                h = new Hermandad();
                h.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setAño_fundacion(cbd.resultado.getString("año_fundacion"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setMunicipio(cbd.resultado.getString("municipio"));
                h.setProvincia(cbd.resultado.getString("provincia"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
                h.setFax(cbd.resultado.getString("fax"));
                h.setDescripcion(cbd.resultado.getString("descripcion"));
                
                listaHermandad.add(h);
            }           
        }
        return listaHermandad;
    }
    
    /*Buscar Filtro*/
    public ArrayList <Hermandad> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <Hermandad> listaHermandad = new ArrayList <Hermandad>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM hermandad WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY nombre;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            h = new Hermandad();
                h.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setAño_fundacion(cbd.resultado.getString("año_fundacion"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setMunicipio(cbd.resultado.getString("municipio"));
                h.setProvincia(cbd.resultado.getString("provincia"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
                h.setFax(cbd.resultado.getString("fax"));
                h.setDescripcion(cbd.resultado.getString("descripcion"));
                
                listaHermandad.add(h);
            
        }
        
        return listaHermandad;
        
    }
    
}