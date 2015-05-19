
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class PapeletaSitioBD {
        
    private PapeletaSitio ps;

    public PapeletaSitioBD(PapeletaSitio ps) {
        this.ps = ps;
    }
    
    /*Metodo Grabar*/
    
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM papeletasitio WHERE identificador=" + ps.getIdentificador()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO papeletasitio VALUES ('"+ps.getIdentificador()+"', '"+ps.getNumero_hermano_id()+"', '1', '"+ps.getDonativo()+"', '"+ps.getSale()+"', '"+ps.getNumero_papeleta()+"', '"+ps.getFecha()+"');";
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una papeleta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*Metodo Modificar*/
    
    public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM papeletasitio WHERE identificador=" + ps.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE papeletasitio SET numero_hermano_id='"+ps.getNumero_hermano_id()+"', fecha='"+ps.getFecha()+"', sale='"+ps.getSale()+"', donativo='"+ps.getDonativo()+"', numero_papeleta='"+ps.getNumero_papeleta()+"' WHERE identificador="+ps.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
            JOptionPane.showMessageDialog(null, "BD "+ps.getIdentificador());
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + ps.getIdentificador() + "No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        } 
        
    }
    
    /*Metodo Leer*/
    
    public PapeletaSitio leer() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM papeletasitio WHERE identificador=" + ps.getIdentificador()+";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                ps.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ps.getIdentificador()+ " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
            }
            
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ps.getIdentificador() + " No se encuentra en la tabla proveedor Ubicacion'" + this.getClass().getName());
        }
        
        return ps;        
    } 
    
    /*Metodo Borrar*/
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from papeletasitio WHERE identificador="+ps.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /*Leer todos*/
    public ArrayList <PapeletaSitio> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <PapeletaSitio> papeleta = new ArrayList <PapeletaSitio>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM papeletasitio";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                ps = new PapeletaSitio();
                ps.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                ps.setFecha(cbd.resultado.getString("fecha"));
                ps.setNumero_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
                ps.setNumero_papeleta(Integer.parseInt(cbd.resultado.getString("numero_papeleta")));
                ps.setSale(cbd.resultado.getString("sale"));
                ps.setDonativo(Integer.parseInt(cbd.resultado.getString("donativo")));
                ps.setSalida_procesional_id(Integer.parseInt(cbd.resultado.getString("salida_procesional_id")));
                papeleta.add(ps);
            }           
        }
        return papeleta;
    }
    
    /*Buscar Filtro*/
    public ArrayList <PapeletaSitio> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <PapeletaSitio> papeleta = new ArrayList <PapeletaSitio>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM papeletasitio WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY donativo;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
                ps = new PapeletaSitio();
                ps.setIdentificador(Integer.parseInt(cbd.resultado.getString("identificador")));
                ps.setFecha(cbd.resultado.getString("fecha"));
                ps.setNumero_hermano_id(Integer.parseInt(cbd.resultado.getString("numero_hermano_id")));
                ps.setNumero_papeleta(Integer.parseInt(cbd.resultado.getString("numero_papeleta")));
                ps.setSale(cbd.resultado.getString("sale"));
                ps.setDonativo(Integer.parseInt(cbd.resultado.getString("donativo")));
                ps.setSalida_procesional_id(Integer.parseInt(cbd.resultado.getString("salida_procesional_id")));
                papeleta.add(ps);
                
        }
        
        return papeleta;
        
    }
    
}