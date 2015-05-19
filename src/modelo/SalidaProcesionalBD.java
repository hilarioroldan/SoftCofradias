
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class SalidaProcesionalBD {
        
    private SalidaProcesional sp;

    public SalidaProcesionalBD(SalidaProcesional sp) {
        this.sp = sp;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM salidaprocesional WHERE identificador=" + sp.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO salidaprocesional VALUES ('"+sp.getIdentificador()+"', '"+sp.getAnio()+"', '"+sp.getDescripcion()+"')";            
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
        
        cbd.un_sql = "SELECT identificador FROM salidaprocesional WHERE identificador=" + sp.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE salidaprocesional SET fecha='"+sp.getAnio()+"', descripcion_salida_procesional='"+sp.getDescripcion()+"' WHERE identificador="+sp.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + sp.getIdentificador() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public SalidaProcesional leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM salidaprocesional WHERE identificador=" + sp.getIdentificador()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                sp.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + sp.getIdentificador()+ " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + sp.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return sp;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from salidaprocesional WHERE identificador="+sp.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <SalidaProcesional> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <SalidaProcesional> salida = new ArrayList <SalidaProcesional>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM salidaprocesional";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                sp = new SalidaProcesional();
                sp.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                sp.setIdentificador(Integer.parseInt(cbd.resultado.getString("fecha")));
                sp.setIdentificador(Integer.parseInt(cbd.resultado.getString("descripcion_salida_procesional")));
                
                salida.add(sp);
            }           
        }
        return salida;
    }
    
    /*Buscar Filtro*/
    public ArrayList<SalidaProcesional> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <SalidaProcesional> salida = new ArrayList <SalidaProcesional>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM salidaprocesional WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY identificador;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
                sp = new SalidaProcesional();
                
                sp.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                sp.setAnio(cbd.resultado.getString("fecha"));
                sp.setDescripcion(String.valueOf(cbd.resultado.getString("descripcion_salida_procesional")));
                
                salida.add(sp);
        }
        
        return salida;
        
    }
}