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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.LibroDeAsientoVista;

/**
 *
 * @author alex
 */ 
public class ControladorLibroAsiento implements ActionListener{
String fexha;
    LibroDeAsientos l1;
    LibroDeAsientoVista lv1;
    JFreeChart Grafica;
    DefaultCategoryDataset Datos = new DefaultCategoryDataset();
    
     public enum di {

        ACEPTAR,ACEPTAR1,ACEPTAR2,ACEPTARCAL,CANCELAR,SALIR,VERCAL,BUSCAR,INGRESAR,RETIRAR,VER,VER2,GRAFICO;
    }
    
     
     public void iniciar(){
     
     l1 = new LibroDeAsientos();
     lv1 = new LibroDeAsientoVista();
     lv1.setVisible(true);
     lv1.setLocationRelativeTo(null);
     lv1.cant.setEnabled(false);
     lv1.retiro01.setEnabled(false);
     cargar_tabla_Bancaria();
     

     lv1.acept.setActionCommand("ACEPTAR");
     lv1.acept.addActionListener(this);
     lv1.cancel.setActionCommand("CANCELAR");
     lv1.acept.addActionListener(this);
     lv1.ingreso.setActionCommand("INGRESAR");
     lv1.ingreso.addActionListener(this);
     lv1.retiro.setActionCommand("RETIRAR");
     lv1.retiro.addActionListener(this);
  //   lv1.ver1.setActionCommand("VER1"); EN DESARROLLO
   //  lv1.ver1.addActionListener(this);
     lv1.ver2.setActionCommand("VER2");
     lv1.ver2.addActionListener(this);
     lv1.aceptarIng.setActionCommand("ACEPTAR1");
     lv1.aceptarIng.addActionListener(this);
       lv1.aceptarIng1.setActionCommand("ACEPTAR2");
     lv1.aceptarIng1.addActionListener(this);
     lv1.retiro.setActionCommand("RETIRAR");
     lv1.retiro.addActionListener(this);
     lv1.ver2.setActionCommand("VER2");
     lv1.ver2.addActionListener(this);
     lv1.grafic.setActionCommand("GRAFICO");
     lv1.grafic.addActionListener(this);
     lv1.aceptarCal.setActionCommand("ACEPTARCAL");
     lv1.aceptarCal.addActionListener(this);
     lv1.calcu.setActionCommand("VERCAL");
      lv1.calcu.addActionListener(this);
      lv1.uno.setActionCommand(lv1.calculadoratxt.getText());
     lv1.uno.addActionListener(this);
     cargarCmbBD1();  
   // inicializacionGrafica();
     
     lv1.tabla_movimiento.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
         });
     }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        
    
    switch (ControladorLibroAsiento.di.valueOf(e.getActionCommand())) {
        
               case ACEPTAR1:
                     int cog1=Integer.parseInt(lv1.textIngreso.getText());    
                      lv1.cant.setText(cog1+"");
                      lv1.ingresar.dispose();
                  break;
                   
                   
                 case ACEPTAR2:
                     int cog2=Integer.parseInt(lv1.textIngreso1.getText());    
                     lv1.retiro01.setText(cog2+"");
                     lv1.retirar.dispose();
                  break;   
            
            case ACEPTAR:
                
            int identificador = Integer.parseInt(lv1.ident.getText()); 
            String fecha= null;
            String concepto=lv1.concept.getText();
            
            int dia=Integer.parseInt(lv1.dia.getText());
            String mes1 = lv1.mes.getText();
            int año=Integer.parseInt(lv1.año.getText());
        
            
            if(mes1.equals("enero")){
            int uno=01;
           fecha =año+"-"+uno+"-"+dia; 
            
            }else{
            
             if(mes1.equals("febrero")){
            int uno=02;
           fecha =año+"-"+uno+"-"+dia; 
            
            }else{
              if(mes1.equals("marzo")){
            int uno=03;
           fecha =año+"-"+uno+"-"+dia; 
            
            }else{
              
               if(mes1.equals("abril")){
            int uno=04;
          fecha =año+"-"+uno+"-"+dia; 
            
                }else{
                if(mes1.equals("mayo")){
            int uno=05;
            fecha =año+"-"+uno+"-"+dia; ;
            
            }else{
                
                 if(mes1.equals("junio")){
            int uno=06;
            fecha =año+"-"+uno+"-"+dia; 
            
            }else{
                  if(mes1.equals("julio")){
            int uno=07;
            fecha =año+"-"+uno+"-"+dia; 
            
            }else{
                  
             if(mes1.equals("agosto")){
            int uno=8;
            fecha =año+"-"+uno+"-"+dia; 
            
            }else{
              if(mes1.equals("septiembre")){
            int uno=9;
            fecha =año+"-"+uno+"-"+dia; 
            
            }else{
              
               if(mes1.equals("octubre")){
            int uno=10;
           fecha =año+"-"+uno+"-"+dia; 
            
            }else{
                if(mes1.equals("noviembre")){
            int uno=11;
           fecha =año+"-"+uno+"-"+dia; 
            
            }else{
                 if(mes1.equals("diciembre")){
            int uno=12;
            fecha =año+"-"+uno+"-"+dia; 
            
                                }           
                              }   
                         }
                          }         
                      }   
                  }
                 
                 }
                
                }
               
               }
              
              }
             
             }
            
            }
            
          
            //para ingresar
             
              int ingresar=Integer.parseInt(lv1.cant.getText());
              int deber=Integer.parseInt(lv1.retiro01.getText());
                   
            int mayordomia_id=1;
            int cuenta_bancaria_id=1;
      

                try {
               agregarDatos(identificador, fecha, concepto, ingresar, deber, mayordomia_id, cuenta_bancaria_id);
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
            break;
                
            case ACEPTARCAL:
      
                int cog3=Integer.parseInt(lv1.calculadoratxt.getText());    
                     lv1.textIngreso.setText(cog3+"");
                     lv1.teclado.dispose();
                break;
            case SALIR:
                //j1.dispose();
                break;
                
        
                
          case INGRESAR:
              lv1.ingresar.setEnabled(true);
              lv1.ingresar.setVisible(true);
              lv1.ingresar.setSize(400, 200);
        
              break;
              
              
          case RETIRAR:            
              lv1.retirar.setEnabled(true);
              lv1.retirar.setVisible(true);
              lv1.retirar.setSize(400, 200);
              break;
          
              
          case VER2:
              lv1.movimiento.setEnabled(true);
              lv1.movimiento.setVisible(true);
              lv1.movimiento.setSize(590, 400);
              
              break;
          case GRAFICO:
             
inicializacionGrafica();
lv1.grafico.dispose();

              button();
              break;
          case  VERCAL:
              lv1.teclado.setEnabled(true);
              lv1.teclado.setVisible(true);
              lv1.teclado.setSize(380, 260);

              break;
                  
    }
 
}
     private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE cuentabancaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            lv1.seleccion_id.removeAllItems();
            
            while(cbd.resultado.next()){
                lv1.seleccion_id.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    //metodo para agregar a la base de datos
     public void agregarDatos(int identificador, String fecha, String concepto, int ingresar, int deber, int mayordomia_id, int cuenta_bancaria_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
       
        l1 = new LibroDeAsientos(identificador, fecha, concepto, ingresar, deber, mayordomia_id, cuenta_bancaria_id);
        l1.insertar();
        
    }
        
     public void button(){
     
     

ChartPanel Panel = new ChartPanel(Grafica);
JFrame Ventana = new JFrame("Movimiento de Saldo");
Ventana.getContentPane().add(Panel);
Ventana.pack();
Ventana.setVisible(true);
Ventana.setSize(1200,600);
Ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     }

   
     //seleccionamos en la tabla de movimientos bancarios
     public void seleccionTabla(){
     
     int clic = lv1.tabla_movimiento.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una
 
        if (clic!=-1) {
           lv1.año_busqueda.setEnabled(false);
            lv1.año_busqueda.setText(lv1.tabla_movimiento.getValueAt(clic, 0).toString());
             lv1.cantida.setText(lv1.tabla_movimiento.getValueAt(clic, 3).toString());
            lv1.retirado.setText(lv1.tabla_movimiento.getValueAt(clic, 4).toString());
  }
     }
     
     //copn esto pretendemos cargar nuestra tabla de movimientos bancarios con el fin de obtener nuestra grafica
    public void cargar_tabla_Bancaria(){
     
        DefaultTableModel ff;
        int clic = lv1.tabla_movimiento.getSelectedRow();
        
        try {
            String titulo[] = {"identificador","fecha","concepto","ingresos","retiros","id bancario"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[7];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from librodeasientos" ;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                
                //fila[0] = cdb.resultado.getString(0);          
              fila[0] = cdb.resultado.getString(1);
                fexha = cdb.resultado.getString("fecha");
                fexha = fexha.substring(0,4);//+"-"+fexha.substring(5,7)+"-"+fexha.substring(8,10);
                fila[1]=fexha;
 
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);
                   
                   ff.addRow(fila);
                
            }

           lv1.tabla_movimiento.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
           lv1.tabla_movimiento.setRowSorter(ordenar);
           lv1.tabla_movimiento.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
     private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
        int clic = lv1.tabla_movimiento.getSelectedRow(); // se guarda en la variable el  de la fila cuando se hace click en una

        if (clic!=-1) {
            lv1.año_busqueda.setText(lv1.tabla_movimiento.getValueAt(clic, 1).toString());
            lv1.cantida.setText(lv1.tabla_movimiento.getValueAt(clic, 3).toString());
            lv1.retirado.setText(lv1.tabla_movimiento.getValueAt(clic, 4).toString());
           
            
        //añadimos el año que deseamos mostrar en la grafica    
        Grafica = ChartFactory.createBarChart("Movimientos Bancarios",
        "Año "+lv1.año_busqueda.getText(), "Dinero", Datos,
        PlotOrientation.VERTICAL, true, true, false);
     
        }
    }
       public void inicializacionGrafica() {

         
         Datos.addValue(Integer.parseInt(lv1.cantida.getText()), "Ingresos","Ingresado");
        Datos.addValue(Integer.parseInt(lv1.retirado.getText()), "Retiros", "Retirado");       
         

Grafica = ChartFactory.createBarChart("Movimientos Bancarios",
"Año de Movimiento "+lv1.año_busqueda.getText(), "Ingresos", Datos,
PlotOrientation.VERTICAL, true, true, false);
     
  

     }
     
     
     
    
    }
    
     
   

    
    
    
    
    

