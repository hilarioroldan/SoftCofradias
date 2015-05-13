
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class EntidadesConocidasBD {
    
    private EntidadesConocidas ec;
    
    EntidadesConocidasBD(EntidadesConocidas ec) {
        this.ec=ec;
    }
    
    /* Método Grabar */
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM entidadesconocidas WHERE identificador=" + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO entidadesconocidas(identificador, nombre, localidad, domicilio, telf1, telf2, secretaria_id, cp, provincia, email) VALUES ("+ec.getIdentificador()+", '"+ec.getNombre()+"', '"+ec.getLocalidad()+"', '"+ec.getDomicilio()+"', '"+ec.getTelf1()+"', '"+ec.getTelf2()+"',1, '"+ec.getCP()+"', '"+ec.getProvincia()+"', '"+ec.getEmail()+"');";
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar una Entidad Conocida", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /* Método Modificar */
    
    public void modificar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM entidadesconocidas WHERE identificador=" + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE entidadesconocidas SET nombre='"+ec.getNombre()+"', localidad='"+ec.getLocalidad()+"', domicilio='"+ec.getDomicilio()+"', telf1='"+ec.getTelf1()+"', telf2='"+ec.getTelf2()+"', cp='"+ec.getCP()+"', provincia='"+ec.getProvincia()+"', email='"+ec.getEmail()+"' WHERE identificador="+ec.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + ec.getIdentificador() + "No se encuentra en la tabla Entidades Conocidas Ubicacion'" + this.getClass().getName());
        }
        
    }
    
    /* Método Leer */
    
    public EntidadesConocidas leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM entidadesconocidas WHERE identificador = " + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                ec.setNombre(cbd.resultado.getString("nombre"));
                ec.setLocalidad(cbd.resultado.getString("localidad"));
                ec.setDomicilio(cbd.resultado.getString("domicilio"));
                ec.setTelf1(cbd.resultado.getInt("telf1"));
                ec.setTelf2(cbd.resultado.getInt("telf2"));
                ec.setCP(Integer.parseInt(cbd.resultado.getString("cp")));
                ec.setProvincia(cbd.resultado.getString("provincia"));
                ec.setEmail(cbd.resultado.getString("email"));
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ec.getIdentificador() + " No se encuentra en la tabla EntidadesConocidas Ubicacion'" + this.getClass().getName());
            }
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ec.getIdentificador() + " No se encuentra en la tabla EntidadesConocidas Ubicacion'" + this.getClass().getName());
        }
        return ec;
        
    }
    
    /* Método borrar */
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from entidadesconocidas WHERE identificador=" + ec.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /* Leer todos */
    
    public ArrayList <EntidadesConocidas> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <EntidadesConocidas> listaEntidadesConocidas = new ArrayList <EntidadesConocidas>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM entidadesconocidas";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                ec = new EntidadesConocidas();
                ec.setNombre(cbd.resultado.getString("nombre"));
                ec.setLocalidad(cbd.resultado.getString("localidad"));
                ec.setDomicilio(cbd.resultado.getString("domicilio"));
                ec.setTelf1(cbd.resultado.getInt("telf1"));
                ec.setTelf2(cbd.resultado.getInt("telf2"));
                ec.setCP(Integer.parseInt(cbd.resultado.getString("cp")));
                ec.setProvincia(cbd.resultado.getString("provincia"));
                ec.setEmail(cbd.resultado.getString("email"));
                
                listaEntidadesConocidas.add(ec);
            }           
        }
        return listaEntidadesConocidas;
    }
    
    /* Buscar Filtro */
    
    public ArrayList <EntidadesConocidas> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <EntidadesConocidas> listaEntidadesConocidas = new ArrayList <EntidadesConocidas>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM entidadesconocidas WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY nombre;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            ec = new EntidadesConocidas();
                ec.setNombre(cbd.resultado.getString("nombre"));
                ec.setLocalidad(cbd.resultado.getString("localidad"));
                ec.setDomicilio(cbd.resultado.getString("domicilio"));
                ec.setTelf1(cbd.resultado.getInt("telf1"));
                ec.setTelf2(cbd.resultado.getInt("telf2"));
                ec.setCP(Integer.parseInt(cbd.resultado.getString("cp")));
                ec.setProvincia(cbd.resultado.getString("provincia"));
                ec.setEmail(cbd.resultado.getString("email"));
                ec.setIdentificador(cbd.resultado.getInt("identificador"));
                
                listaEntidadesConocidas.add(ec);
            
        }
        
        return listaEntidadesConocidas;
        
    }
    
}