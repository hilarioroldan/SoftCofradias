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
import modelo.PagoCuotas;
import vista.PagoCuotasVista;

/**
 *
 * @author alex
 */
public class ControladorPagoCuotas implements ActionListener {
    
    PagoCuotas p;
    PagoCuotasVista p1;
    int contador=0;

   

   
    
   public enum di {

        ACEPTAR,SALIR,MODIFICAR;
    }

   public void iniciar() {
        p = new PagoCuotas();
        p1 = new PagoCuotasVista();
        p1.setVisible(true);
        p1.setLocationRelativeTo(null);
   

        //se añade las acciones a los controles del formulario padre
        p1.aceptar.setActionCommand("ACEPTAR");
        p1.aceptar.addActionListener(this);
  
        
        p1.salir.setActionCommand("SALIR");
        p1.salir.addActionListener(this);
      
     

       
            
       
    } 
    
    
      public void actionPerformed(ActionEvent e) {
        switch (ControladorJuntaGobierno.di.valueOf(e.getActionCommand())) {

            
            case ACEPTAR:
                
            int identificador = Integer.parseInt(p1.ide.getText()); 
            int numero_hermano_id = Integer.parseInt(p1.hermano.getText());
            String date=p1.fechar.getText();
            int precio=Integer.parseInt(p1.price.getText());
            int mayordomia_id=Integer.parseInt(p1.mayordomia.getText());
            String pagado = String.valueOf(p1.eleccion.getSelectedItem());

                try {
               agregarPago(identificador,numero_hermano_id,mayordomia_id,pagado,date,precio);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case SALIR:
                p1.dispose();
                break;
                
            

             
          case BUSCAR:
     // cargarTablaMiembros2();
                
                    break;
           case ACEPTAR1:
               
              break;  
               
               
              
    }
        

}
   
   
      private void agregarPago(int identificador, int numero_hermano_id,int mayordomia_id, String pagado,String date ,int precio) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
      p = new PagoCuotas(identificador,numero_hermano_id,mayordomia_id,pagado,date,precio);
      p.guardar();   }
}
