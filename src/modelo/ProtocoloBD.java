/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

/**
 *
 * @author alex
 */
public class ProtocoloBD {
    
    Protocolo p1;
    
    
    public ProtocoloBD(){}
    
    public ProtocoloBD(Protocolo p1){
    
    this.p1=p1;
    
    
    }
    
    public void guardar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    
     Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM protocolo WHERE identificador=" + p1.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        
         if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO protocolo VALUES ('"+p1.getIdentificador()+"','"+p1.getDescripcion()+"','"+p1.getNumero_hermano()+"','"+p1.getSalida_profesional_id()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una cuota", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
            
    
    
    
    
    }
    
    
    
}
