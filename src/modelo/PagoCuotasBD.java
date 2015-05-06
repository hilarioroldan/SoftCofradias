
package modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class PagoCuotasBD {
    
    
    private PagoCuotas p;

    public PagoCuotasBD(PagoCuotas p) {
        this.p = p;
    }
    
    public void guardar()throws ClassNotFoundException, 
                                InstantiationException, 
                                IllegalAccessException,
                                SQLException {
     Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
        
        cbd.un_sql = "SELECT identificador FROM pagocuotas WHERE identificador=" + p.getIdentificador() +";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
        
        
         if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO pagocuotas VALUES ('"+p.getIdentificador()+"','"+p.getNumero_hermano_id()+"','"+p.getMayordomia_id()+"','"+p.getPrecio()+"','"+p.getFecha()+"','"+p.isPagado()+"')";            
            cbd.un_st.executeUpdate(cbd.un_sql);
       } else {
            JOptionPane.showInputDialog(null, "Error al registrar una cuota", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
            
            
            }
    
    
}
