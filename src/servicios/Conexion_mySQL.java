
package servicios;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_mySQL extends Conexion {
    
    private String opciones;

    public Conexion_mySQL() throws ClassNotFoundException, 
                                    InstantiationException, 
                                    IllegalAccessException, 
                                    SQLException {
        
       opciones = System.getProperty("options");
        iniciardb();
    }
    
    public void iniciardb() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        s_conexion = jdbc + host + "/" + database + "?" + opciones;
        Class.forName(driver).newInstance();
        conexion = DriverManager.getConnection(s_conexion,username,password);
        dbmt = conexion.getMetaData();
        un_st = conexion.createStatement();
    }   
}