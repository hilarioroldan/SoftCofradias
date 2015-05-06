
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class PlaningMayordomiaBD {
    
    PlaningMayordomia pm;
    
    PlaningMayordomiaBD(PlaningMayordomia pm) {
        this.pm=pm;
    }
    
    /*Metodo Grabar*/    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM planingmayordomia WHERE identificador=" + pm.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO planingmayordomia (identificador, hora, fecha, labor, mayordomia_id) VALUES ('"+pm.getIdentificador()+"', '"+pm.getHora()+"', '"+pm.getFecha()+"', '"+pm.getLabor()+"', '1');";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar un planing", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM planingmayordomia WHERE identificador=" + pm.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE planingmayordomia SET hora='"+pm.getHora()+"', fecha='"+pm.getFecha()+"', labor='"+pm.getLabor()+"' WHERE identificador="+pm.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + pm.getIdentificador() + "No se encuentra en la tabla planing Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public PlaningMayordomia leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM planingmayordomia WHERE identificador=" + pm.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                pm.setIdentificador(cbd.resultado.getInt("identificador"));
                pm.setHora(cbd.resultado.getTime("hora"));
                pm.setFecha(cbd.resultado.getDate("fecha"));
                pm.setLabor(cbd.resultado.getString("labor"));                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + pm.getIdentificador() + " No se encuentra en la tabla planingmayordomia Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + pm.getIdentificador() + " No se encuentra en la tabla planingmayordomia Ubicacion'" + this.getClass().getName());
        }
        
        return pm;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from planingmayordomia WHERE identificador="+pm.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <PlaningMayordomia> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <PlaningMayordomia> listaPlaningMayordomia = new ArrayList <PlaningMayordomia>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM planingmayordomia";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                pm = new PlaningMayordomia();
                pm.setIdentificador(cbd.resultado.getInt("identificador"));
                pm.setHora(cbd.resultado.getTime("hora"));
                pm.setFecha(cbd.resultado.getDate("fecha"));
                pm.setLabor(cbd.resultado.getString("labor")); 
                
                listaPlaningMayordomia.add(pm);
            }           
        }
        return listaPlaningMayordomia;
    }
    
    /*Buscar Filtro*/
    public ArrayList <PlaningMayordomia> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <PlaningMayordomia> listaPlaningMayordomia = new ArrayList <PlaningMayordomia>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM listaPlaningMayordomia WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY fecha;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            pm = new PlaningMayordomia();
                pm.setIdentificador(cbd.resultado.getInt("identificador"));
                pm.setHora(cbd.resultado.getTime("hora"));
                pm.setFecha(cbd.resultado.getDate("fecha"));
                pm.setLabor(cbd.resultado.getString("labor")); 
                
                listaPlaningMayordomia.add(pm);            
        }
        
        return listaPlaningMayordomia;
        
    }
    
}
