package modelo;

import controlador.ControladorHermandad;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        try {
            FileInputStream propFile = new FileInputStream("C:\\Users\\hilario\\Documents\\NetBeansProjects\\SoftCofradias\\SoftCofradias\\src\\modelo\\configurarbd.txt");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            System.setProperties(p);
            if (System.getProperty("mostrarpropierties").compareTo("si") == 0) {
                System.getProperties().list(System.out);
            }
        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo de configuracion" + e);
            System.exit(-1);
        } catch (java.io.IOException w) {
            JOptionPane.showMessageDialog(null, "Ocurrio algun error de I/O");
            System.exit(-1);
        }

        try {
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
        } catch (java.lang.ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio la excepcion " + e);
            JOptionPane.showMessageDialog(null, "es probable que no se puede encontrar la clase del conector jdbc" + System.getProperty("driver")
                    + "Agregela en su classpath con la opcion -cp");
            System.exit(-1);
        } catch (java.lang.InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de instanciamiento" + e.toString());
            System.exit(-1);

        } catch (java.lang.IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de acceso ilegal" + e.toString());
            System.exit(-1);
        }

        try {
            VentanaPrincipal x = new VentanaPrincipal();
            x.setVisible(true);
            x.setLocationRelativeTo(null);

//        } catch (ClassNotFoundException e) {
//            System.out.println("Ocurrio la excepcion" + e.toString());
//        } catch (InstantiationException e) {
//            System.out.println("Ocurrio una excepcion SQL" + e.getMessage());
//        } catch (IllegalAccessException e) {
//            System.out.println("Ocurrio la excepcion de acceso ilegal" + e.toString());
//        } catch (SQLException e) {
//            System.out.println("Ocurrio una excepcion SQL" + e.getMessage());
        } catch (Error e) {
            System.out.println("Ocurrio el error" + e.getMessage());
        }
    }
}