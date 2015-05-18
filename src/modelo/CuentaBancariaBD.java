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
public class CuentaBancariaBD {
    
    private CuentaBancaria c1;
    
    public CuentaBancariaBD(){}
    public CuentaBancariaBD(CuentaBancaria c1){
    
        this.c1=c1;
    
    }
    
     
        
    public void insertar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM cuentabancaria WHERE identificador=" + c1.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO cuentabancaria VALUES ('"+c1.getIdentificador()+"','"+c1.getDescripcion()+"','"+c1.getTitulo()+"','"+c1.getBanco()+"','"+c1.getDireccion()+"','"+c1.getMunicipio()+"','"+c1.getCodigo_postal()+"','"+c1.getProvincia()+"','"+c1.getPais()+"','"+c1.getNum_cuenta()+"','"+c1.getIban()+"','"+c1.getBic()+"','"+c1.getCantidad()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una miembro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
     public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM cuentabancaria WHERE identificador=" + c1.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
             cbd.un_sql = "UPDATE cuentabancaria SET  descripcion='"+c1.getDescripcion()+"',titulo='"+c1.getTitulo()+"',banco='"+c1.getBanco()+"',direccion='"+c1.getDireccion()+"',municipio='"+c1.getMunicipio()+"',codigo_postal='"+c1.getCodigo_postal()+"',provincia='"+c1.getProvincia()+"',pais='"+c1.getPais()+"',num_cuenta='"+c1.getNum_cuenta()+"',iban='"+c1.getIban()+"',bic='"+c1.getBic()+"',cantidad='"+c1.getCantidad()+"' WHERE identificador=" + c1.getIdentificador() +"";            
        
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + c1.getIdentificador() + "No se encuentra en la tabla'" + this.getClass().getName());
        } 
        
    }
    
}
