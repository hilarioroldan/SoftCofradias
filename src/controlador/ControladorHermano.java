/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
 
//    package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import modelo.*;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.*;
/**
 *
 * @author alex
 */
public class ControladorHermano implements ActionListener {




    hermanito h1;
    HermanitoVista1 hv1;
    DefaultTableModel ff;

    public void agregarHermano(int numero_hermano, String nombre, String apellido, String nif, String municipio, String provincia,String pais, String tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id, int id_hermandad) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        h1 = new hermanito(numero_hermano, nombre,apellido,nif,municipio,provincia,pais,tfno,email,banco,cuenta_bancaria,tipo_pago_id,forma_pago_id,id_hermandad);
        h1.grabar();  
    
    }
        
        
            public void modificarHermano(int numero_hermano, String nombre, String apellido, String nif, String municipio, String provincia,String pais, String tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id, int id_hermandad) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h1 = new hermanito(numero_hermano, nombre,apellido,nif,municipio,provincia,pais,tfno,email,banco,cuenta_bancaria,tipo_pago_id,forma_pago_id,id_hermandad);
        h1.actualizar();
    }
            
            
            /*Metodo para borrar una Hermandad*/
   public void eliminarHermano(int numero_hermano) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanito h1;
        h1 = new hermanito(numero_hermano);
        h1.borrar();
    }

   
    public enum di {

        GUARDAR,SALIR,MODIFICAR,ELIMINAR,BUSCAR,GUARDAR2;
    }

    public void iniciar() {
        h1 = new hermanito();
        hv1 = new HermanitoVista1();
        hv1.setVisible(true);
        hv1.setLocationRelativeTo(null);
        cargarTablaHermanos();
        cargarTablaHermanos2();

        //se añade las acciones a los controles del formulario padre
        hv1.guardar.setActionCommand("GUARDAR");
        hv1.guardar.addActionListener(this);
        hv1.salir.setActionCommand("SALIR");
        hv1.salir.addActionListener(this);
        hv1.modificar.setActionCommand("MODIFICAR");
        hv1.modificar.addActionListener(this);
        hv1.eliminar.setActionCommand("ELIMINAR");
           hv1.eliminar.addActionListener(this);
          hv1.btnBuscar.setActionCommand("BUSCAR");
          hv1.btnBuscar.addActionListener(this);
           hv1.guardar1.setActionCommand("GUARDAR2");
            hv1.guardar1.addActionListener(this);
        hv1.salir2.setActionCommand("SALIR"); 
          hv1.salir2.addActionListener(this);
            hv1.tablaHermano.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
       
    } 
    


    // ACCIONES DEL USUARIO
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (di.valueOf(e.getActionCommand())) {
            case GUARDAR:
                
                
           String numero_hermano = hv1.nherma.getText();
            String nombre = hv1.nombretext.getText();
            String nif = hv1.niftx.getText();
            String apellido=hv1.apellidostxt.getText();
            String municipio=hv1.municipiotxt.getText();
            String provincia=hv1.provinciatxt.getText();
            String pais=hv1.paistxt.getText();
            String tfno=hv1.telefono.getText();
            String email=hv1.emailtxt.getText();
            String banco=hv1.bancotxt.getText();
            String cuenta_bancaria=hv1.cuenta.getText();
            String tipo_pago_id=hv1.tipo_pago.getText();
            String forma_pago_id=hv1.id_pago.getText();
            String id_hermandad=hv1.hid.getText();
                
                

                try {
               agregarHermano(Integer.parseInt(numero_hermano), nombre,apellido,nif,municipio,provincia,pais,tfno,email,banco,cuenta_bancaria,Integer.parseInt(tipo_pago_id),Integer.parseInt(forma_pago_id),Integer.parseInt(id_hermandad));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case SALIR:
                hv1.dispose();
                break;
            case ELIMINAR:
         
      
   eliminarSeleccion();
        
        //limpiarTexto();
       cargarTablaHermanos();
break;
          case MODIFICAR:
              
             hv1.modificacion.setSize(1200,550);
        hv1.modificacion.setLocation(100,100);
        hv1.modificacion.setVisible(true);
             int clic = hv1.tablaHermano.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

           
        if (clic!=-1) {
           hv1.nherma1.setEnabled(false);
            hv1.nherma1.setText(hv1.tablaHermano.getValueAt(clic, 0).toString());
            hv1.nombretext1.setText(hv1.tablaHermano.getValueAt(clic, 1).toString());
            hv1.apellidostxt1.setText(hv1.tablaHermano.getValueAt(clic, 3).toString());
            hv1.niftx1.setText(hv1.tablaHermano.getValueAt(clic, 2).toString());
            hv1.municipiotxt1.setText(hv1.tablaHermano.getValueAt(clic, 4).toString());
            hv1.provinciatxt1.setText(hv1.tablaHermano.getValueAt(clic, 5).toString());
            hv1.paistxt1.setText(hv1.tablaHermano.getValueAt(clic, 6).toString());
            hv1.telefono1.setText(hv1.tablaHermano.getValueAt(clic, 7).toString());
            hv1.emailtxt1.setText(hv1.tablaHermano.getValueAt(clic, 8).toString());
            hv1.bancotxt1.setText(hv1.tablaHermano.getValueAt(clic, 9).toString());
            hv1.cuenta1.setText(hv1.tablaHermano.getValueAt(clic, 10).toString());
            hv1.tipo_pago1.setText(hv1.tablaHermano.getValueAt(clic, 11).toString());
            hv1.id_pago1.setText(hv1.tablaHermano.getValueAt(clic, 12).toString());
            hv1.hid1.setText(hv1.tablaHermano.getValueAt(clic, 13).toString());
        }       
               //cargarTablaHermanos();
               //limpiarTexto();
              break;
          case BUSCAR:
            cargarTablaHermanos2();
             break;
          case GUARDAR2:
               modificar();
              break;
              
    }
        

}
    

    /*Metodo para leer todas las hermandades*/
    public ArrayList<hermanito> recargarHermanos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanito h2;
        h2 = new hermanito();
        return h2.leerTodos();
    }
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE hermanos;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            hv1.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                hv1.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermano.class.getName()).log(Level.SEVERE, null, ex);
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

            hv1.tablaHermano.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            hv1.tablaHermano.setRowSorter(ordenar);
            hv1.tablaHermano.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
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
            String campo =  (String) hv1.cmbBD1.getSelectedItem();
            String filtro = hv1.txtFiltro1.getText();
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
            
            hv1.tablaHermano1.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            hv1.tablaHermano1.setRowSorter(ordenar);
            hv1.tablaHermano1.setModel(ff);
            
        } catch (Exception e) {
       //   e.printStackTrace();  
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    
    
    
    
    
    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
        int clic = hv1.tablaHermano.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic!=-1) {
             hv1.busquedah.setText(hv1.tablaHermano.getValueAt(clic, 1).toString());
             hv1.busquedaA.setText(hv1.tablaHermano.getValueAt(clic, 2).toString());
             hv1.herm.setText(hv1.tablaHermano.getValueAt(clic, 0).toString());
           
        }
    }
    
    
    
    
    private void eliminarSeleccion() {
        String num = hv1.herm.getText();
        try {
            eliminarHermano(Integer.parseInt(num));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermano.class.getName()).log(Level.SEVERE, null, ex);
        }
              cargarTablaHermanos();

        hv1.tablaHermano.setModel(new DefaultTableModel());
       
    }
    
    private void modificar(){
     
         String numero_hermano1 = hv1.nherma1.getText();
            String nombre1 = hv1.nombretext1.getText();
            String nif1 = hv1.niftx1.getText();
            String apellido1=hv1.apellidostxt1.getText();
            String municipio1=hv1.municipiotxt1.getText();
            String provincia1=hv1.provinciatxt1.getText();
            String pais1=hv1.paistxt1.getText();
            String tfno1=hv1.telefono1.getText();
            String email1=hv1.emailtxt1.getText();
            String banco1=hv1.bancotxt1.getText();
            String cuenta_bancaria1=hv1.cuenta1.getText();
            String tipo_pago_id1=hv1.tipo_pago1.getText();
            String forma_pago_id1=hv1.id_pago1.getText();
            String id_hermandad1=hv1.hid1.getText();
                
                 
           try {
               modificarHermano(Integer.parseInt(numero_hermano1), nombre1,apellido1,nif1,municipio1,provincia1,pais1,tfno1,email1,banco1,cuenta_bancaria1,Integer.parseInt(tipo_pago_id1),Integer.parseInt(forma_pago_id1),Integer.parseInt(id_hermandad1));
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            }            
               cargarTablaHermanos();
    }
    
 
    
   public ArrayList<hermanito> buscarHermano(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        h1 = new hermanito();
        return h1.buscarFiltro(filtro, campo);
    }
}

