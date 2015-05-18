
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class JuntaGobiernoBD {
    
     private JuntaGobierno j;

    public JuntaGobiernoBD(JuntaGobierno j) {
        this.j = j;
    }
    
    /*Metodo insertar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM juntagobierno WHERE identificador=" + j.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO juntagobierno VALUES ('"+j.getIdentificador()+"','"+j.getNombre()+"','"+j.getApellido()+"','"+j.getCargo()+"','"+j.getObservaciones()+"','"+j.getNumero_hermano_id()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una miembro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

     /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM juntagobierno WHERE identificador=" + j.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE juntagobierno SET nombre='"+j.getNombre()+"',apellidos='"+j.getApellido()+"', cargo='"+j.getCargo()+"', observaciones='"+j.getObservaciones()+"', numero_hermano_id='"+j.getNumero_hermano_id()+"' WHERE identificador="+j.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + j.getIdentificador() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public JuntaGobierno leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM juntagobierno WHERE identificador=" + j.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                j.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                j.setNombre(cbd.resultado.getString("nombre"));
                //h.setAño_fundacion(Integer.parseInt(cbd.resultado.getString("año_fundacion")));
                j.setApellido(cbd.resultado.getString("apellidos"));
                j.setCargo(cbd.resultado.getInt("cargo"));
                j.setObservaciones(cbd.resultado.getString("observacion"));
                j.setNumero_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
               
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + j.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + j.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return j;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from juntagobierno WHERE identificador="+j.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <JuntaGobierno> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <JuntaGobierno> listaGobierno = new ArrayList <JuntaGobierno>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM juntadegobierno";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                j = new JuntaGobierno();
                 j.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                j.setNombre(cbd.resultado.getString("nombre"));
                //h.setAño_fundacion(Integer.parseInt(cbd.resultado.getString("año_fundacion")));
                j.setApellido(cbd.resultado.getString("apellidos"));
                j.setCargo(cbd.resultado.getInt("cargo"));
                j.setObservaciones(cbd.resultado.getString("observacion"));
                j.setNumero_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
                
                listaGobierno.add(j);
            }           
        }
        return listaGobierno ;
    }
    
    /*Buscar Filtro*/
    public ArrayList <JuntaGobierno> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
         ArrayList <JuntaGobierno> listaGobierno = new ArrayList <JuntaGobierno>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM juntagobierno WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY identificador;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            j = new JuntaGobierno();
                 j = new JuntaGobierno();
                 j.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                j.setNombre(cbd.resultado.getString("nombre"));
                //h.setAño_fundacion(Integer.parseInt(cbd.resultado.getString("año_fundacion")));
                j.setApellido(cbd.resultado.getString("apellidos"));
                j.setCargo(cbd.resultado.getInt("cargo"));
                j.setObservaciones(cbd.resultado.getString("observaciones"));
                j.setNumero_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
                
                listaGobierno.add(j);
            
        }
        
        return listaGobierno;
        
    }
    
    
}
