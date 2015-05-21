
package modelo;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class InventarioBD {
    
    Inventario i = new Inventario();
    
    public InventarioBD(Inventario i) {
        this.i=i;
    }
    
    /* Metodo grabar */
    public void grabar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM inventario WHERE identificador=" + i.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
       if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO inventario (identificador, nombre, autor, estilo, fecha_realizacion, procedencia, valoracion_economica, mejora, restauracion, cantidad, observaciones, secretaria_id, adquisicion, imagen, fecha_baja) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            ps.setInt(1, i.getIdentificador());
            ps.setString(2, i.getNombre());
            ps.setString(3, i.getAutor());
            ps.setString(4, i.getEstilo());
            ps.setString(5, i.getFecha_realizacion());
            ps.setString(6, i.getProcedencia());
            ps.setDouble(7, i.getValoracion_economica());
            ps.setString(8, i.getMejora());
            ps.setString(9, i.getRestauracion());
            ps.setInt(10, i.getCantidad());
            ps.setString(11, i.getObservaciones());
            ps.setInt(12, 1);
            ps.setString(13, i.getAdquisicion());
            ps.setBinaryStream(14, i.getImagen(), i.getLongitudBytes()); // almacenamos la imagen: FileInputStream -donde se almacena la imagen- y su tamaño
            ps.setString(15, i.getFecha_baja());
            ps.execute();   // ejecutamos
            ps.close();     // se cierra la conexion           
            
       } else {
            JOptionPane.showMessageDialog(null, "Error al registrar una hermandad", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /* Metodo modificar */
     public void modificar() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM inventario WHERE identificador=" + i.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE inventario SET identificador= ?" + ", nombre = ?" + ", autor = ?" + ", estilo = ?" + ", fecha_realizacion = ?" + ", procedencia = ?" + ", valoracion_economica = ?" + ", mejora = ?" + ", restauracion = ?" + ", cantidad = ?" + ", observaciones = ?" + ", adquisicion = ?" + ", imagen = ?" + ", fecha_baja = ? WHERE identificador="+i.getIdentificador()+";";
            
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            
            ps.setInt(1, i.getIdentificador());
            ps.setString(2, i.getNombre());
            ps.setString(3, i.getAutor());
            ps.setString(4, i.getEstilo());
            ps.setString(5, i.getFecha_realizacion());
            ps.setString(6, i.getProcedencia());
            ps.setDouble(7, i.getValoracion_economica());
            ps.setString(8, i.getMejora());
            ps.setString(9, i.getRestauracion());
            ps.setInt(10, i.getCantidad());
            ps.setString(11, i.getObservaciones());
            //ps.setInt(11, 1);
            ps.setString(12, i.getAdquisicion());
            ps.setBinaryStream(13, i.getImagen(), i.getLongitudBytes());
            ps.setString(14, i.getFecha_baja());
            ps.executeUpdate();
            ps.close();
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + i.getIdentificador() + "No se encuentra en la tabla Inventario Ubicacion'" + this.getClass().getName());
        } 
        
    }
     
     /* Metodo modificar sin imagen*/
     public void modificarSinImagen() throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM inventario WHERE identificador=" + i.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE inventario SET identificador= ?" + ", nombre = ?" + ", autor = ?" + ", estilo = ?" + ", fecha_realizacion = ?" + ", procedencia = ?" + ", valoracion_economica = ?" + ", mejora = ?" + ", restauracion = ?" + ", cantidad = ?" + ", observaciones = ?" + ", adquisicion = ?" + ", fecha_baja = ? WHERE identificador="+i.getIdentificador()+";";
            
            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);
            
            ps.setInt(1, i.getIdentificador());
            ps.setString(2, i.getNombre());
            ps.setString(3, i.getAutor());
            ps.setString(4, i.getEstilo());
            ps.setString(5, i.getFecha_realizacion());
            ps.setString(6, i.getProcedencia());
            ps.setDouble(7, i.getValoracion_economica());
            ps.setString(8, i.getMejora());
            ps.setString(9, i.getRestauracion());
            ps.setInt(10, i.getCantidad());
            ps.setString(11, i.getObservaciones());
            //ps.setInt(11, 1);
            ps.setString(12, i.getAdquisicion());
            ps.setString(13, i.getFecha_baja());
            ps.executeUpdate();
            ps.close();
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + i.getIdentificador() + "No se encuentra en la tabla Inventario Ubicacion'" + this.getClass().getName());
        } 
        
    }
     
    /* Metodo leer */
     public Inventario leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM inventario WHERE identificador = " + i.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                i.setIdentificador(cbd.resultado.getInt("identificador"));
                i.setNombre(cbd.resultado.getString("nombre"));
                i.setAutor(cbd.resultado.getString("autor"));
                i.setEstilo(cbd.resultado.getString("estilo"));
                i.setFecha_realizacion(cbd.resultado.getString("fecha_realizacion"));
                i.setProcedencia(cbd.resultado.getString("procedencia"));
                i.setValoracion_economica(cbd.resultado.getDouble("valoracion_economica"));
                i.setMejora(cbd.resultado.getString("mejora"));
                i.setRestauracion(cbd.resultado.getString("restauracion"));
                i.setCantidad(cbd.resultado.getInt("cantidad"));
                i.setObservaciones(cbd.resultado.getString("observaciones"));
                i.setAdquisicion(cbd.resultado.getString("adquisicion"));
                i.setImagen((FileInputStream) cbd.resultado.getBinaryStream("imagen"));
                i.setFecha_baja(cbd.resultado.getString("fecha_baja"));
                
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + i.getIdentificador() + " No se encuentra en la tabla Inventario Ubicacion'" + this.getClass().getName());
            }
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + i.getIdentificador() + " No se encuentra en la tabla Inventario Ubicacion'" + this.getClass().getName());
        }
        return i;        
    }   
    
    /* Metodo leer todos */
     public ArrayList <Inventario> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <Inventario> listaInventario = new ArrayList <>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM inventario";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);  
                
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                i = new Inventario();
                i.setIdentificador(cbd.resultado.getInt("identificador"));
                i.setNombre(cbd.resultado.getString("nombre"));
                i.setAutor(cbd.resultado.getString("autor"));
                i.setEstilo(cbd.resultado.getString("estilo"));
                i.setFecha_realizacion(cbd.resultado.getString("fecha_realizacion"));
                i.setProcedencia(cbd.resultado.getString("procedencia"));
                i.setValoracion_economica(cbd.resultado.getDouble("valoracion_economica"));
                i.setMejora(cbd.resultado.getString("mejora"));
                i.setRestauracion(cbd.resultado.getString("restauracion"));
                i.setCantidad(cbd.resultado.getInt("cantidad"));
                i.setObservaciones(cbd.resultado.getString("observaciones"));
                i.setAdquisicion(cbd.resultado.getString("adquisicion"));
                i.setImagen((FileInputStream) cbd.resultado.getBinaryStream("imagen"));
                i.setFecha_baja(cbd.resultado.getString("fecha_baja"));
                
                listaInventario.add(i);
            }           
        }
        return listaInventario;
    }
    
    /* Buscar filtro */
     public ArrayList <Inventario> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <Inventario> listaInventario = new ArrayList <>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM inventario WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY nombre;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            i = new Inventario();
            i.setIdentificador(cbd.resultado.getInt("identificador"));
            i.setNombre(cbd.resultado.getString("nombre"));
            i.setAutor(cbd.resultado.getString("autor"));
            i.setEstilo(cbd.resultado.getString("estilo"));
            i.setFecha_realizacion(cbd.resultado.getString("fecha_realizacion"));
            i.setProcedencia(cbd.resultado.getString("procedencia"));
            i.setValoracion_economica(cbd.resultado.getDouble("valoracion_economica"));
            i.setMejora(cbd.resultado.getString("mejora"));
            i.setRestauracion(cbd.resultado.getString("restauracion"));
            i.setCantidad(cbd.resultado.getInt("cantidad"));
            i.setObservaciones(cbd.resultado.getString("observaciones"));
            i.setAdquisicion(cbd.resultado.getString("adquisicion"));
            //i.setImagen((FileInputStream) cbd.resultado.getBinaryStream("imagen"));
            i.setFecha_baja(cbd.resultado.getString("fecha_baja"));

            listaInventario.add(i);
            
        }
        
        return listaInventario;
        
    }
    
    /* Metodo borrar */
     public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from inventario WHERE identificador=" + i.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
}
