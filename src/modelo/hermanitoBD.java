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
/**
 *
 * @author Alex Dopino
 */
public class hermanitoBD {
    
     private hermanito h2;
private static int contador=1;
    
    public hermanitoBD(hermanito h2) {
        this.h2 = h2;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
         Conexion cbd2 = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT numero_hermano FROM hermanos WHERE numero_hermano=" + h2.getNumero_hermano()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO hermanos (numero_hermano, nombre,apellidos,nif,municipio,provincia,pais,tfno,email,banco,cuenta_bancaria,tipo_pago_id,forma_pago_id,id_hermandad,fecha_nacimiento,direccion,codigo_postal) VALUES ('"+h2.getNumero_hermano()+"', '"+h2.getNombre()+"', '"+h2.getApellido()+"', '"+h2.getNif()+"','"+h2.getMunicipio()+"','"+h2.getPais()+"','"+h2.getProvincia()+"','"+h2.getTfno()+"','"+h2.getEmail()+"','"+h2.getBanco()+"','"+h2.getCuenta_bancaria()+"','"+h2.getTipo_pago_id()+"','"+h2.getForma_pago_id()+"',1,'"+h2.getFecha()+"','"+h2.getDireccion()+"','"+h2.getCp()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una hermano", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /*Metodo Modificar*/
     public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT numero_hermano FROM hermanos WHERE numero_hermano=" + h2.getNumero_hermano()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
             cbd.un_sql = "UPDATE hermanos SET  nombre='"+h2.getNombre()+"',apellidos='"+h2.getApellido()+"',nif='"+h2.getNif()+"',municipio='"+h2.getMunicipio()+"',provincia='"+h2.getProvincia()+"',pais='"+h2.getPais()+"',tfno='"+h2.getTfno()+"',email='"+h2.getEmail()+"',banco='"+h2.getBanco()+"',cuenta_bancaria='"+h2.getCuenta_bancaria()+"',tipo_pago_id='"+h2.getTipo_pago_id()+"',forma_pago_id='"+h2.getForma_pago_id()+"',id_hermandad=1, fecha_nacimiento='"+h2.getFecha()+"',direccion='"+h2.getDireccion()+"', codigo_postal='"+h2.getCp()+"' WHERE numero_hermano=" + h2.getNumero_hermano()+";";            
        
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + h2.getNumero_hermano()+ "No se encuentra en la tabla'" + this.getClass().getName());
        } 
        
    }

   public hermanito leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
         cbd.un_sql = "SELECT numero_hermano FROM hermanos WHERE numero_hermano=" + h2.getNumero_hermano()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
             h2.setNumero_hermano(Integer.parseInt(cbd.resultado.getString("numero_hermano")));
                h2.setNombre(cbd.resultado.getString("nombre"));
                h2.setApellido(cbd.resultado.getString("apellidos"));
                h2.setNif(cbd.resultado.getString("nif"));
                h2.setMunicipio(cbd.resultado.getString("municipio"));
                h2.setProvincia(cbd.resultado.getString("provincia"));
                h2.setPais(cbd.resultado.getString("pais"));
                h2.setTfno(cbd.resultado.getString("tfno"));
                h2.setEmail(cbd.resultado.getString("email"));
                h2.setBanco(cbd.resultado.getString("banco"));
                h2.setCuenta_bancaria(cbd.resultado.getString("cuenta_bancaria"));
                h2.setTipo_pago_id(Integer.parseInt(cbd.resultado.getString("tipo_pago_id")));
                h2.setForma_pago_id(Integer.parseInt(cbd.resultado.getString("forma_pago_id")));
                h2.setId_hermandad(Integer.parseInt(cbd.resultado.getString("id_hermandad"))); 
                h2.setFecha(cbd.resultado.getString("fecha_nacimiento")); 
                h2.setDireccion(cbd.resultado.getString("direccion")); 
                h2.setCp(Integer.parseInt(cbd.resultado.getString("codigo_postal")));
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
               throw new Error("Registro " + h2.getNumero_hermano() + " No se encuentra en la tabla hermanos'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
               throw new Error("Registro " + h2.getNumero_hermano() + " No se encuentra en la tabla hermanos'" + this.getClass().getName());
        }
        
        return h2;        
    }
   
    public ArrayList <hermanito> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <hermanito> listaHermanos = new ArrayList <hermanito>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM hermanos";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                h2 = new hermanito();
                h2.setNumero_hermano(cbd.resultado.getInt("numero_hermano"));
                h2.setNombre(cbd.resultado.getString("nombre"));
                h2.setApellido(cbd.resultado.getString("apellidos"));
                h2.setNif(cbd.resultado.getString("nif"));
                h2.setMunicipio(cbd.resultado.getString("municipio"));
                h2.setProvincia(cbd.resultado.getString("provincia"));
                h2.setPais(cbd.resultado.getString("pais"));
                h2.setTfno(cbd.resultado.getString("tfno"));
                h2.setEmail(cbd.resultado.getString("email"));
                h2.setBanco(cbd.resultado.getString("banco"));
                h2.setCuenta_bancaria(cbd.resultado.getString("cuenta_bancaria"));
                h2.setTipo_pago_id(Integer.parseInt(cbd.resultado.getString("tipo_pago_id")));
                h2.setForma_pago_id(Integer.parseInt(cbd.resultado.getString("forma_pago_id")));
                h2.setId_hermandad(Integer.parseInt(cbd.resultado.getString("id_hermandad")));
                  h2.setFecha(cbd.resultado.getString("fecha_nacimiento")); 
                h2.setDireccion(cbd.resultado.getString("direccion")); 
                h2.setCp(Integer.parseInt(cbd.resultado.getString("codigo_postal")));
                listaHermanos.add(h2);
            }           
        }
        return listaHermanos;
    }
 public ArrayList <hermanito> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <hermanito> listaHermanos = new ArrayList <hermanito>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM hermanos WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY numero_hermano;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
          h2 = new hermanito();
                h2.setNumero_hermano(cbd.resultado.getInt("numero_hermano"));
                h2.setNombre(cbd.resultado.getString("nombre"));
                h2.setApellido(cbd.resultado.getString("apellidos"));
                h2.setNif(cbd.resultado.getString("nif"));
                h2.setMunicipio(cbd.resultado.getString("municipio"));
                h2.setProvincia(cbd.resultado.getString("provincia"));
                h2.setPais(cbd.resultado.getString("pais"));
                h2.setTfno(cbd.resultado.getString("tfno"));
                h2.setEmail(cbd.resultado.getString("email"));
                h2.setBanco(cbd.resultado.getString("banco"));
                h2.setCuenta_bancaria(cbd.resultado.getString("cuenta_bancaria"));
                h2.setTipo_pago_id(cbd.resultado.getInt("tipo_pago_id"));
                h2.setForma_pago_id(cbd.resultado.getInt("forma_pago_id"));
                h2.setId_hermandad(cbd.resultado.getInt("id_hermandad"));
                h2.setFecha(cbd.resultado.getString("fecha_nacimiento")); 
                h2.setDireccion(cbd.resultado.getString("direccion")); 
                h2.setCp(Integer.parseInt(cbd.resultado.getString("codigo_postal")));
              listaHermanos.add(h2);
            
        }
        
        return listaHermanos;
        
    }
   public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from hermanos WHERE numero_hermano="+h2.getNumero_hermano()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
        Conexion cbd2 = ConectarServicio.getInstancia().getConexionDb();
        
        cbd2.un_sql = "DELETE from formapago  WHERE identificador="+h2.getForma_pago_id()+";";
        cbd2.un_st.executeUpdate(cbd2.un_sql);
    }
    
}
