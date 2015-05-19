
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class LoteriasYSorteosDB {
    
    private LoteriasYSorteos ec;
    
    LoteriasYSorteosDB(LoteriasYSorteos ec) {
        this.ec=ec;
    }
    
    /* Método Grabar */
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM loterias WHERE identificador=" + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO loterias VALUES ('"+ec.getIdentificador()+"', '"+ec.getSorteo()+"', '"+ec.getFecha_devolucion()+"', '"+ec.getCantidad()+"', '"+ec.getPrecio_unidad()+"', '"+ec.getGanancia_unidad()+"',1)";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el Sorteo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /* Método Modificar */
    
    public void modificar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM loterias WHERE identificador=" + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE loterias SET sorteo='"+ec.getSorteo()+"', fecha_devolucion='"+ec.getFecha_devolucion()+"', cantidad='"+ec.getCantidad()+"', precio_unidad='"+ec.getPrecio_unidad()+"', ganancia_unidad='"+ec.getGanancia_unidad()+"' WHERE identificador="+ec.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + ec.getIdentificador() + "No se encuentra en la tabla Loterias Ubicacion'" + this.getClass().getName());
        }
        
    }
    
    /* Método Leer */
    
    public LoteriasYSorteos leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM entidadesconocidas WHERE identificador = " + ec.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            if (cbd.resultado.next()) {
                ec.setSorteo(cbd.resultado.getString("sorteo"));
                ec.setCantidad(cbd.resultado.getDouble("cantidad"));
                ec.setFecha_devolucion(cbd.resultado.getString("fecha_devolucion"));
                ec.setGanancia_unidad(cbd.resultado.getDouble("ganancia_unidad"));
                ec.setPrecio_unidad(cbd.resultado.getDouble("precio_unidad"));
            } else {
                 /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ec.getIdentificador() + " No se encuentra en la tabla Loterias Ubicacion'" + this.getClass().getName());
            }
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro " + ec.getIdentificador() + " No se encuentra en la tabla Loterias Ubicacion'" + this.getClass().getName());
        }
        return ec;
        
    }
    
    /* Método borrar */
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from loterias WHERE identificador=" + ec.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /* Leer todos */
    
    public ArrayList <LoteriasYSorteos> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <LoteriasYSorteos> listaLoterias = new ArrayList <LoteriasYSorteos>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM loterias";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                ec = new LoteriasYSorteos();
                ec.setSorteo(cbd.resultado.getString("sorteo"));
                ec.setCantidad(cbd.resultado.getDouble("cantidad"));
                ec.setFecha_devolucion(cbd.resultado.getString("fecha_devolucion"));
                ec.setGanancia_unidad(cbd.resultado.getDouble("ganancia_unidad"));
                ec.setPrecio_unidad(cbd.resultado.getDouble("precio_unidad"));
               
                listaLoterias.add(ec);
            }           
        }
        return listaLoterias;
    }
    
    /* Buscar Filtro */
    
    public ArrayList <LoteriasYSorteos> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        ArrayList <LoteriasYSorteos> listaSorteos = new ArrayList <LoteriasYSorteos>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT * FROM loterias WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY sorteo;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        while(cbd.resultado.next()){
            
            ec = new LoteriasYSorteos();
                ec.setIdentificador(cbd.resultado.getInt("identificador"));
                ec.setSorteo(cbd.resultado.getString("sorteo"));
                ec.setCantidad(cbd.resultado.getDouble("cantidad"));
                ec.setFecha_devolucion(cbd.resultado.getString("fecha_devolucion"));
                ec.setGanancia_unidad(cbd.resultado.getDouble("ganancia_unidad"));
                ec.setPrecio_unidad(cbd.resultado.getDouble("precio_unidad"));
               
                listaSorteos.add(ec);
            
        }
        
        return listaSorteos;
        
    }
    
}