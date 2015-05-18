
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class CargosJuntaBD {
    
    CargosJunta cj = null;
            
    public CargosJuntaBD(CargosJunta cj) {
        this.cj = cj;
    }
    
    /* Metodo grabar */
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM cargosjunta WHERE identificador=" + cj.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO cargosjunta (identificador, descripcion) VALUES (?, ?);";
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            ps.setInt(1, cj.getIdentificador());
            ps.setString(2, cj.getDescripcion());
            
            ps.execute();   // ejecutamos
            ps.close();     // se cierra la conexion           
            
       } else {
            JOptionPane.showMessageDialog(null, "Error al registrar un cargo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /* Metodo modificar */
     public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM cargosjunta WHERE identificador=" + cj.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE cargosjunta SET descripcion = ?" + " WHERE identificador="+cj.getIdentificador()+";";
            
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            
            ps.setString(1, cj.getDescripcion());
            ps.executeUpdate();
            ps.close();
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + cj.getIdentificador() + "No se encuentra en la tabla cargos'" + this.getClass().getName());
        } 
        
    }     
    
    
    /* Buscar filtro */
     public ArrayList <CargosJunta> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <CargosJunta> listaCargos = new ArrayList <>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM cargosjunta WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY identificador;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
             cj = new CargosJunta();
                cj.setIdentificador(cbd.resultado.getInt("identificador"));
                cj.setDescripcion(cbd.resultado.getString("descripcion"));
                
                listaCargos.add(cj);
            
        }
        
        return listaCargos;
        
    }
    
    /* Metodo borrar */
     public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from cargosjunta WHERE identificador=" + cj.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
}
