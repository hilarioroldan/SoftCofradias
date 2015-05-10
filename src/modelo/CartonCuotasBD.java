
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class CartonCuotasBD {
    
    public CartonCuotas cc;
    
    public CartonCuotasBD(CartonCuotas cc) {
        this.cc=cc;
    }
    
    /* Metodo grabar */
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM cartoncuotas WHERE identificador=" + cc.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO cartoncuotas (identificador, año, numero_hermano_id) VALUES (?, ?, ?);";
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            ps.setInt(1, cc.getIdentificador());
            ps.setInt(2, cc.getAño());
            ps.setInt(3, cc.getNumero_hermano());
            
            ps.execute();   // ejecutamos
            ps.close();     // se cierra la conexion           
            
       } else {
            JOptionPane.showMessageDialog(null, "Error al registrar un carton", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /* Metodo modificar */
     public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM cartoncuotas WHERE identificador=" + cc.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE cartoncuotas SET identificador= ?" + ", enero = ?" + ", febrero = ?" + ", marzo = ?" + ", abril = ?" + ", mayo = ?" + ", junio = ?" + ", julio = ?" + ", agosto = ?" + ", septiembre = ?" + ", octubre = ?" + ", noviembre = ?" + ", diciembre = ?" + ", año = ?" + ",numero_hermano_id = ?" + " WHERE identificador="+cc.getIdentificador()+";";
            
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            
            ps.setInt(1, cc.getIdentificador());
            ps.setBoolean(2, cc.isEnero());
            ps.setBoolean(3, cc.isFebrero());
            ps.setBoolean(4, cc.isMarzo());
            ps.setBoolean(5, cc.isAbril());
            ps.setBoolean(6, cc.isMayo());
            ps.setBoolean(7, cc.isJunio());
            ps.setBoolean(8, cc.isJulio());
            ps.setBoolean(9, cc.isAgosto());
            ps.setBoolean(10, cc.isSeptiembre());
            ps.setBoolean(11, cc.isOctubre());
            ps.setBoolean(12, cc.isNoviembre());
            ps.setBoolean(13, cc.isDiembre());
            ps.setInt(14, cc.getAño());
            ps.setInt(15, cc.getNumero_hermano());
            ps.executeUpdate();
            ps.close();
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + cc.getIdentificador() + "No se encuentra en la tabla carton cuotas'" + this.getClass().getName());
        } 
        
    }
     
    /* Metodo leer */
     public CartonCuotas leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM cartoncuotas WHERE identificador = " + cc.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                cc.setIdentificador(cbd.resultado.getInt("identificador"));
                cc.setEnero(cbd.resultado.getBoolean("enero"));
                cc.setFebrero(cbd.resultado.getBoolean("febrero"));
                cc.setMarzo(cbd.resultado.getBoolean("marzo"));
                cc.setAbril(cbd.resultado.getBoolean("abril"));
                cc.setMayo(cbd.resultado.getBoolean("mayo"));
                cc.setJunio(cbd.resultado.getBoolean("junio"));
                cc.setJulio(cbd.resultado.getBoolean("julio"));
                cc.setAgosto(cbd.resultado.getBoolean("agosto"));
                cc.setSeptiembre(cbd.resultado.getBoolean("septiembre"));
                cc.setOctubre(cbd.resultado.getBoolean("octubre"));
                cc.setNoviembre(cbd.resultado.getBoolean("noviembre"));
                cc.setDiembre(cbd.resultado.getBoolean("diciembre"));
                cc.setAño(cbd.resultado.getInt("año"));
                cc.setNumero_hermano(cbd.resultado.getInt("numero_hermano_id"));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + cc.getIdentificador() + " No se encuentra en la tabla carton cuotas Ubicacion'" + this.getClass().getName());
            }
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + cc.getIdentificador() + " No se encuentra en la tabla carton cuotas Ubicacion'" + this.getClass().getName());
        }
        return cc;        
    }   
    
    /* Metodo leer todos */
     public ArrayList <CartonCuotas> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <CartonCuotas> listaCartonCuotas = new ArrayList <>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM cartoncuotas";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);  
                
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                cc = new CartonCuotas();
                cc.setIdentificador(cbd.resultado.getInt("identificador"));
                cc.setEnero(cbd.resultado.getBoolean("enero"));
                cc.setFebrero(cbd.resultado.getBoolean("febrero"));
                cc.setMarzo(cbd.resultado.getBoolean("marzo"));
                cc.setAbril(cbd.resultado.getBoolean("abril"));
                cc.setMayo(cbd.resultado.getBoolean("mayo"));
                cc.setJunio(cbd.resultado.getBoolean("junio"));
                cc.setJulio(cbd.resultado.getBoolean("julio"));
                cc.setAgosto(cbd.resultado.getBoolean("agosto"));
                cc.setSeptiembre(cbd.resultado.getBoolean("septiembre"));
                cc.setOctubre(cbd.resultado.getBoolean("octubre"));
                cc.setNoviembre(cbd.resultado.getBoolean("noviembre"));
                cc.setDiembre(cbd.resultado.getBoolean("diciembre"));
                cc.setAño(cbd.resultado.getInt("año"));
                cc.setNumero_hermano(cbd.resultado.getInt("numero_hermano_id"));
                
                listaCartonCuotas.add(cc);
            }           
        }
        return listaCartonCuotas;
    }
    
    /* Buscar filtro */
     public ArrayList <CartonCuotas> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <CartonCuotas> listaCartonCuotas = new ArrayList <>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM cartoncuotas WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY identificador;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
             cc = new CartonCuotas();
                cc.setIdentificador(cbd.resultado.getInt("identificador"));
                cc.setEnero(cbd.resultado.getBoolean("enero"));
                cc.setFebrero(cbd.resultado.getBoolean("febrero"));
                cc.setMarzo(cbd.resultado.getBoolean("marzo"));
                cc.setAbril(cbd.resultado.getBoolean("abril"));
                cc.setMayo(cbd.resultado.getBoolean("mayo"));
                cc.setJunio(cbd.resultado.getBoolean("junio"));
                cc.setJulio(cbd.resultado.getBoolean("julio"));
                cc.setAgosto(cbd.resultado.getBoolean("agosto"));
                cc.setSeptiembre(cbd.resultado.getBoolean("septiembre"));
                cc.setOctubre(cbd.resultado.getBoolean("octubre"));
                cc.setNoviembre(cbd.resultado.getBoolean("noviembre"));
                cc.setDiembre(cbd.resultado.getBoolean("diciembre"));
                cc.setAño(cbd.resultado.getInt("año"));
                cc.setNumero_hermano(cbd.resultado.getInt("numero_hermano_id"));
                
                listaCartonCuotas.add(cc);
            
        }
        
        return listaCartonCuotas;
        
    }
    
    /* Metodo borrar */
     public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from cartoncuotas WHERE identificador=" + cc.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
}
