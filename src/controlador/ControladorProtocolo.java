/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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

        ACEPTAR,SALIR,SELECCION1,SELECCION2,MODIFICAR,ELIMINAR;
    }
       
       public void iniciar(){
       
       p = new Protocolo();
       pv1 = new ProtocoloVista();
       pv1.setVisible(true);
       pv1.setLocationRelativeTo(null);
       cargarProtocoloss();
       pv1.Aceptar.setActionCommand("ACEPTAR");
       pv1.Aceptar.addActionListener(this);
       pv1.Salir.setActionCommand("SALIR");
       pv1.Salir.addActionListener(this);
       pv1.eliminar.setActionCommand("ELIMINAR");
       pv1.eliminar.addActionListener(this);
       pv1.modificar.setActionCommand("MODIFICAR");
       pv1.modificar.addActionListener(this);
      
       
      seleccion();
       
      pv1.tabla_proto.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }

          
        });
       
       
       
       
       }
       
       
         public void agregarDatos(int identificador, String descripcion, int numero_hermano_id, int salida_profesional_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
       
        p = new Protocolo(identificador,descripcion,numero_hermano_id,salida_profesional_id);
        p.guardar();
        
    } 
         public void eliminarDatos(int identificador){
         
        p = new Protocolo(identificador);
        p.eliminar();
         
         }
         
       ///METODO PARA SELECCIONAR EL HERMANO QUE QUEREMOS INTRODUCIR
         
        
         
         //METODO PARA CARGAR ESA SELECCION
         
         
         public void seleccion(){
         
          try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="select identificador from salidaprocesional;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pv1.Seleccion_salida2.removeAllItems();
              
            while(cbd.resultado.next()){
                
                
               
               pv1.Seleccion_salida2.addItem(1); 
               
  
            }                      
           
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
         
         }
  
         
          public void seleccion1(){
         
          try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="select identificador from salidaprocesional;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pv1.Seleccion_salida3.removeAllItems();
              
            while(cbd.resultado.next()){
                
                
               
               pv1.Seleccion_salida3.addItem(1); 
               
  
            }                      
           
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
         
         }
  
         
         
        public void eliminar(){
        
            
         int clic = pv1.tabla_proto.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una
          
        if (clic!=-1) {
            
            String num = pv1.tabla_proto.getValueAt(clic, 0).toString();
        
            eliminarDatos(Integer.parseInt(num));
            
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        
        cargarProtocoloss();

       pv1.tabla_proto.setModel(new DefaultTableModel());
        
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
                    
                    String descripcion = pv1.area_de_texto2.getText();
                    
                    int numero_hermano_id=Integer.parseInt(pv1.id_muestra2.getText()); 
                    
                    int salida_profesional_id=(int) pv1.Seleccion_salida2.getSelectedItem();
                    
                   
                    
                   
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
  
   
       case ELIMINAR:
           eliminar();
           cargarProtocoloss();
           
           break;
   
       case MODIFICAR:
           pv1.modificacion.setSize(465, 400);
           pv1.modificacion.setVisible(true);
           pv1.modificacion.setLocationRelativeTo(null);
           
            int clic = pv1.tabla_proto.getSelectedRow();
        
        if (clic!=-1) {
                pv1.id_muestra3.setText(pv1.tabla_proto.getValueAt(clic, 1).toString());
                pv1.Seleccion_salida3.setSelectedItem(pv1.tabla_proto.getValueAt(clic, 3).toString());
                pv1.area_de_texto3.setText(pv1.tabla_proto.getValueAt(clic, 2).toString());
   }             
           
           break;

   }
   }   

       public void modificarProtocolo() {
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
            int clic = pv1.tabla_proto.getSelectedRow();
            
            if (clic!=-1) {
                
            String descripcion = pv1.area_de_texto3.getText();
                    
                    int numero_hermano_id=Integer.parseInt(pv1.id_muestra3.getText()); 
                    
                    int salida_profesional_id=(int) pv1.Seleccion_salida3.getSelectedItem();
                    
            agregarDatos(identificador,descripcion,numero_hermano_id,salida_profesional_id);
            
            } else {
                JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Has introducido un parámetro un incorrecto. Revisa los campos correctamente");
        }
        
        
       }
       
       
       
       
       
       
     public void cargarProtocoloss() {
        DefaultTableModel ff;

        try {
            String titulo[] = {"identificador","numero de hermano","descripcion","salida procesional id"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[4];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from protocolo";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                
                fila[0] = cdb.resultado.getString("identificador");
                fila[1] = cdb.resultado.getString(1);
                fila[2] = cdb.resultado.getString(2);
                fila[3] = cdb.resultado.getString(3);
                
                ff.addRow(fila);
            }

            pv1.tabla_proto.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            pv1.tabla_proto.setRowSorter(ordenar);
            pv1.tabla_proto.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
e.printStackTrace();
//JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
      private void tablaEntidadConocida1MousePressed(MouseEvent evt) {
             
      
      
      
      
      }
    
}
