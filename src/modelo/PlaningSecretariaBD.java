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

public class PlaningSecretariaBD {
        
    private PlaningSecretaria ps;

    public PlaningSecretariaBD(PlaningSecretaria ps) {
        this.ps = ps;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM planingsecretaria WHERE identificador=" + ps.getId() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO planingsecretaria VALUES ('"+ps.getId()+"', '"+ps.getHora()+"', '"+ps.getFecha()+"', '"+ps.getDescripcion()+"',1)";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar un planing de secretaria", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM planingsecretaria WHERE identificador=" + ps.getId() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE planingsecretaria SET hora='"+ps.getHora()+"', descripcion='"+ps.getDescripcion()+"', WHERE id="+ps.getId()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + ps.getId() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public PlaningSecretaria leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM planingsecretaria WHERE identificador=" + ps.getId()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                ps.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ps.getId()+ " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ps.getId() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return ps;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from planingsecretaria WHERE identificador="+ps.getId()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <PlaningSecretaria> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <PlaningSecretaria> listaplaning = new ArrayList <PlaningSecretaria>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM planingsecretaria";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                ps = new PlaningSecretaria();
                ps.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                ps.setDescripcion(cbd.resultado.getString("descripcion"));
                ps.setFecha(cbd.resultado.getDate("fecha"));
                ps.setHora(cbd.resultado.getString("hora"));
                listaplaning.add(ps);
            }           
        }
        return listaplaning;
    }
    
    /*Buscar Filtro*/
    public ArrayList <PlaningSecretaria> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <PlaningSecretaria> listaplaning = new ArrayList <PlaningSecretaria>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM planingsecretaria WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY descripcion;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
                ps = new PlaningSecretaria();
                ps.setId(Integer.parseInt(cbd.resultado.getString("identificador")));
                ps.setDescripcion(cbd.resultado.getString("descripcion"));
                ps.setFecha(cbd.resultado.getDate("fecha"));
                ps.setHora(cbd.resultado.getString("hora"));
                listaplaning.add(ps);
        }
        
        return listaplaning;
        
    }
    
}
