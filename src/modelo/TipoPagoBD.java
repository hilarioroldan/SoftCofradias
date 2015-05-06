
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class TipoPagoBD {
    
    TipoPago tp;
    
    TipoPagoBD(TipoPago tp) {
        this.tp=tp;
    }
    
    /* Método Grabar */
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM tipopago WHERE identificador=" + tp.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO tipopago(identificador, tipo_pago, precio) VALUES ("+tp.getIdentificador()+", '"+tp.getDescripcion()+"', "+tp.getPrecio()+");";
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar un Tipo de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void modificar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM tipopago WHERE identificador=" + tp.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE tipopago SET tipo_pago='"+tp.getDescripcion()+"', precio="+tp.getPrecio()+" WHERE identificador="+tp.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + tp.getIdentificador() + "No se encuentra en la tabla Forma de pago'" + this.getClass().getName());
        }
        
    }
    
    /* Método borrar */
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from tipopago WHERE identificador=" + tp.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /* Leer todos */
    
    public ArrayList <TipoPago> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <TipoPago> listaTipoPago = new ArrayList <TipoPago>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM tipopago";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                tp = new TipoPago();
                tp.setDescripcion(cbd.resultado.getString("tipo_pago"));
                tp.setPrecio(cbd.resultado.getDouble("precio"));
                
                listaTipoPago.add(tp);
            }           
        }
        return listaTipoPago;
    }
    
}