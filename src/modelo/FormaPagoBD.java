
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class FormaPagoBD {
    
    FormaPago fo;
    
    FormaPagoBD(FormaPago fo){
        this.fo=fo;
    }
    
    /* Método Grabar */
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM formapago WHERE identificador=" + fo.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO formapago(identificador, forma_pago) VALUES ("+fo.getIdentificador()+", '"+fo.getForma_pago()+"');";
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar una Forma de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void modificar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM formapago WHERE identificador=" + fo.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE formapago SET forma_pago='"+fo.getForma_pago()+"' WHERE identificador="+fo.getIdentificador()+";";            
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
             /*Generamos nuestro propio error, luego este se activa por el catch quien lo lanza nuevamente*/
                throw new Error("Registro" + fo.getIdentificador() + "No se encuentra en la tabla Forma de pago'" + this.getClass().getName());
        }
        
    }
    
    /* Método borrar */
    
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "DELETE from formapago WHERE identificador=" + fo.getIdentificador()+";";
        cbd.un_st.executeUpdate(cbd.un_sql);
    }
    
    /* Leer todos */
    
    public ArrayList <FormaPago> leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        ArrayList <FormaPago> listaFormaPago = new ArrayList <FormaPago>();
        
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        cbd.un_sql = "SELECT * FROM formapago";
        cbd.resultado=cbd.un_st.executeQuery(cbd.un_sql);       
        
        if (cbd.resultado != null) {
            while(cbd.resultado.next()){
                fo = new FormaPago();
                fo.setForma_pago(cbd.resultado.getString("nombre"));
                
                listaFormaPago.add(fo);
            }           
        }
        return listaFormaPago;
    }
    
}
