/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

/**
 *
 * @author Adrián
 */
public class HermandadesHermanadasBD {
        
    private HermandadesHermanadas h;

    public HermandadesHermanadasBD(HermandadesHermanadas h) {
        this.h = h;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM hermandadeshermanadas WHERE identificador=" + h.getId() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO hermandadeshermanadas VALUES ('"+h.getId()+"', '"+h.getNombre()+"', '"+h.getParroquia()+"', '"+h.getLocalidad()+"', '"+h.getDomicilio()+"', '"+h.getTelf1()+"', '"+h.getTelf2()+"',1)";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una hermandadesHermanadas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM hermandadeshermanadas WHERE identificador=" + h.getId() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE hermandadeshermanadas SET nombre='"+h.getNombre()+"', nombre='"+h.getNombre()+"',  parroquia='"+h.getParroquia()+"', localidad ='"+h.getLocalidad()+"', domicilio ='"+h.getDomicilio()+"',  telef1 ='"+h.getTelf1()+"', telef2 ='"+h.getTelf2()+"', WHERE id="+h.getId()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + h.getId() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public HermandadesHermanadas leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM hermandadeshermanadas WHERE identificador=" + h.getId()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                h.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setParroquia(cbd.resultado.getString("parroquia"));
                h.setLocalidad(cbd.resultado.getString("localidad"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + h.getId()+ " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + h.getId() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return h;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from hermandadeshermanadas WHERE identificador="+h.getId()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <HermandadesHermanadas> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <HermandadesHermanadas> listaHermandades = new ArrayList <HermandadesHermanadas>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM hermandadeshermanadas";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                h = new HermandadesHermanadas();
                h.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setParroquia(cbd.resultado.getString("parroquia"));
                h.setLocalidad(cbd.resultado.getString("localidad"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
                listaHermandades.add(h);
            }           
        }
        return listaHermandades;
    }
    
    /*Buscar Filtro*/
    public ArrayList <HermandadesHermanadas> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <HermandadesHermanadas> listaHermandades = new ArrayList <HermandadesHermanadas>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM hermandadeshermanadas WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY nombre;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            h = new HermandadesHermanadas();
                h.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                h.setNombre(cbd.resultado.getString("nombre"));
                h.setParroquia(cbd.resultado.getString("parroquia"));
                h.setLocalidad(cbd.resultado.getString("localidad"));
                h.setDomicilio(cbd.resultado.getString("domicilio"));
                h.setTelf1(cbd.resultado.getString("telf1"));
                h.setTelf2(cbd.resultado.getString("telf2"));
                listaHermandades.add(h);
        }
        
        return listaHermandades;
        
    }
    
}