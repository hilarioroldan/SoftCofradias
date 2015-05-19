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

public class RepartoLoteriasBD {
        
    private RepartoLoterias rl;

    public RepartoLoteriasBD(RepartoLoterias rl) {
        this.rl = rl;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM repartoloteria WHERE identificador=" + rl.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO repartoloteria VALUES ('"+rl.getIdentificador()+"', '"+rl.getNum_hermano_id()+"', '"+rl.getNum_desde()+"', '"+rl.getNum_hasta()+"', '"+rl.getCantidad()+"',1)";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar un reparto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM repartoloteria WHERE identificador=" + rl.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE repartoloteria SET num_hermano_id='"+rl.getNum_hermano_id()+"', num_desde='"+rl.getNum_desde()+"', num_hasta='"+rl.getNum_hasta()+"', cantidad='"+rl.getCantidad()+"' WHERE identificador="+rl.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + rl.getIdentificador() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public RepartoLoterias leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM repartoloteria WHERE identificador=" + rl.getIdentificador()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                rl.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + rl.getIdentificador()+ " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + rl.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return rl;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from repartoloteria WHERE identificador="+rl.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <RepartoLoterias> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <RepartoLoterias> reparto = new ArrayList <RepartoLoterias>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM repartoloteria";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                rl = new RepartoLoterias();
                rl.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                rl.setNum_hermano_id(Integer.parseInt(cbd.resultado.getString("num_hermano_id")));
                rl.setNum_desde(Integer.parseInt(cbd.resultado.getString("num_desde")));
                rl.setNum_hasta(Integer.parseInt(cbd.resultado.getString("num_hasta")));
                rl.setCantidad(Integer.parseInt(cbd.resultado.getString("cantidad")));
                rl.setLoterias_id(Integer.parseInt(cbd.resultado.getString("loterias_id")));
                reparto.add(rl);
            }           
        }
        return reparto;
    }
    
    /*Buscar Filtro*/
    public ArrayList<RepartoLoterias> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <RepartoLoterias> reparto = new ArrayList <RepartoLoterias>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM repartoloteria WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY cantidad;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
                rl = new RepartoLoterias();
                
                rl.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                rl.setNum_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
                rl.setNum_desde(Integer.parseInt(cbd.resultado.getString("num_desde")));
                rl.setNum_hasta(Integer.parseInt(cbd.resultado.getString("num_hasta")));
                rl.setCantidad(Integer.parseInt(cbd.resultado.getString("cantidad")));
                rl.setLoterias_id(Integer.parseInt(cbd.resultado.getString("loterias_id")));
                reparto.add(rl);
        }
        
        return reparto;
        
    }
}