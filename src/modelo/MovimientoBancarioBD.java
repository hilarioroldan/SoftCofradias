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
public class MovimientoBancarioBD {
    
    private MovimientoBancario m1;
    
    public MovimientoBancarioBD(){}
    
    
    public MovimientoBancarioBD(MovimientoBancario m1){
    
    this.m1=m1;
    
    }
    public void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    
    
    Conexion cbd2 = ConectarServicio.getInstancia().getConexionDb();
    
     
       
        
        cbd2.un_sql = "SELECT identificador FROM movimientobancario WHERE identificador=" +m1.getIdentificador() +";";
        cbd2.resultado = cbd2.un_st.executeQuery(cbd2.un_sql);
    
       
        
        
        if (cbd2.resultado != null) {
            cbd2.un_sql = "INSERT INTO movimientobancario VALUES ('"+m1.getIdentificador()+"','"+m1.getAño()+"','"+m1.getMes()+"','"+m1.getDia()+"','"+m1.getIngreso()+"','"+m1.getRetiro()+"','"+m1.getId_bancario()+"')";            
            cbd2.un_st.executeUpdate(cbd2.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una miembro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
}
