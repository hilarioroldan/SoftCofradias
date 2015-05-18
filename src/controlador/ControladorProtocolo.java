/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.LibroDeAsientos;
import modelo.Protocolo;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.ProtocoloVista;
/**
 *
 * @author alex
 */
public class ControladorProtocolo implements ActionListener{
    
    Protocolo p;
    ProtocoloVista pv1;
    String numero;
    int id;
  
    
       public enum di {

        ACEPTAR,SALIR,SELECCION1,SELECCION2;
    }
       
       public void iniciar(){
       
       p = new Protocolo();
       pv1 = new ProtocoloVista();
       pv1.setVisible(true);
       pv1.setLocationRelativeTo(null);
       
       pv1.Aceptar.setActionCommand("ACEPTAR");
       pv1.Aceptar.addActionListener(this);
       pv1.Salir.setActionCommand("SALIR");
       pv1.Salir.addActionListener(this);
      
       
      seleccion();
       
     
       
       
       
       
       }
       
       
         public void agregarDatos(int identificador, String descripcion, int numero_hermano_id, int salida_profesional_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
       
        p = new Protocolo(identificador,descripcion,numero_hermano_id,salida_profesional_id);
        p.guardar();
        
    } 
       ///METODO PARA SELECCIONAR EL HERMANO QUE QUEREMOS INTRODUCIR
         
        
         
         //METODO PARA CARGAR ESA SELECCION
         
         
         public void seleccion(){
         
          try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="select identificador from salidaprocesional;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
       pv1.Seleccion_salida.removeAllItems();
              
            while(cbd.resultado.next()){
                
                
               
               pv1.Seleccion_salida.addItem(1); 
               //pv1.hermano_seleccion.addItem();
  
            }                      
           
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
         
         }
  
        
       
       
       
   public void actionPerformed(ActionEvent e) {
        
   switch(ControladorProtocolo.di.valueOf(e.getActionCommand())){
   
       
       case ACEPTAR:

                    
                   
                       try {
                    
                    Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
                    cbd.un_sql="select max(identificador) from protocolo";
                    cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
                    
                    int identificador=0; //= Integer.parseInt(lv1.ident.getText());
                    
                    if(cbd.resultado.next()){
                    
                    identificador=cbd.resultado.getInt(1)+1;
                    }else{
                    
                    identificador=1;
                    
                    }
                    
                    String descripcion = pv1.area_de_texto.getText();
                    
                    int numero_hermano_id=Integer.parseInt(pv1.id_muestra.getText()); 
                    
                    int salida_profesional_id=(int) pv1.Seleccion_salida.getSelectedItem();
                    
                   
                    
                   
                    try {
                        agregarDatos(identificador,descripcion,numero_hermano_id,salida_profesional_id);
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "No se Ha introducido ningun dato");
                        Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
                        
                    } catch (InstantiationException ex) {
                        Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE,null, ex);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
        Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
    }            
                
                    
                    
        
           
           
           
           break;
  
   
   
   
   
   
   }
   
   }     
       
    
    
    
}
