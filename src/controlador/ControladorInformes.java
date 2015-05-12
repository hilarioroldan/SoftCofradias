/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import servicios.ConectarServicio;
import servicios.Conexion;

/**
 *
 * @author hilario
 */
public class ControladorInformes {
    
    public void cargarInformeListaHermanos() {
        
        try {
            
            Conexion cbd = null;
            
            try {
                cbd = ConectarServicio.getInstancia().getConexionDb();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ControladorInformes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String repor = "C:\\Users\\hilario\\Documents\\NetBeansProjects\\SoftCofradias\\SoftCofradias\\src\\informes\\listaHermanos.jasper";
            JasperReport jp = null;
            jp = (JasperReport) JRLoader.loadObjectFromFile(repor);
            JasperPrint jprint = JasperFillManager.fillReport(jp, null, cbd.getConnection());
            JasperViewer jv = new JasperViewer(jprint, false);
            jv.setVisible(true);
            jv.setTitle(repor);
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void cargarInformeListaEnseresyPatrimonio() {
        
        try {
            
            Conexion cbd = null;
            
            try {
                cbd = ConectarServicio.getInstancia().getConexionDb();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ControladorInformes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String repor = "C:\\Users\\hilario\\Documents\\NetBeansProjects\\SoftCofradias\\SoftCofradias\\src\\informes\\listaEnseresyPatrimonio.jasper";
            JasperReport jp = null;
            jp = (JasperReport) JRLoader.loadObjectFromFile(repor);
            JasperPrint jprint = JasperFillManager.fillReport(jp, null, cbd.getConnection());
            JasperViewer jv = new JasperViewer(jprint, false);
            jv.setVisible(true);
            jv.setTitle(repor);
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void cargarInformeListaLibrodeAsientos() {
        
        try {
            
            Conexion cbd = null;
            
            try {
                cbd = ConectarServicio.getInstancia().getConexionDb();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ControladorInformes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String repor = "C:\\Users\\hilario\\Documents\\NetBeansProjects\\SoftCofradias\\SoftCofradias\\src\\informes\\listaLibrodeAsientos.jasper";
            JasperReport jp = null;
            jp = (JasperReport) JRLoader.loadObjectFromFile(repor);
            JasperPrint jprint = JasperFillManager.fillReport(jp, null, cbd.getConnection());
            JasperViewer jv = new JasperViewer(jprint, false);
            jv.setVisible(true);
            jv.setTitle(repor);
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
