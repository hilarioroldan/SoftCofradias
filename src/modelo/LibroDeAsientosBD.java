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
public class LibroDeAsientosBD {
    
    private LibroDeAsientos l1;
    
    public LibroDeAsientosBD(){}    
    
    public LibroDeAsientosBD(LibroDeAsientos l1) {
        this.l1 = l1;
    }
    
    
    public void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    
    
    Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM librodeasientos WHERE identificador=" + l1.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
    
        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO librodeasientos VALUES ('"+l1.getIdentificador()+"','"+l1.getFecha()+"','"+l1.getConcepto()+"','"+l1.getIngresar()+"','"+l1.getDeber()+"','"+l1.getMayordomia_id()+"','"+l1.getCuenta_bancaria_id()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una miembro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
