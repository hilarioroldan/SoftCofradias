
package servicios;

import java.sql.*;

public class Conexion {
    
    public Connection conexion = null;
    public Statement un_st = null; // enviar sentencia SQL a la bd
    public DatabaseMetaData dbmt;
    public String s_conexion = null;
    public ResultSet resultado = null;
    public String un_sql = null;
    
    protected String jdbc;
    protected String driver;
    protected String host;
    protected String database;
    protected String username;
    protected String password;

    public Conexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        jdbc = System.getProperty("jdbc");
        driver = System.getProperty("driver");
        host = System.getProperty("host");
        database = System.getProperty("database");
        username = System.getProperty("username");
        password = System.getProperty("password");
        
        iniciardb();
    }
    
    public void iniciardb() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        s_conexion = jdbc + host + "/" + database;
        Class.forName(driver).newInstance();
        conexion = DriverManager.getConnection(s_conexion,username,password);
        dbmt = conexion.getMetaData();
        un_st = conexion.createStatement();
    }   
    
}