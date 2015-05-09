/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.hermanito;
import modelo.hermanitoBD;
import modelo.PagoCuotas;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.PagoCuotasVista;

/**
 *
 * @author alex
 */
public class ControladorPagoCuotas implements ActionListener {
    hermanito h1 = new hermanito();
    PagoCuotas p;
    PagoCuotasVista p1;
    int contador=0;

    
   public enum di {

        ACEPTAR,SALIR,MODIFICAR,
        GESTION,VER,SALIR1,CANCELAR,BUSCAR;
    }

   public void iniciar() {
        p = new PagoCuotas();
        p1 = new PagoCuotasVista(); 
     
        p1.setVisible(true);
        p1.setLocationRelativeTo(null);
        cargarTablaHermanos();
      

        //se añade las acciones a los controles del formulario padre
          p1.cancelar.setActionCommand("CANCELAR");
         p1.cancelar.addActionListener(this);
        p1.aceptar.setActionCommand("ACEPTAR");
        p1.aceptar.addActionListener(this);
        p1.salir.setActionCommand("SALIR");
        p1.salir.addActionListener(this);
       p1.gesti.setActionCommand("GESTION");
       p1.gesti.addActionListener(this);
       p1.pagos.setActionCommand("VER");
       p1.pagos.addActionListener(this);
       p1.salir1.setActionCommand("SALIR1");
       p1.salir1.addActionListener(this);
        p1.busqueda.setActionCommand("BUSCAR");
       p1.busqueda.addActionListener(this);
 
    } 
    
    
      public void actionPerformed(ActionEvent e) {
        switch (ControladorPagoCuotas.di.valueOf(e.getActionCommand())) {

            
            case ACEPTAR:
                
            int identificador = Integer.parseInt(p1.ide.getText()); 
            int numero_hermano_id = Integer.parseInt(p1.her.getText());
            String date=p1.fechaa.getText();
            int precio=Integer.parseInt(p1.pric.getText());
            int mayordomia_id=1;
            String pagado = String.valueOf(p1.seleccio.getSelectedItem());

               try {
               agregarPago(identificador,numero_hermano_id,date,mayordomia_id,precio,pagado);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            }
              this.p1.dispose();
            break;
            case SALIR:
               p1.dispose();
                break;
            case SALIR1:
                p1.verpagos.dispose();
                break;
               
           case GESTION:
               
               
               p1.cuotas.setSize(350,380);
               p1.cuotas.setVisible(true);
               
              //  int clic = p1.tablaHermano.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una
            int clic = p1.tablaHermano.getSelectedRow();
           if (clic!=-1) {
               
            p1.her.setEnabled(false);
            p1.her.setText(p1.tablaHermano.getValueAt(clic, 0).toString());
            JOptionPane.showMessageDialog(null,p1.her.getText() );
            
            cargarPagos();
           
        }       
               break;     
               
           case VER:
               
               p1.verpagos.setSize(450, 500);
               p1.verpagos.setVisible(true);
               
               break;
           case CANCELAR:
               p1.verpagos.dispose();
               break;
           
           case BUSCAR:
               cargarTablaHermanos2();
               break;
               
               
    }
}
       public void cargarTablaHermanos() {
        DefaultTableModel ff;
        

        try {
            String titulo[] = {"Numero de Hermano", "Nombre", "Apellidos", "Nif/Dni", "Municipio", "Provincia","Pais", "Tfno", "Email", "Banco","Cuenta bancaria","forma de pago","tipo de pago","id hermandad"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[14];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from hermanos";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);
                fila[6] = cdb.resultado.getString(7);
                fila[7] = cdb.resultado.getString(8);
                fila[8] = cdb.resultado.getString(9);
                fila[9] = cdb.resultado.getString(10);
                fila[10] = cdb.resultado.getString(11);
                fila[11] = cdb.resultado.getString(12);
                fila[12] = cdb.resultado.getString(13);
                fila[13] = cdb.resultado.getString(14);

                ff.addRow(fila);
            }

            p1.tablaHermano.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaHermano.setRowSorter(ordenar);
            p1.tablaHermano.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
       }
      //metodo para cargar los pagos
       
      public void cargarPagos(){
      DefaultTableModel ff;
        int numero_hermano_i = Integer.parseInt(p1.her.getText());
         try {
            String titulo[] = {"año", "precio", "pagado"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[3];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
           
            // se guarda en la variable el numero de la fila cuando se hace click en una
        //String numero_hermano_id = (String) p1.tablaHermano.getValueAt(clic, 0);
      
            cdb.un_sql = "select * from pagocuotas WHERE numero_hermano_id="+p1.her.getText()+";";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                
                fila[0] = cdb.resultado.getString(3);         
                fila[1] = cdb.resultado.getString(5);
                fila[2] = cdb.resultado.getString(6);
              

                ff.addRow(fila);
            }

            p1.tablaPagos.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaPagos.setRowSorter(ordenar);
            p1.tablaPagos.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
          //  JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" +e, "Error", JOptionPane.ERROR_MESSAGE);
       e.printStackTrace();
        }
      
      } 
      
       public void cargarTablaHermanos2() {
        hermanito hermanos = null;
        DefaultTableModel ff;
        try {
           String titulo[] = {"Numero de hermano", "Nombre", "Apellidos", "Nif/Dni", "Municipio", "Provincia","Pais", "Tfno", "Email", "Banco","Cuenta bancaria","forma de pago","tipo de pago","id hermandad"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String[] fila = new String[14];
            ArrayList <hermanito> x;
            String campo =  (String) p1.seleccion.getSelectedItem();
            String filtro = p1.filtro.getText();
            x = h1.buscarFiltro(filtro, campo);
            
            Iterator <hermanito> it = x.iterator();
            while(it.hasNext()){
                hermanos = it.next();
               fila[0] = String.valueOf(hermanos.getNumero_hermano());
                fila[1] = hermanos.getNombre();
                fila[2] = hermanos.getApellido();
                fila[3] = hermanos.getNif();
                fila[4] = hermanos.getMunicipio();
                fila[5] = hermanos.getProvincia();
                fila[6] = hermanos.getPais();
                fila[7] = hermanos.getTfno();
                fila[8] = hermanos.getEmail();
                fila[9] = hermanos.getBanco();
                fila[10] = hermanos.getCuenta_bancaria();
                fila[11] =  String.valueOf(hermanos.getTipo_pago_id());
                fila[12] =  String.valueOf(hermanos.getForma_pago_id());
                fila[13] =  String.valueOf(hermanos.getId_hermandad());
                ff.addRow(fila);
            }           
            
            p1.tablaHermano.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaHermano.setRowSorter(ordenar);
            p1.tablaHermano.setModel(ff);
            
        } catch (Exception e) {
       //   e.printStackTrace();  
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
  
      //agregar
      private void agregarPago(int identificador, int numero_hermano_id,String date,int mayordomia_id ,int precio,String pagado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
      p = new PagoCuotas(identificador,numero_hermano_id,date,mayordomia_id,precio,pagado);
      p.guardar();   }
      
      //buscar
      public ArrayList<hermanito> buscarHermano(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        h1 = new hermanito();
        return h1.buscarFiltro(filtro, campo);
    }
}
